package dao;

import dao.BaseDAO;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Branch;
import utility.labels.Node;
import utility.labels.Property;

import java.util.List;

public class FantaTeamDAO extends BaseDAO {

    public Path getFantaTeamPathById(long id){
        Path result = this.g.V().has(Property.FANTATEAM_ID[0], id).as("team").in(Branch.USER_TO_FANTATEAM).as("user")
                .path().next();
        commit();
        return result;
    }

    public List<Path> getAllFantaTeamsPaths(){
        List<Path> result = this.g.V().has(Property.FANTATEAM_ID[0]).as("team").in(Branch.USER_TO_FANTATEAM).as("user")
                .path().toList();
        commit();
        return result;
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
            this.addEdge(player,team,Branch.PLAYER_TO_FANTATEAM);
            return true;
        }
        else return false;
    }

    public boolean removeFantaTeam(long teamId){
        this.g.V().has(Property.FANTATEAM_ID[0], teamId).drop().iterate();
        commit();
        return true;
    }

    public boolean removeAllFantaTeams(){
        this.g.V().has(Property.FANTATEAM_ID[0]).drop().iterate();
        commit();
        return true;
    }

    public boolean removePlayerFromFantaTeam(long teamId, long playerId){
        GraphTraversal<Vertex, Object> e = this.g.V().has(Property.PLAYER_ID[0],playerId).outE().as("e").inV().has(Property.FANTATEAM_ID[0], teamId).select("e").drop().iterate();
        commit();
        return true;
    }

    public List<Object> getPlayersIdList(long id){
        List<Object> result = this.g.V().has(Property.FANTATEAM_ID[0], id).in(Branch.PLAYER_TO_FANTATEAM).values(Property.PLAYER_ID[0]).toList();
        commit();
        return result;
    }

    public List<Path> getPlayersPaths(long id){
        List<Path> result = this.g.V().has(Property.FANTATEAM_ID[0],id).in(Branch.PLAYER_TO_FANTATEAM).as("player")
                .out(Branch.PLAYER_TO_TEAM).as("team")
                .select("player").out(Branch.PLAYER_TO_PROSECUTOR).as("prosecutor")
                .path().toList();
        commit();
        return result;
    }
}
