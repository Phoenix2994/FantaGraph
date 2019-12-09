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
        Path result = this.g.V().has(Property.COACH_ID, id).as("coach")
                .out(Branch.COACH_TO_TEAM).as("team").path().next();
        commit();
        return result;
    }

    public List<Path> getAllCoachesPaths(){
        List<Path> result = this.g.V().has(Property.COACH_ID).as("coach")
                .out(Branch.COACH_TO_TEAM).as("team").path().toList();
        commit();
        return result;
    }

    public Vertex addCoach(long id, String name, String birthdate, String birthplace, String nationality, long module){
        Vertex result = g.addV(Node.COACH).property(Property.NAME, name)
                .property(Property.BIRTHDATE, birthdate)
                .property(Property.BIRTHPLACE, birthplace)
                .property(Property.NATIONALITY, nationality).property(Property.MODULE, module)
                .property(Property.COACH_ID, id).next();
        commit();
        return result;
    }

    public boolean addTeamToCoach(Vertex coach,Vertex team){
        this.addEdge(coach,team,Branch.COACH_TO_TEAM);
        commit();
        return true;
    }

    public boolean addPresidentToCoach(Vertex coach,Vertex president){
        this.addEdge(coach,president,Branch.COACH_TO_PRESIDENT);
        commit();
        return true;
    }
}
