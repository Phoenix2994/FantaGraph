package population;

import dao.*;
import schema.SchemaBuilder;
import model.Team;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.labels.Branch;
import utility.labels.Node;
import utility.labels.Property;


import java.io.File;
import java.io.FileReader;
import java.net.URL;

public class PopulationBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchemaBuilder.class);

    private static void createPlayersVertices(GraphTraversalSource g) {
        JSONParser parser = new JSONParser();
        PlayerDAO playerDAO = new PlayerDAO();
        PlayerStatsDAO playerStatsDAO = new PlayerStatsDAO();
        ProsecutorDAO prosecutorDAO = new ProsecutorDAO();

        long prosecutor_counter = 0;
        try {
            Object obj = parser.parse(new FileReader(new PopulationBuilder().
                    getFileFromResources("players.txt")));
            JSONObject jsonObject = (JSONObject) obj;
            for (Object key : jsonObject.keySet()) {

                //GET Player Infos from file
                JSONObject player_info = (JSONObject) jsonObject.get(key);
                String name = (String) player_info.get("name");
                String team_name = (String) player_info.get("team");
                String player_place = (String) player_info.get("birthplace");
                String player_data = (String) player_info.get("birthdate");
                String player_nat = (String) player_info.get("nationality");
                String player_height = (String) player_info.get("height");
                String player_role = (String) player_info.get("role");
                String player_foot = (String) player_info.get("mainFoot");
                String player_img = (String) player_info.get("img");
                long player_id = Long.parseLong((String) player_info.get("id"));
                Long player_quot = (Long) player_info.get("quot");
                JSONObject player_stats = (JSONObject) player_info.get("stats");
                String prosecutor_name = (String) player_info.get("prosecutor");

                // ADD Player vertex
                final Vertex player = playerDAO.addPlayer(name, player_data, player_place, player_nat, player_height, player_role, player_foot,
                        player_img, player_id, player_quot);

                // ADD Player-Team edge
                Vertex team = g.V().hasLabel(Node.TEAM).has(Property.NAME[0], (team_name + " ")
                        .split(" ")[0].toUpperCase()).next();

                playerDAO.addTeamToPlayer(player, team);

                // GET player stats from file
                for (Object season_key : player_stats.keySet()) {
                    JSONObject season_stats = (JSONObject) player_stats.get(season_key);
                    if (season_stats.containsKey("team")) {
                        String season_year = season_key.toString().replace("stagione", "");
                        String role = (String) season_stats.get("r");
                        String playing_team = ((String) season_stats.get("team")).toUpperCase();
                        Long played_matches = (Long) season_stats.get("pg");
                        Double avg_rating = ((Number) season_stats.get("mv")).doubleValue();
                        Double avg_fantarating = ((Number) season_stats.get("mf")).doubleValue();
                        Double avg_gaussian_rating = ((Number) season_stats.get("mvt")).doubleValue();
                        Double avg_gaussian_fantarating = ((Number) season_stats.get("mft")).doubleValue();
                        Long scored_goals = (Long) season_stats.get("gf");
                        Long conceded_goals = (Long) season_stats.get("gs");
                        Long assists = (Long) season_stats.get("ass");
                        Long yellow_cards = (Long) season_stats.get("amm");
                        Long red_cards = (Long) season_stats.get("esp");
                        Long own_goals = (Long) season_stats.get("aut");
                        if (g.V().hasLabel(Node.TEAM).has(Property.NAME[0], playing_team)
                                .hasNext()) {
                            Vertex season_team = g.V().hasLabel(Node.TEAM).has(Property.NAME[0],
                                    playing_team).next();

                            LOGGER.info(season_team.toString());

                            //ADD Player Stats vertex and Player-Player Stats edge
                            final Vertex player_season_stats = playerStatsDAO.addPlayerStats(season_year, role, played_matches, scored_goals, conceded_goals,
                                    own_goals, assists, yellow_cards, red_cards, avg_rating,
                                    avg_fantarating, avg_gaussian_rating, avg_gaussian_fantarating, season_team, playing_team);

                            playerDAO.addStatsToPlayer(player, player_season_stats);
                        } else {
                            final Vertex player_season_stats = playerStatsDAO.addPlayerStats(season_year, role, played_matches, scored_goals, conceded_goals,
                                    own_goals, assists, yellow_cards, red_cards, avg_rating,
                                    avg_fantarating, avg_gaussian_rating, avg_gaussian_fantarating, null, playing_team);

                            playerDAO.addStatsToPlayer(player, player_season_stats);
                        }
                    }
                }
                // Check and ADD Prosecutor vertex
                boolean exist = g.V().hasLabel(Node.PROSECUTOR).has(Property.NAME[0], prosecutor_name).hasNext();
                final Vertex prosecutor;
                if (!exist) {
                    prosecutor_counter = prosecutor_counter + 1;
                    prosecutor = prosecutorDAO.addProsecutor(prosecutor_counter, prosecutor_name);

                } else {
                    prosecutor = g.V().hasLabel(Node.PROSECUTOR).has(Property.NAME[0], prosecutor_name).next();
                }
                // ADD Player-Prosecutor edge
                playerDAO.addProsecutorToPlayer(player, prosecutor);

                g.tx().commit();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            g.tx().rollback();
        }
    }

    private static void createInfosVertices(GraphTraversalSource g) {
        JSONParser parser = new JSONParser();
        PresidentDAO presidentDAO = new PresidentDAO();
        StadiumDAO stadiumDAO = new StadiumDAO();
        TeamDAO teamDAO = new TeamDAO();
        CoachDAO coachDAO = new CoachDAO();

        try {
            Object obj = parser.parse(new FileReader(new PopulationBuilder()
                    .getFileFromResources("teams.txt")));
            long team_counter = 0;
            long president_counter = 0;
            long coach_counter = 0;
            long stadium_counter = 0;
            JSONObject jsonObject = (JSONObject) obj;
            for (Object key : jsonObject.keySet()) {
                //GET Team/Coach/President/Stadium from file
                //ADD these vertices and relative edges
                String name = (String) key;
                JSONObject team_info = (JSONObject) jsonObject.get(key);
                String logo = (String) team_info.get("logo");
                team_counter = team_counter + 1;
                final Vertex team = teamDAO.addTeam(name, logo, team_counter);

                //team vertex added to graph
                JSONObject stadium_info = (JSONObject) team_info.get("stadium");
                String stadium_name = (String) stadium_info.get("name");
                String stadium_place = (String) stadium_info.get("city");
                Long stadium_fans = (Long) stadium_info.get("capacity");
                String stadium_img = (String) stadium_info.get("img");
                boolean stadium_exist = g.V().hasLabel(Node.STADIUM).has(Property.NAME[0], stadium_name).hasNext();
                final Vertex stadium;
                if (!stadium_exist) {
                    stadium_counter = stadium_counter + 1;

                    stadium = stadiumDAO.addStadium(stadium_counter, stadium_name, stadium_place, stadium_fans, stadium_img);

                } else {
                    stadium = g.V().hasLabel(Node.STADIUM).has(Property.NAME[0], stadium_name).next();
                }
                boolean league_exist = g.V().hasLabel(Node.LEAGUE)
                        .has(Property.NAME[0], "Serie A TIM").hasNext();
                final Vertex league;
                if (!league_exist) {
                    league = g.addV(Node.LEAGUE).property(Property.NAME[0], "Serie A TIM")
                            .property(Property.COUNTRY[0], "ITALIA").next();
                } else {
                    league = g.V().hasLabel(Node.LEAGUE).has(Property.NAME[0], "Serie A TIM").next();
                }
                //stadium vertex added to graph
                JSONObject president_info = (JSONObject) team_info.get("president");
                String pres_name = (String) president_info.get("name");
                String pres_place = (String) president_info.get("birthplace");
                String pres_data = (String) president_info.get("birthdate");
                String pres_nat = (String) president_info.get("nationality");
                president_counter = president_counter + 1;
                final Vertex president = presidentDAO.addPresident(president_counter, pres_name,
                        pres_data.split(" ")[0], pres_place, pres_nat);

                //president vertex added to graph
                JSONObject coach_info = (JSONObject) team_info.get("coach");
                String coach_name = (String) coach_info.get("name");
                String coach_place = (String) coach_info.get("birthplace");
                String coach_data = (String) coach_info.get("birthdate");
                String coach_nat = (String) coach_info.get("nationality");
                Long coach_schema = (Long) coach_info.get("module");
                coach_counter = coach_counter + 1;
                final Vertex coach = coachDAO.addCoach(coach_name, coach_data, coach_place, coach_nat, coach_schema, coach_counter);
                //coach vertex added to graph
                teamDAO.addStadiumToTeam(team, stadium);

                g.V(team).as("a").V(league).addE(Branch.TEAM_TO_LEAGUE).from("a");
                presidentDAO.addTeamToPresident(president, team);

                coachDAO.addPresidentToCoach(coach, president);
                coachDAO.addTeamToCoach(coach, team);

                g.tx().commit();
            }


        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            g.tx().rollback();
        }
    }

    private File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }

    public void populateGraph(GraphTraversalSource g) {
        createInfosVertices(g);
        createPlayersVertices(g);
    }

}
