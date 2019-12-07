package dao;

import dao.BaseDAO;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Branch;
import utility.labels.Node;
import utility.labels.Property;

import java.util.List;

public class CoachDAO extends BaseDAO {

    public Path getCoachPathById(long id){
        Path result = this.g.V().has(Property.COACH_ID[0], id).as("coach")
                .out(Branch.COACH_TO_TEAM).as("team").path().next();
        commit();
        return result;
    }

    public List<Path> getAllCoachesPaths(){
        List<Path> result = this.g.V().has(Property.COACH_ID[0]).as("coach")
                .out(Branch.COACH_TO_TEAM).as("team").path().toList();
        commit();
        return result;
    }

    public Vertex addCoach(long id, String name, String birthdate, String birthplace, String nationality, String module){
        Vertex result = g.addV(Node.COACH).property(Property.NAME[0], name)
                .property(Property.BIRTHDATE[0], birthdate)
                .property(Property.BIRTHPLACE[0], birthplace)
                .property(Property.NATIONALITY[0], nationality).property(Property.MODULE[0], module)
                .property(Property.COACH_ID[0], id).next();
        commit();
        return result;
    }

    public boolean addTeamToCoach(Vertex coach,Vertex team){
        return this.addEdge(coach,team,Branch.COACH_TO_TEAM);
    }

    public boolean addPresidentToCoach(Vertex coach,Vertex president){
        return this.addEdge(coach,president,Branch.COACH_TO_PRESIDENT);
    }
}
