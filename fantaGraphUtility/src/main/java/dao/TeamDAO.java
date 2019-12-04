package dao;

import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Branch;
import utility.labels.Node;
import utility.labels.Property;

import java.util.List;

public class TeamDAO extends BaseDAO{

    public Path getTeamPathById(long id){
        return this.g.V().has(Property.TEAM_ID[0], id).as(Node.TEAM).in(Branch.COACH_TO_TEAM).as(Node.COACH)
                .out(Branch.COACH_TO_TEAM).has(Property.TEAM_ID[0],id).in(Branch.PRESIDENT_TO_TEAM).as(Node.PRESIDENT)
                .out(Branch.PRESIDENT_TO_TEAM).has(Property.TEAM_ID[0],id).out(Branch.TEAM_TO_STADIUM).as(Node.STADIUM).path().next();
    }

    public List<Object> getIdList(){
        return super.getIdList(Property.TEAM_ID[0]);
    }

    public Vertex addTeam(String name, String logo, long id){
        Vertex team = this.g.addV(Node.TEAM).property(Property.NAME[0], name)
                .property(Property.LOGO[0], logo)
                .property(Property.TEAM_ID[0], id).next();
        this.g.tx().commit();
        return team;
    }



    public boolean addStadiumToTeam(Vertex team,Vertex stadium){
        this.addEdge(team,stadium,Branch.TEAM_TO_STADIUM);
        return true;
    }
}
