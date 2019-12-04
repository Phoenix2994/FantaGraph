package controller;

import dao.PlayerDAO;
import mapper.PlayerMapper;
import model.Player;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.web.bind.annotation.*;
import utility.labels.Branch;
import utility.labels.Node;

import java.util.List;

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
    public Player[] getAllPlayers(@RequestParam(required = false) boolean showStats){
        List<Object> list = dao.getIdList();
        return getPlayersByIdList(list);
    }


    @RequestMapping(value = "/player/{id}", method = RequestMethod.GET)
    public Player getPlayerById(@PathVariable long id, @RequestParam(required = false) boolean showStats) throws IllegalStateException {
        Path p = dao.getPlayerPathById(id);

        Vertex player = p.get(Node.PLAYER);
        Vertex team = p.get(Node.TEAM);
        Vertex prosecutor = p.get(Node.PROSECUTOR);
        List<Vertex> statList;

        if (!showStats) {
            statList = null;
        } else {
            statList = dao.getListOutVertex(player, Branch.PLAYER_TO_SEASON);
        }

        return mapper.VertexToPlayer(player, statList, team, prosecutor, showStats);
    }
}
