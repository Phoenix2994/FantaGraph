package controller;


import dao.StadiumDAO;
import mapper.StadiumMapper;
import model.Stadium;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StadiumController extends Controller{

    private StadiumDAO dao = new StadiumDAO();
    private StadiumMapper mapper = new StadiumMapper();


    @RequestMapping(value = "/stadium/", method = RequestMethod.GET)
    public List<Stadium> getAllStadiums(){
        List<Path> paths = dao.getStadiumsPaths();
        List<Stadium> result = new ArrayList<>();

        for(Path temp : paths){
            Vertex stadium = temp.get("stadium");
            Vertex team = temp.get("team");
            result.add(mapper.VertexToEntity(stadium,team));
        }
        return result;
    }

    @RequestMapping(value = "/stadium/{id}", method = RequestMethod.GET)
    public Stadium getStadiumById(@PathVariable long id){
        Path p = dao.getStadiumPathById(id);
        Vertex stadium = p.get("stadium");
        Vertex team = p.get("team");

        return mapper.VertexToEntity(stadium,team);
    }
}
