package controller;

import mapper.CoachMapper;
import model.Coach;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.labels.Branch;
import utility.labels.Node;
import utility.labels.Property;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CoachController extends Controller {

    private CoachMapper mapper = new CoachMapper();

    public CoachController(){
    }

    @RequestMapping(value = "/coach/", method = RequestMethod.GET)
    public Coach[] getAllCoaches(){
        List<Object> list = this.g.V().has(Property.COACH_ID[0]).order().by(Property.NAME[0]).values(Property.COACH_ID[0]).toList();

        Coach[] allCoaches = new Coach[list.size()];
        for(int i = 0; i < list.size(); i++){
            allCoaches[i] = getCoachById(Long.parseLong(String.valueOf(list.get(i))));
        }
        return allCoaches;
    }

    @RequestMapping(value = "/coach/{id}", method = RequestMethod.GET)
    public  Coach getCoachById(@PathVariable long id){
        Path p = this.g.V().has(Property.COACH_ID[0], id).as(Node.COACH)
                .out(Branch.COACH_TO_TEAM).as(Node.TEAM).path().next();

        Vertex coach = p.get(Node.COACH);
        Vertex team = p.get(Node.TEAM);

        return mapper.VertexToEntity(coach,team);
    }
}
