package dao;

import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Branch;
import utility.labels.Node;
import utility.labels.Property;

public class ProsecutorDAO extends BaseDAO {

    public Path getProsecutorPathById(long id){
        return this.g.V().has(Property.PROSECUTOR_ID[0], id).as(Node.PROSECUTOR).in(Branch.PLAYER_TO_PROSECUTOR).as(Node.PLAYER).path().next();

    }

    public Vertex addProsecutor(long id, String name){
        Vertex prosecutor = this.g.addV(Node.PROSECUTOR).property(Property.NAME[0], name)
                .property(Property.PROSECUTOR_ID[0], id).next();
        this.g.tx().commit();
        return prosecutor;
    }
}
