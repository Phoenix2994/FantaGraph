package controller;

import dao.ProsecutorDAO;
import mapper.PlayerMapper;
import mapper.ProsecutorMapper;
import model.Player;
import model.Prosecutor;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProsecutorController extends Controller {

    private ProsecutorMapper mapper = new ProsecutorMapper();
    private ProsecutorDAO dao = new ProsecutorDAO();


    @RequestMapping(value = "/prosecutor/", method = RequestMethod.GET)
    public List<Prosecutor> getAllProsecutors(){
        List<Vertex> list = dao.getVertexListByProperty("prosecutor id");
        List<Prosecutor> result = new ArrayList<>();

        for(Vertex temp : list){
            result.add(mapper.VertexToEntity(temp));
        }
        return result;
    }

    @RequestMapping(value = "/prosecutor/{id}", method = RequestMethod.GET)
    public Prosecutor getProsecutorById(@PathVariable long id){
        Vertex prosecutor = dao.getVertexById("prosecutor id", id);

        return mapper.VertexToEntity(prosecutor);
    }

    @RequestMapping(value = "/prosecutor/{id}/player/", method = RequestMethod.GET)
    public List<Player> getAssistedPlayers(@PathVariable long id){
        Vertex prosecutor = dao.getVertexById("prosecutor id",id);
        List<Path> paths = dao.getPlayersPaths(id);
        List<Player> result = new ArrayList<>();
        PlayerMapper playerMapper = new PlayerMapper();
        for(Path temp : paths){
            Vertex player = temp.get("player");
            Vertex team = temp.get("team");
            result.add(playerMapper.VertexToModel(player,null,team,prosecutor,false));
        }
        return result;
    }

}
