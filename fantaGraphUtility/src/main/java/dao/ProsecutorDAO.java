package dao;

import dao.BaseDAO;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Branch;
import utility.labels.Node;
import utility.labels.Property;

import java.util.List;

public class ProsecutorDAO extends BaseDAO {

    public List<Path> getPlayersPaths(long id){
        List<Path> result = this.g.V().has(Property.PROSECUTOR_ID, id).in(Branch.PLAYER_TO_PROSECUTOR).as("player").out(Branch.PLAYER_TO_TEAM).as("team").path().toList();
        commit();
        return result;
    }

    public Vertex addProsecutor(long id, String name){
        Vertex result = this.g.addV(Node.PROSECUTOR).property(Property.NAME, name)
                .property(Property.PROSECUTOR_ID, id).next();
        commit();
        return result;
    }
}
