package dao;

import dao.BaseDAO;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Branch;
import utility.labels.Node;
import utility.labels.Property;

import java.util.List;

public class PlayerDAO extends BaseDAO {

    public Path getPlayerPathById(long id){
        Path result = this.g.V().has(Property.PLAYER_ID[0], id).as("player")
                .out(Branch.PLAYER_TO_TEAM).as("team")
                .select("player").out(Branch.PLAYER_TO_PROSECUTOR).as("prosecutor")
                .path().next();
        commit();
        return result;
    }

    public List<Path> getAllPlayersPaths(){
        List<Path> result = this.g.V().has(Property.PLAYER_ID[0]).as("player")
                .out(Branch.PLAYER_TO_TEAM).as("team")
                .select("player").out(Branch.PLAYER_TO_PROSECUTOR).as("prosecutor")
                .path().toList();
        commit();
        return result;
    }

    public List<Object> getIdList(){
        return super.getIdList(Property.PLAYER_ID[0]);
    }

    public Vertex addPlayer(String name, String birthdate, String birthplace, String nationality, String height, String role, String mainFoot, String img, long id, long quot){
        Vertex result = this.g.addV(Node.PLAYER)
                .property(Property.NAME[0], name).property(Property.BIRTHDATE[0], birthdate)
                .property(Property.NATIONALITY[0], nationality).property(Property.HEIGHT[0], height)
                .property(Property.ROLE[0], role).property(Property.MAIN_FOOT[0], mainFoot)
                .property(Property.IMG[0], img).property(Property.PLAYER_ID[0], id)
                .property(Property.QUOT[0], quot).property(Property.BIRTHPLACE[0], birthplace).next();
        commit();
        return result;
    }

    public boolean addTeamToPlayer(Vertex player, Vertex team){
        return this.addEdge(player,team,Branch.PLAYER_TO_TEAM);
    }
    public boolean addTeamToPlayer(long playerId, long teamId){
        return this.addEdge(playerId,Property.PLAYER_ID[0],teamId,Property.TEAM_ID[0],Branch.PLAYER_TO_TEAM);
    }

    public boolean addStatsToPlayer(Vertex player, Vertex stats){
       return this.addEdge(player,stats,Branch.PLAYER_TO_SEASON);
    }

    public boolean addProsecutorToPlayer(Vertex player, Vertex prosecutor){
        return this.addEdge(player,prosecutor,Branch.PLAYER_TO_PROSECUTOR);
    }
}
