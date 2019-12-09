package mapper;

import dao.BaseDAO;
import model.PlayerStats;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Property;

public class PlayerStatsMapper extends Mapper{
    private BaseDAO dao = new BaseDAO();

    public PlayerStats VertexToStats(Vertex stat, Vertex team){
        String year = this.mapString(stat, Property.YEAR);
        String seasonTeam = "";
        try {
            seasonTeam = this.mapName(team);
        } catch (Exception e){
            seasonTeam = this.mapString(stat, Property.TEAM_LABEL);
        }
        long playedMatches = this.mapLong(stat,Property.PLAYED_MATCHES);
        long goals = this.mapLong(stat,Property.SCORED_GOALS);
        long assists = this.mapLong(stat,Property.ASSISTS);
        long concededGoals = this.mapLong(stat,Property.CONCEDED_GOALS);
        long ownGoals = this.mapLong(stat,Property.OWN_GOALS);
        long redCards = this.mapLong(stat,Property.RED_CARDS);
        long yellowCards = this.mapLong(stat,Property.YELLOW_CARDS);
        double average = this.mapDouble(stat,Property.AVERAGE);
        double fantaAverage = this.mapDouble(stat,Property.FANTA_AVERAGE);
        double avgGaussianRating = this.mapDouble(stat,Property.GAUSS_AVERAGE);
        double avgGaussianFantaRating = this.mapDouble(stat,Property.GAUSS_FANTA_AVERAGE);

        return new PlayerStats(year, seasonTeam, playedMatches, goals, assists, concededGoals, ownGoals, redCards, yellowCards, average, fantaAverage,avgGaussianRating,avgGaussianFantaRating);
    }
}
