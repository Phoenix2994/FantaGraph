package dao;

import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Branch;
import utility.labels.Node;
import utility.labels.Property;

public class StadiumDAO extends BaseDAO {

    public Path getStadiumPathById(long id){
        return this.g.V().has(Property.STADIUM_ID[0], id).as(Node.STADIUM).in(Branch.TEAM_TO_STADIUM).as(Node.TEAM).path().next();
    }

    public Vertex addStadium(long id, String name, String city, long capacity, String img){
        Vertex stadium = this.g.addV(Node.STADIUM).property(Property.NAME[0], name)
                .property(Property.CITY[0], city).property(Property.CAPACITY[0], capacity)
                .property(Property.IMG[0], img).property(Property.STADIUM_ID[0], id).next();
        this.g.tx().commit();
        return stadium;
    }

}
