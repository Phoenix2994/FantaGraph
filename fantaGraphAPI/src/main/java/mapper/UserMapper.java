package mapper;

import controller.FantaTeamController;
import model.FantaTeam;
import model.User;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Property;

import java.util.List;

public class UserMapper extends Mapper {

    public long mapUserId(Vertex user){ return this.mapLong(user, Property.USER_ID[0]); }

    public User VertexToEntity(Vertex user, List<Object> fantateamsIdList){

        String username = this.mapString(user, Property.USERNAME[0]);
        long id = this.mapLong(user, Property.USER_ID[0]);
        String email = this.mapString(user,Property.EMAIL[0]);

        FantaTeamController fantaTeamController = new FantaTeamController();
        FantaTeam[] teams = fantaTeamController.getFantaTeamsByIdList(fantateamsIdList);

        return new User(id,username,email, teams);
    }
}
