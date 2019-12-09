package dao;

import dao.BaseDAO;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Branch;
import utility.labels.Node;
import utility.labels.Property;

import java.util.List;

public class StadiumDAO extends BaseDAO {

    public Path getStadiumPathById(long id){
        Path result = this.g.V().has(Property.STADIUM_ID, id).as("stadium").in(Branch.TEAM_TO_STADIUM).as("team").path().next();
        commit();
        return result;
    }

    public List<Path> getStadiumsPaths(){
        List<Path> result = this.g.V().has(Property.STADIUM_ID).as("stadium").in(Branch.TEAM_TO_STADIUM).as("team").path().toList();
        commit();
        return result;
    }

    public Vertex addStadium(long id, String name, String city, long capacity, String img){
        Vertex result = this.g.addV(Node.STADIUM).property(Property.NAME, name)
                .property(Property.CITY, city).property(Property.CAPACITY, capacity)
                .property(Property.IMG, img).property(Property.STADIUM_ID, id).next();
        commit();
        return result;
    }
}
