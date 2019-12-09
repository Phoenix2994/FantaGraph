package mapper;

import controller.PlayerStatsController;
import model.Player;

import model.PlayerStats;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import utility.labels.Property;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.YEARS;

public class PlayerMapper extends Mapper {

    public long mapPlayerId(Vertex player){ return this.mapLong(player, Property.PLAYER_ID); }

    public Player VertexToModel(Vertex player, List<Vertex> statList, Vertex team, Vertex prosecutor, boolean showStats){

        long id = this.mapPlayerId(player);
        String name = this.mapName(player);
        String birthplace = this.mapBirthplace(player);
        LocalDate birthdate = this.mapBirthdate(player);
        String nationality = this.mapNationality(player);
        long age = YEARS.between(birthdate, today);
        String height = this.mapString(player,Property.HEIGHT);
        String position = this.mapString(player,Property.ROLE);
        String mainfoot = this.mapString(player,Property.MAIN_FOOT);
        String img = this.mapImg(player);
        long quot = 0;
        try{
            quot = this.mapLong(player,Property.QUOT);
        }catch (Exception e){
            System.out.println("Error id: " + id);
        }
        String teamName = this.mapName(team);
        long teamId = this.mapLong(team,Property.TEAM_ID);
        String prosecutorName = this.mapName(prosecutor);
        long prosecutorId = this.mapLong(prosecutor,Property.PROSECUTOR_ID);

        PlayerStats[] stats;
        if(showStats){
            PlayerStatsController statsController = new PlayerStatsController();
            stats = statsController.getStatsFromPlayerVertex(player);
        } else {
            stats = null;
        }
        return new Player(id,name,birthplace,birthdate,nationality,age,height,position,mainfoot,stats, quot, teamName, teamId, prosecutorName,prosecutorId,img);
    }
}
