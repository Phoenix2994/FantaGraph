package controller;

import dao.PresidentDAO;
import mapper.PresidentMapper;
import model.President;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PresidentController extends Controller {
    PresidentMapper mapper = new PresidentMapper();
    PresidentDAO dao = new PresidentDAO();

    @RequestMapping(value = "/president/", method = RequestMethod.GET)
    public List<President> getAllPresidents(){
        List<Path> paths = dao.getPresidentsPaths();
        List<President> result = new ArrayList<>();

        for(Path temp : paths){
            Vertex president = temp.get("president");
            Vertex team = temp.get("team");
            result.add(mapper.VertexToEntity(president,team));
        }
        return result;
    }

    @RequestMapping(value = "/president/{id}", method = RequestMethod.GET)
    public President getPresidentById(@PathVariable long id){
        Path p = dao.getPresidentPathById(id);
        Vertex president = p.get("president");
        Vertex team = p.get("team");

        return mapper.VertexToEntity(president, team);
    }
}
