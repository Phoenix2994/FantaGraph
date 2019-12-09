package mapper;

import model.Coach;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Property;

import java.time.LocalDate;

public class CoachMapper extends Mapper {

    public long mapCoachId(Vertex coach){ return this.mapLong(coach, Property.COACH_ID); }

    public Coach VertexToEntity(Vertex coach, Vertex team){
        long id = this.mapCoachId(coach);
        String name = this.mapName(coach);

        // vanno corrette le date, ora sono timestap con l'ora
        String tmp = this.mapString(coach,Property.BIRTHDATE);
        LocalDate birthdate = LocalDate.parse(tmp.split(" ")[0]);
        String birthplace = this.mapBirthplace(coach);
        String nationality = this.mapNationality(coach);
        String module = this.mapString(coach,Property.MODULE);
        String teamName = this.mapName(team);
        long teamId = this.mapLong(team,Property.TEAM_ID);

        return new Coach(id,name,birthdate,birthplace,nationality,module,teamName, teamId);
    }
}
