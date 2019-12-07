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
                .property(Property.YEAR[0], year)
                .property(Property.ROLE[0], role).property(Property.PLAYED_MATCHES[0], playedMatches)
                .property(Property.AVERAGE[0], average)
                .property(Property.FANTA_AVERAGE[0], fantaAverage)
                .property(Property.GAUSS_AVERAGE[0], gaussAverage)
                .property(Property.GAUSS_FANTA_AVERAGE[0], gaussFantaAverage)
                .property(Property.SCORED_GOALS[0], scoredGoals)
                .property(Property.CONCEDED_GOALS[0], concededGoals)
                .property(Property.ASSISTS[0], assists).property(Property.YELLOW_CARDS[0], yellowCards)
                .property(Property.RED_CARDS[0], redCards).property(Property.OWN_GOALS[0], ownGoals).next();
        if(teamVertex != null){
            g.V(stats).as("a").V(team).addE(Branch.SEASON_TO_TEAM).from("a").next();
        } else {
            stats.property(Property.TEAM_LABEL[0], team);
        }
        commit();
        return stats;
    }
}
