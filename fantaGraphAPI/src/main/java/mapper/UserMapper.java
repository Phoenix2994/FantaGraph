package mapper;

import model.FantaTeam;
import model.User;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Property;

import java.util.List;

public class UserMapper extends Mapper {

    public long mapUserId(Vertex user){ return this.mapLong(user,Property.USER_ID[0]); }

    public User VertexToEntity(Vertex user, List<Vertex> fantaTeamsList, List<List<Object>> birthdateList){

        String username = this.mapString(user, Property.USERNAME[0]);
        long id = this.mapLong(user, Property.USER_ID[0]);
        String email = this.mapString(user, Property.EMAIL[0]);

        FantaTeamMapper fantaTeamMapper = new FantaTeamMapper();
        FantaTeam[] teams = new FantaTeam[fantaTeamsList.size()];

        for(int i = 0; i < fantaTeamsList.size(); i++){
            Vertex fantaTeam = fantaTeamsList.get(i);
            List<Object> birthdates = birthdateList.get(i);
            teams[i] = fantaTeamMapper.VertexToModel(fantaTeam,user,birthdates);
        }

        return new User(id,username,email, teams);
    }
}
