package controller;

import dao.FantaTeamDAO;
import mapper.FantaTeamMapper;
import model.FantaTeam;
import model.Player;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.web.bind.annotation.*;
import utility.labels.Branch;
import utility.labels.Node;
import utility.labels.Property;

import java.util.List;



@RestController
public class FantaTeamController extends Controller{

    private FantaTeamMapper mapper = new FantaTeamMapper();
    private FantaTeamDAO dao = new FantaTeamDAO();


    public FantaTeam[] getFantaTeamsByIdList(List<Object> list){
        FantaTeam[] result = new FantaTeam[list.size()];

        for(int i = 0; i < list.size(); i++){
            result[i] = this.getFantaTeamById(Long.parseLong(String.valueOf(list.get(i))));
        }
        return result;
    }

    @RequestMapping(value = "/fantateam/", method = RequestMethod.GET)
    public FantaTeam[] getAllFantaTeams(){
        List<Object> list = dao.getIdList();

        return getFantaTeamsByIdList(list);
    }

    @RequestMapping(value = "/fantateam/", method = RequestMethod.POST)
    public long createFantaTeam(@RequestParam(value= "name") String name, @RequestParam(value="userId") long userId ) throws Exception {
        try{
            Vertex fantaTeam = dao.addFantaTeam(userId, name);
            dao.commit();

            return mapper.mapFantaTeamId(fantaTeam);
        } catch (Exception e){
            System.out.println("Fantateam already exists!");
            return -1;
        }
    }

    @RequestMapping(value = "/fantateam/", method = RequestMethod.DELETE)
    public boolean deleteFantaTeam(){
        dao.removeAllFantaTeams();
        dao.commit();
        return true;
    }

    @RequestMapping(value = "/fantateam/{id}", method = RequestMethod.GET)
    public FantaTeam getFantaTeamById(@PathVariable long id ){
        Path p = dao.getFantaTeamPathById(id);
        Vertex team = p.get(Node.TEAM);
        Vertex user = p.get(Node.USER);
        List<Object> list = dao.getListInValues(team, Branch.PLAYER_TO_FANTATEAM, Property.BIRTHDATE[0]);

        return mapper.VertexToFantaTeam(team,user,list);
    }

    @RequestMapping(value = "/fantateam/{id}", method = RequestMethod.DELETE)
    public boolean deleteFantaTeamById(@PathVariable long id ){
        dao.removeFantaTeam(id);
        dao.commit();
        return true;
    }


    @RequestMapping(value = "/fantateam/{teamId}/player/", method = RequestMethod.GET)
    public Player[] getPlayers(@PathVariable long teamId ){
        List<Object> list = dao.getPlayersIdList(teamId);

        PlayerController pc = new PlayerController();
        Player[] result = new Player[list.size()];
        for(int i = 0; i < list.size(); i++){
            result[i] = pc.getPlayerById(Long.parseLong(String.valueOf(list.get(i))),true);
        }
        return result;
    }

    @RequestMapping(value = "/fantateam/{teamId}/player", method = RequestMethod.POST)
    public boolean addPlayer(@RequestParam(value="playerId") long playerId, @PathVariable long teamId ) {
        boolean result = dao.addPlayerToFantaTeam(teamId,playerId);
        dao.commit();

        return result;
    }

    @RequestMapping(value = "/fantateam/{teamId}/player/{playerId}", method = RequestMethod.DELETE)
    public boolean removePlayer(@PathVariable long playerId, @PathVariable long teamId ) {
        dao.removePlayerFromFantaTeam(teamId, playerId);
        dao.commit();

        return true;
    }

}
