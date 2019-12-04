package dao;

import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Branch;
import utility.labels.Node;
import utility.labels.Property;

public class PresidentDAO extends BaseDAO {

    public Path getPresidentPathById(long id){
        return this.g.V().has(Property.PRESIDENT_ID[0], id).as(Node.PRESIDENT).out(Branch.PRESIDENT_TO_TEAM).as(Node.TEAM).path().next();
    }

    public Vertex addPresident(long id, String name, String birthdate, String birthplace, String nationality){
        Vertex president =  this.g.addV(Node.PRESIDENT).property(Property.NAME[0], name)
                .property(Property.BIRTHDATE[0], birthdate)
                .property(Property.BIRTHPLACE[0], birthplace)
                .property(Property.NATIONALITY[0], nationality).property(Property.PRESIDENT_ID[0], id).next();
        this.g.tx().commit();
        return president;
    }

    public boolean addTeamToPresident(Vertex president, Vertex team){
        return this.addEdge(president,team,Branch.PRESIDENT_TO_TEAM);
    }
}
