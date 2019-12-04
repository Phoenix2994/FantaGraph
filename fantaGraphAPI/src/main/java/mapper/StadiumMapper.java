package mapper;

import model.Stadium;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Property;

public class StadiumMapper extends Mapper {

    public long mapStadiumId(Vertex stadium){ return this.mapLong(stadium, Property.STADIUM_ID[0]); }

    public Stadium VertexToEntity(Vertex stadium, Vertex team){

        String name = this.mapName(stadium);
        long id = this.mapStadiumId(stadium);
        String city = this.mapString(stadium, Property.CITY[0]);
        long capacity = this.mapLong(stadium, Property.CAPACITY[0]);
        String img = this.mapImg(stadium);
        String teamName = this.mapName(team);
        long teamId = this.mapLong(team, Property.TEAM_ID[0]);

        return new Stadium(id,name,city,capacity,img,teamName,teamId);
    }
}
