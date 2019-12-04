package controller;

import dao.PresidentDAO;
import mapper.PresidentMapper;
import model.President;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.labels.Node;
import utility.labels.Property;

import java.time.LocalDate;
import java.util.List;

@RestController
public class PresidentController extends Controller {
    PresidentMapper mapper = new PresidentMapper();
    PresidentDAO dao = new PresidentDAO();
    public PresidentController(){
    }

    @RequestMapping(value = "/president/", method = RequestMethod.GET)
    public President[] getAllPresidents(){
        List<Object> list = dao.getIdList(Property.PRESIDENT_ID[0]);

        President[] result = new President[list.size()];
        for(int i = 0; i < list.size(); i++){
            result[i] = this.getPresidentById(Long.parseLong(String.valueOf(list.get(i))));
        }
        return result;
    }

    @RequestMapping(value = "/president/{id}", method = RequestMethod.GET)
    public President getPresidentById(@PathVariable long id){
        Path p = dao.getPresidentPathById(id);
        Vertex president = p.get(Node.PRESIDENT);
        Vertex team = p.get(Node.TEAM);

        return mapper.VertexToEntity(president, team);
    }
}
