package controller;

import dao.FantaTeamDAO;
import mapper.FantaTeamMapper;
import mapper.PlayerMapper;
import model.FantaTeam;
import model.Player;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public List<FantaTeam> getAllFantaTeams(){
        List<Path> paths = dao.getAllFantaTeamsPaths();
        List<FantaTeam> result = new ArrayList<>();

        for(Path temp : paths){
            Vertex team = temp.get("team");
            Vertex user = temp.get("user");
            List<Object> list = dao.getListInValues(team,"fanta plays for", "birthdate");
            result.add(mapper.VertexToModel(team,user,list));
        }
        return result;
    }

    @RequestMapping(value = "/fantateam/", method = RequestMethod.POST)
    public long createFantaTeam(@RequestParam(value="name") String name, @RequestParam(value="userId") long userId ) throws Exception {
        try{
            Vertex fantaTeam = dao.addFantaTeam(userId, name);

            return mapper.mapFantaTeamId(fantaTeam);
        } catch (Exception e){
            System.out.println("Fantateam already exists!");
            return -1;
        }
    }

    @RequestMapping(value = "/fantateam/", method = RequestMethod.DELETE)
    public boolean deleteFantaTeam(){
        dao.removeAllFantaTeams();

        return true;
    }

    @RequestMapping(value = "/fantateam/{id}", method = RequestMethod.GET)
    public FantaTeam getFantaTeamById(@PathVariable long id ){
        Path p = dao.getFantaTeamPathById(id);

        Vertex team = p.get("team");
        Vertex user = p.get("user");
        List<Object> list = dao.getListInValues(team,"fanta plays for", "birthdate");

        return mapper.VertexToModel(team,user,list);
    }

    @RequestMapping(value = "/fantateam/{id}", method = RequestMethod.DELETE)
    public boolean deleteFantaTeamById(@PathVariable long id ){
        dao.removeFantaTeam(id);
        return true;
    }


    @RequestMapping(value = "/fantateam/{teamId}/player/", method = RequestMethod.GET)
    public List<Player> getPlayers(@PathVariable long teamId ){
        List<Path> paths = dao.getPlayersPaths(teamId);
        List<Player> result = new ArrayList<>();
        PlayerMapper playerMapper = new PlayerMapper();

        for(Path temp : paths){
            Vertex player = temp.get("player");
            Vertex team = temp.get("team");
            Vertex prosecutor = temp.get("prosecutor");
            result.add(playerMapper.VertexToModel(player,null,team,prosecutor,false));
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
