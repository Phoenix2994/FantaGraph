package mapper;

import model.Prosecutor;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Property;


public class ProsecutorMapper extends Mapper {

    public long mapProsecutorId(Vertex prosecutor){ return this.mapLong(prosecutor, Property.PROSECUTOR_ID); }

    public Prosecutor VertexToEntity(Vertex prosecutor){
        String name = this.mapName(prosecutor);
        long id = this.mapProsecutorId(prosecutor);

        return new Prosecutor(id,name);
    }
}
