package mapper;

import dao.BaseDAO;
import model.PlayerStats;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Property;

public class PlayerStatsMapper extends Mapper{
    private BaseDAO dao = new BaseDAO();

    public PlayerStats VertexToStats(Vertex stat, Vertex team){
        String year = this.mapString(stat, Property.YEAR[0]);
        String seasonTeam = "";
        try {
            seasonTeam = this.mapName(team);
        } catch (Exception e){
            seasonTeam = this.mapString(stat, Property.TEAM_LABEL[0]);
        }
        long playedMatches = this.mapLong(stat,Property.PLAYED_MATCHES[0]);
        long goals = this.mapLong(stat,Property.SCORED_GOALS[0]);
        long assists = this.mapLong(stat,Property.ASSISTS[0]);
        long concededGoals = this.mapLong(stat,Property.CONCEDED_GOALS[0]);
        long ownGoals = this.mapLong(stat,Property.OWN_GOALS[0]);
        long redCards = this.mapLong(stat,Property.RED_CARDS[0]);
        long yellowCards = this.mapLong(stat,Property.YELLOW_CARDS[0]);
        double average = this.mapDouble(stat,Property.AVERAGE[0]);
        double fantaAverage = this.mapDouble(stat,Property.FANTA_AVERAGE[0]);
        double avgGaussianRating = this.mapDouble(stat,Property.GAUSS_AVERAGE[0]);
        double avgGaussianFantaRating = this.mapDouble(stat,Property.GAUSS_FANTA_AVERAGE[0]);

        return new PlayerStats(year, seasonTeam, playedMatches, goals, assists, concededGoals, ownGoals, redCards, yellowCards, average, fantaAverage,avgGaussianRating,avgGaussianFantaRating);
    }
}
