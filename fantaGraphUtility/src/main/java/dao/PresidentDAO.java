package dao;

import dao.BaseDAO;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Branch;
import utility.labels.Node;
import utility.labels.Property;

import java.util.List;

public class PresidentDAO extends BaseDAO {

    public Path getPresidentPathById(long id){
        Path result = this.g.V().has(Property.PRESIDENT_ID, id).as("president").out(Branch.PRESIDENT_TO_TEAM).as("team").path().next();
        commit();
        return result;
    }

    public List<Path> getPresidentsPaths(){
        List<Path> result = this.g.V().has(Property.PRESIDENT_ID).as("president").out(Branch.PRESIDENT_TO_TEAM).as("team").path().toList();
        commit();
        return result;
    }

    public Vertex addPresident(long id, String name, String birthdate, String birthplace, String nationality){
        Vertex result =  this.g.addV(Node.PRESIDENT).property(Property.NAME, name)
                .property(Property.BIRTHDATE, birthdate)
                .property(Property.BIRTHPLACE, birthplace)
                .property(Property.NATIONALITY, nationality).property(Property.PRESIDENT_ID, id).next();
        commit();
        return result;
    }

    public boolean addTeamToPresident(Vertex president, Vertex team){
        this.addEdge(president,team,Branch.PRESIDENT_TO_TEAM);
        commit();
        return true;
    }
}
