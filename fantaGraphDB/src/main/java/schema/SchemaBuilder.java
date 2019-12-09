package schema;

import org.janusgraph.core.*;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.janusgraph.core.schema.JanusGraphManagement;
import utility.labels.Branch;
import utility.labels.CompositeIndex;
import utility.labels.Node;
import utility.labels.Property;

public class SchemaBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchemaBuilder.class);

    /**
     * Creates the vertex labels, the property labels and the related constraints
     */
    private static void createLabelsAndConstraints(final JanusGraphManagement management) {
        // Vertex labels
        VertexLabel player = management.makeVertexLabel(Node.PLAYER).make();
        VertexLabel coach = management.makeVertexLabel(Node.COACH).make();
        VertexLabel president = management.makeVertexLabel(Node.PRESIDENT).make();
        VertexLabel prosecutor = management.makeVertexLabel(Node.PROSECUTOR).make();
        VertexLabel team = management.makeVertexLabel(Node.TEAM).make();
        VertexLabel stadium = management.makeVertexLabel(Node.STADIUM).make();
        VertexLabel league = management.makeVertexLabel(Node.LEAGUE).make();
        VertexLabel season = management.makeVertexLabel(Node.SEASON).make();
        VertexLabel user = management.makeVertexLabel(Node.USER).make();
        VertexLabel fantateam = management.makeVertexLabel(Node.FANTATEAM).make();

        // PropertyKey labels
        PropertyKey assists = management.makePropertyKey(Property.ASSISTS).dataType(Long.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey concededGoals = management.makePropertyKey(Property.CONCEDED_GOALS).dataType(Long.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey average = management.makePropertyKey(Property.AVERAGE).dataType(Double.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey birthdate = management.makePropertyKey(Property.BIRTHDATE).dataType(String.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey birthplace = management.makePropertyKey(Property.BIRTHPLACE).dataType(String.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey capacity = management.makePropertyKey(Property.CAPACITY).dataType(Long.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey city = management.makePropertyKey(Property.CITY).dataType(String.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey coachId = management.makePropertyKey(Property.COACH_ID).dataType(Long.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey country = management.makePropertyKey(Property.COUNTRY).dataType(String.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey email = management.makePropertyKey(Property.EMAIL).dataType(String.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey fantaAverage = management.makePropertyKey(Property.FANTA_AVERAGE).dataType(Double.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey fantateamId = management.makePropertyKey(Property.FANTATEAM_ID).dataType(Long.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey gaussAverage = management.makePropertyKey(Property.GAUSS_AVERAGE).dataType(Double.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey gaussFantaAverage = management.makePropertyKey(Property.GAUSS_FANTA_AVERAGE).dataType(Double.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey height = management.makePropertyKey(Property.HEIGHT).dataType(String.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey img = management.makePropertyKey(Property.IMG).dataType(String.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey logo = management.makePropertyKey(Property.LOGO).dataType(String.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey playedMatches = management.makePropertyKey(Property.PLAYED_MATCHES).dataType(Long.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey mainFoot = management.makePropertyKey(Property.MAIN_FOOT).dataType(String.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey module = management.makePropertyKey(Property.MODULE).dataType(String.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey name = management.makePropertyKey(Property.NAME).dataType(String.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey nationality = management.makePropertyKey(Property.NATIONALITY).dataType(String.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey ownGoals = management.makePropertyKey(Property.OWN_GOALS).dataType(Long.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey password = management.makePropertyKey(Property.PASSWORD).dataType(String.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey playerId = management.makePropertyKey(Property.PLAYER_ID).dataType(Long.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey presidentId = management.makePropertyKey(Property.PRESIDENT_ID).dataType(Long.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey prosecutorId = management.makePropertyKey(Property.PROSECUTOR_ID).dataType(Long.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey quot = management.makePropertyKey(Property.QUOT).dataType(Long.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey redCards = management.makePropertyKey(Property.RED_CARDS).dataType(Long.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey role = management.makePropertyKey(Property.ROLE).dataType(String.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey scoredGoals = management.makePropertyKey(Property.SCORED_GOALS).dataType(Long.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey stadiumId = management.makePropertyKey(Property.STADIUM_ID).dataType(Long.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey teamId = management.makePropertyKey(Property.TEAM_ID).dataType(Long.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey teamLabel = management.makePropertyKey(Property.TEAM_LABEL).dataType(String.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey userId = management.makePropertyKey(Property.USER_ID).dataType(Long.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey username = management.makePropertyKey(Property.USERNAME).dataType(String.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey year = management.makePropertyKey(Property.YEAR).dataType(String.class).cardinality(Cardinality.SINGLE).make();
        PropertyKey yellowCards = management.makePropertyKey(Property.YELLOW_CARDS).dataType(Long.class).cardinality(Cardinality.SINGLE).make();

        //Vertex Constraints
        management.addProperties(player, name, birthdate, birthplace, nationality, height, role, mainFoot, img, playerId, quot);
        management.addProperties(coach, coachId, name, birthdate, birthplace, nationality, module);
        management.addProperties(president, presidentId, name, birthdate, birthplace, nationality);
        management.addProperties(stadium, stadiumId, name, city, capacity, img);
        management.addProperties(prosecutor, prosecutorId, name);
        management.addProperties(team, teamId, name, logo);
        management.addProperties(season, year, role, playedMatches, scoredGoals, yellowCards, redCards, concededGoals, ownGoals, assists, average, fantaAverage, gaussAverage, gaussFantaAverage);
        management.addProperties(user, userId, username);
        management.addProperties(fantateam, fantateamId, name);

        //Edge Labels and Constraints
        EdgeLabel playerToTeam = management.makeEdgeLabel(Branch.PLAYER_TO_TEAM).multiplicity(Multiplicity.MANY2ONE).make();
        management.addConnection(playerToTeam, player, team);
        EdgeLabel playerToFantateam = management.makeEdgeLabel(Branch.PLAYER_TO_FANTATEAM).multiplicity(Multiplicity.MULTI).make();
        management.addConnection(playerToFantateam, player, fantateam);
        EdgeLabel playerToProsecutor = management.makeEdgeLabel(Branch.PLAYER_TO_PROSECUTOR).multiplicity(Multiplicity.MANY2ONE).make();
        management.addConnection(playerToProsecutor, player, prosecutor);
        EdgeLabel coachToTeam = management.makeEdgeLabel(Branch.COACH_TO_TEAM).multiplicity(Multiplicity.ONE2ONE).make();
        management.addConnection(coachToTeam, coach, team);
        EdgeLabel presidentToTeam = management.makeEdgeLabel(Branch.PRESIDENT_TO_TEAM).multiplicity(Multiplicity.ONE2MANY).make();
        management.addConnection(presidentToTeam, president, team);
        EdgeLabel coachToPresident = management.makeEdgeLabel(Branch.COACH_TO_PRESIDENT).multiplicity(Multiplicity.ONE2ONE).make();
        management.addConnection(coachToPresident, coach, president);
        EdgeLabel userToFantateam = management.makeEdgeLabel(Branch.USER_TO_FANTATEAM).multiplicity(Multiplicity.ONE2MANY).make();
        management.addConnection(userToFantateam, user, fantateam);
        EdgeLabel playerToSeason = management.makeEdgeLabel(Branch.PLAYER_TO_SEASON).multiplicity(Multiplicity.ONE2MANY).make();
        management.addConnection(playerToSeason, player, season);
        EdgeLabel seasonToTeam = management.makeEdgeLabel(Branch.SEASON_TO_TEAM).multiplicity(Multiplicity.MANY2ONE).make();
        management.addConnection(seasonToTeam, season, team);
        EdgeLabel teamToStadium = management.makeEdgeLabel(Branch.TEAM_TO_STADIUM).multiplicity(Multiplicity.MANY2ONE).make();
        management.addConnection(teamToStadium, team, stadium);
    }


    public void dropPreviousGraph() throws Exception {
        JanusGraph graph_old = JanusGraphFactory.open("conf/janusgraph-cassandra-elasticsearch.properties");
        if (graph_old != null) {
            JanusGraphFactory.drop(graph_old);
        }
    }

    private static void buildCompositeIndex(JanusGraphManagement mgmt) {
        for (String index : CompositeIndex.INDICES) {
            mgmt.buildIndex(index, Vertex.class).addKey(mgmt.getPropertyKey(index.toLowerCase()
                    .replace("by", " "))).unique().buildCompositeIndex();
        }
    }

    public void createSchema(final JanusGraphManagement management) {
        LOGGER.info("creating schema");
        createLabelsAndConstraints(management);
        buildCompositeIndex(management);
        management.commit();
    }


}

