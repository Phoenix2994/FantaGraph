package dao;

import dao.BaseDAO;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Branch;
import utility.labels.Node;
import utility.labels.Property;

import java.util.List;

public class TeamDAO extends BaseDAO {

    public Path getTeamPathById(long id){
        Path result = this.g.V().has(Property.TEAM_ID,id).as("team").in(Branch.COACH_TO_TEAM).as("coach")
                .select("team").in(Branch.PRESIDENT_TO_TEAM).as("president")
                .select("team").out(Branch.TEAM_TO_STADIUM).as("stadium").path().next();
        commit();
        return result;
    }

    public List<Path> getTeamsPaths(){
        List<Path> result = this.g.V().has(Property.TEAM_ID).as("team").in(Branch.COACH_TO_TEAM).as("coach")
                .select("team").in(Branch.PRESIDENT_TO_TEAM).as("president")
                .select("team").out(Branch.TEAM_TO_STADIUM).as("stadium").path().toList();
        commit();
        return result;
    }

    public List<Object> getIdList(){
        return super.getIdList(Property.TEAM_ID);
    }

    public Vertex addTeam(String name, String logo, long id){
        Vertex result = this.g.addV(Node.TEAM).property(Property.NAME, name)
                .property(Property.LOGO, logo)
                .property(Property.TEAM_ID, id).next();
        commit();
        return result;
    }

    public boolean addStadiumToTeam(Vertex team,Vertex stadium){
        this.addEdge(team,stadium,Branch.TEAM_TO_STADIUM);
        commit();
        return true;
    }
}
