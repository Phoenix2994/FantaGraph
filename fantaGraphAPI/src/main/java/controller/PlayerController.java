package controller;

import dao.PlayerDAO;
import mapper.PlayerMapper;
import model.Player;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PlayerController extends Controller{

    PlayerMapper mapper = new PlayerMapper();
    PlayerDAO dao = new PlayerDAO();

    public Player[] getPlayersByIdList(List<Object> list){
        Player[] result = new Player[list.size()];
        for(int i = 0; i < list.size(); i++){
            result[i] = getPlayerById(Long.parseLong(String.valueOf(list.get(i))),false);
        }
        return result;
    }

    @RequestMapping(value = "/player/", method = RequestMethod.GET)
    public List<Player> getAllPlayers(@RequestParam(required = false) boolean showStats){
        List<Path> paths = dao.getAllPlayersPaths();
        List<Player> result = new ArrayList<>();

        for(Path temp : paths){
            Vertex player = temp.get("player");
            Vertex team = temp.get("team");
            Vertex prosecutor = temp.get("prosecutor");
            result.add(mapper.VertexToModel(player,null,team,prosecutor,false));
        }
        return result;
    }


    @RequestMapping(value = "/player/{id}", method = RequestMethod.GET)
    public Player getPlayerById(@PathVariable long id, @RequestParam(required = false) boolean showStats) throws IllegalStateException {
        Path p = dao.getPlayerPathById(id);

        Vertex player = p.get("player");
        Vertex team = p.get("team");
        Vertex prosecutor = p.get("prosecutor");
        List<Vertex> statList;

        if (!showStats) {
            statList = null;
        } else {
            statList = dao.getListOutVertex(player,"stats");
        }

        return mapper.VertexToModel(player, statList, team, prosecutor, showStats);
    }
}
