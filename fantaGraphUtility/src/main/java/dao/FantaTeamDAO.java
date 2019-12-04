package dao;

import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Branch;
import utility.labels.Node;
import utility.labels.Property;

import java.util.List;

public class FantaTeamDAO extends BaseDAO {

    public Path getFantaTeamPathById(long id){
        return this.g.V().has(Property.FANTATEAM_ID[0], id).as(Node.TEAM).in(Branch.USER_TO_FANTATEAM).as(Node.USER)
                .path().next();
    }
    public List<Object> getIdList(){
        return super.getIdList(Property.FANTATEAM_ID[0]);
    }

    public Vertex addFantaTeam(long userId, String name) throws Exception {
        Vertex user = this.getVertexById(Property.USER_ID[0], userId);

        boolean check = this.g.V(user).out(Branch.USER_TO_FANTATEAM).has(Property.NAME[0], name).hasNext();

        if (!check) {
            long newId = this.getNewId(Node.FANTATEAM);
            Vertex team = this.g.addV(Node.FANTATEAM).property(Property.FANTATEAM_ID[0], newId).property(Property.NAME[0], name).next();
            this.addEdge(user,team,Branch.USER_TO_FANTATEAM);
            return team;
        } else throw new Exception();
    }

    public boolean addPlayerToFantaTeam(long teamId, long playerId){
        Vertex team = this.getVertexById(Property.FANTATEAM_ID[0], teamId);
        boolean check = this.g.V(team).in(Branch.PLAYER_TO_FANTATEAM).has(Property.PLAYER_ID[0], playerId).hasNext();

        if(!check) {
            Vertex player = this.getVertexById(Property.PLAYER_ID[0], playerId);
            this.g.V(player).as("a").V(team).addE(Branch.PLAYER_TO_FANTATEAM).from("a").next();
            return true;
        }
        else return false;
    }

    public boolean removeFantaTeam(long teamId){
        this.g.V().has(Property.FANTATEAM_ID[0], teamId).drop().iterate();
        return true;
    }

    public boolean removeAllFantaTeams(){
        this.g.V().has(Property.FANTATEAM_ID[0]).drop().iterate();
        return true;
    }

    public boolean removePlayerFromFantaTeam(long teamId, long playerId){
        GraphTraversal<Vertex, Object> e = this.g.V().has(Property.PLAYER_ID[0],playerId).outE().as("e").inV().has(Property.FANTATEAM_ID[0], teamId).select("e").drop().iterate();
        return true;
    }

    public List<Object> getPlayersIdList(long id){
        return this.g.V().has(Property.FANTATEAM_ID[0], id).in(Branch.PLAYER_TO_FANTATEAM).values(Property.PLAYER_ID[0]).toList();
    }
}
