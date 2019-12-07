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
        Path result = this.g.V().has(Property.STADIUM_ID[0], id).as("stadium").in(Branch.TEAM_TO_STADIUM).as("team").path().next();
        commit();
        return result;
    }

    public List<Path> getStadiumsPaths(){
        List<Path> result = this.g.V().has(Property.STADIUM_ID[0]).as("stadium").in(Branch.TEAM_TO_STADIUM).as("team").path().toList();
        commit();
        return result;
    }

    public Vertex addStadium(long id, String name, String city, long capacity, String img){
        Vertex result = this.g.addV(Node.STADIUM).property(Property.NAME[0], name)
                .property(Property.CITY[0], city).property(Property.CAPACITY[0], capacity)
                .property(Property.IMG[0], img).property(Property.STADIUM_ID[0], id).next();
        commit();
        return result;
    }
}
