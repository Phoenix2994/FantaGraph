package mapper;

import model.Team;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Property;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.YEARS;

public class TeamMapper extends Mapper {

    public double getAvgAge(List<Object> playersBirthdate){
        double total = 0;
        for(Object o : playersBirthdate){
            LocalDate birthdate = LocalDate.parse((String)o.toString());
            double age = YEARS.between(birthdate, today);
            total += age;
        }
        return Math.round(total/playersBirthdate.size() * 100.00) / 100.00;
    }

    public long mapTeamId(Vertex team){ return this.mapLong(team, Property.TEAM_ID[0]); }

    public Team VertexToTeam(Vertex team, Vertex coach, Vertex president, Vertex stadium, List<Object> playersBirthdate){

        long id = this.mapTeamId(team);
        String name = this.mapName(team);
        long players = playersBirthdate.size();
        double avgAge = getAvgAge(playersBirthdate);
        String logo = this.mapString(team,Property.LOGO[0]);
        String coachName = this.mapName(coach);
        long coachId = this.mapLong(coach, Property.COACH_ID[0]);
        String presidentName = this.mapName(president);
        long presidentId = this.mapLong(president, Property.PRESIDENT_ID[0]);
        String stadiumName = this.mapName(stadium);
        long stadiumId = this.mapLong(stadium, Property.STADIUM_ID[0]);

        return new Team(id, name, players, avgAge, logo, coachName, coachId, presidentName, presidentId, stadiumName, stadiumId);
    }
}
