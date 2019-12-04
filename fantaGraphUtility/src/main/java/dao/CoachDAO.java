package dao;

import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Branch;
import utility.labels.Node;
import utility.labels.Property;

public class CoachDAO extends BaseDAO {

    public Path getCoachPathById(long id){
        return this.g.V().has(Property.COACH_ID[0], id).as(Node.COACH)
                .out(Branch.COACH_TO_TEAM).as(Node.TEAM).path().next();
    }

    public Vertex addCoach(String coach_name, String coach_data, String coach_place, String coach_nat, long coach_schema, long coach_counter){
        Vertex coach = g.addV(Node.COACH).property(Property.NAME[0], coach_name)
                .property(Property.BIRTHDATE[0], coach_data.split(" ")[0])
                .property(Property.BIRTHPLACE[0], coach_place)
                .property(Property.NATIONALITY[0], coach_nat).property(Property.MODULE[0], coach_schema)
                .property(Property.COACH_ID[0], coach_counter).next();
        this.g.tx().commit();
        return coach;
    }

    public boolean addTeamToCoach(Vertex coach,Vertex team){
        this.addEdge(coach,team, Branch.COACH_TO_TEAM);
        return true;
    }

    public boolean addPresidentToCoach(Vertex coach,Vertex president){
        this.addEdge(coach,president,Branch.COACH_TO_PRESIDENT);
        return true;
    }
}
