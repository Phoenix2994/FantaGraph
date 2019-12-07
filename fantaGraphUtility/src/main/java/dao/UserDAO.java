package dao;

import dao.BaseDAO;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Node;
import utility.labels.Property;

import java.util.List;

public class UserDAO extends BaseDAO {

    public long getNewUserId(){ return super.getNewId(Node.USER); }

    public List<Object> getIdList(){
        List<Object> result = getTraversalByProp(Property.USER_ID[0]).order().by(Property.USERNAME[0]).values(Property.USER_ID[0]).toList();
        commit();
        return result;
    }

    public Vertex addUser(String username, String email){
        long id = this.getNewUserId();
        Vertex result = this.g.addV(Node.USER).property(Property.USERNAME[0],username).property(Property.USER_ID[0], id).property(Property.EMAIL[0],email).next();
        commit();
        return result;
    }

}
