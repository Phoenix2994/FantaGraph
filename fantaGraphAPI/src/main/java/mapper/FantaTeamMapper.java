package mapper;

import model.FantaTeam;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Property;

import java.util.List;

public class FantaTeamMapper extends TeamMapper {

    public long mapFantaTeamId(Vertex fantateam){ return this.mapLong(fantateam,Property.FANTATEAM_ID[0]); }

    public FantaTeam VertexToFantaTeam(Vertex fantateam, Vertex user, List<Object> playersBirthdate){

        long id = this.mapFantaTeamId(fantateam);
        String name = this.mapName(fantateam);
        long players = playersBirthdate.size();
        double avgAge = getAvgAge(playersBirthdate);
        String logo = "";
        String username = this.mapString(user,Property.USERNAME[0]);
        long ownerId = this.mapLong(user, Property.USER_ID[0]);

        return new FantaTeam(id, name, players, avgAge, logo, username, ownerId);
    }
}
