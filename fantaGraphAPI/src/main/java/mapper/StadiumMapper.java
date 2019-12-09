package mapper;

import model.Stadium;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Property;

public class StadiumMapper extends Mapper {

    public long mapStadiumId(Vertex stadium){ return this.mapLong(stadium, Property.STADIUM_ID); }

    public Stadium VertexToEntity(Vertex stadium, Vertex team){

        String name = this.mapName(team);
        long id = this.mapStadiumId(stadium);
        String city = this.mapString(stadium, Property.CITY);
        long capacity = this.mapLong(stadium, Property.CAPACITY);
        String img = this.mapImg(stadium);
        String teamName = this.mapName(team);
        long teamId = this.mapLong(team, Property.TEAM_ID);

        return new Stadium(id,name,city,capacity,img,teamName,teamId);
    }
}
