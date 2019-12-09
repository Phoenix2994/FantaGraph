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
        Path result = this.g.V().has(Property.PLAYER_ID, id).as("player")
                .out(Branch.PLAYER_TO_TEAM).as("team")
                .select("player").out(Branch.PLAYER_TO_PROSECUTOR).as("prosecutor")
                .path().next();
        commit();
        return result;
    }

    public List<Path> getAllPlayersPaths(){
        List<Path> result = this.g.V().has(Property.PLAYER_ID).as("player")
                .out(Branch.PLAYER_TO_TEAM).as("team")
                .select("player").out(Branch.PLAYER_TO_PROSECUTOR).as("prosecutor")
                .path().toList();
        commit();
        return result;
    }

    public List<Object> getIdList(){
        return super.getIdList(Property.PLAYER_ID);
    }

    public Vertex addPlayer(String name, String birthdate, String birthplace, String nationality, String height, String role, String mainFoot, String img, long id, long quot){
        Vertex result = this.g.addV(Node.PLAYER)
                .property(Property.NAME, name).property(Property.BIRTHDATE, birthdate)
                .property(Property.NATIONALITY, nationality).property(Property.HEIGHT, height)
                .property(Property.ROLE, role).property(Property.MAIN_FOOT, mainFoot)
                .property(Property.IMG, img).property(Property.PLAYER_ID, id)
                .property(Property.QUOT, quot).property(Property.BIRTHPLACE, birthplace).next();
        commit();
        return result;
    }

    public boolean addTeamToPlayer(Vertex player, Vertex team){
        this.addEdge(player,team,Branch.PLAYER_TO_TEAM);
        commit();
        return true;
    }
    public boolean addTeamToPlayer(long playerId, long teamId){
        return this.addEdge(playerId,Property.PLAYER_ID,teamId,Property.TEAM_ID,Branch.PLAYER_TO_TEAM);
    }

    public boolean addStatsToPlayer(Vertex player, Vertex stats){
       return this.addEdge(player,stats,Branch.PLAYER_TO_SEASON);
    }

    public boolean addProsecutorToPlayer(Vertex player, Vertex prosecutor){
        return this.addEdge(player,prosecutor,Branch.PLAYER_TO_PROSECUTOR);
    }
}
