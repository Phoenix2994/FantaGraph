package dao;

import dao.BaseDAO;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Branch;
import utility.labels.Node;
import utility.labels.Property;

public class PlayerStatsDAO extends BaseDAO {

    public Vertex addPlayerStats(String year, String role, long playedMatches, long scoredGoals, long concededGoals,
                                 long ownGoals, long assists, long yellowCards, long redCards, double average,
                                 double fantaAverage, double gaussAverage, double gaussFantaAverage, Vertex teamVertex, String team){
        Vertex stats = this.g.addV(Node.SEASON)
                .property(Property.YEAR, year)
                .property(Property.ROLE, role).property(Property.PLAYED_MATCHES, playedMatches)
                .property(Property.AVERAGE, average)
                .property(Property.FANTA_AVERAGE, fantaAverage)
                .property(Property.GAUSS_AVERAGE, gaussAverage)
                .property(Property.GAUSS_FANTA_AVERAGE, gaussFantaAverage)
                .property(Property.SCORED_GOALS, scoredGoals)
                .property(Property.CONCEDED_GOALS, concededGoals)
                .property(Property.ASSISTS, assists).property(Property.YELLOW_CARDS, yellowCards)
                .property(Property.RED_CARDS, redCards).property(Property.OWN_GOALS, ownGoals).next();
        if(teamVertex != null){
            g.V(stats).as("a").V(teamVertex).addE(Branch.SEASON_TO_TEAM).from("a").next();
        } else {
            stats.property(Property.TEAM_LABEL, team);
        }
        commit();
        return stats;
    }
}
