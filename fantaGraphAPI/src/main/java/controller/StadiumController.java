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
import utility.labels.Node;
import utility.labels.Property;

import java.util.List;

@RestController
public class StadiumController extends Controller{

    private StadiumDAO dao = new StadiumDAO();
    private StadiumMapper mapper = new StadiumMapper();


    @RequestMapping(value = "/stadium/", method = RequestMethod.GET)
    public Stadium[] getAllStadiums(){
        List<Object> list = dao.getIdList(Property.STADIUM_ID[0]);

        Stadium[] result = new Stadium[list.size()];

        for(int i = 0; i < list.size(); i++){
            result[i] = this.getStadiumById(Long.parseLong(String.valueOf(list.get(i))));
        }
        return result;
    }

    @RequestMapping(value = "/stadium/{id}", method = RequestMethod.GET)
    public Stadium getStadiumById(@PathVariable long id){
        Path p = dao.getStadiumPathById(id);
        Vertex stadium = p.get(Node.STADIUM);
        Vertex team = p.get(Node.TEAM);

        return mapper.VertexToEntity(stadium,team);
    }
}
