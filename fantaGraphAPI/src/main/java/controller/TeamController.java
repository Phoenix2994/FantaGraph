package controller;

import dao.TeamDAO;
import mapper.TeamMapper;
import model.Team;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TeamController extends Controller {
    TeamMapper mapper = new TeamMapper();
    TeamDAO dao = new TeamDAO();


    @RequestMapping(value = "/team/", method = RequestMethod.GET)
    public List<Team> getAllTeams() {
        List<Path> paths = dao.getTeamsPaths();
        List<Team> result = new ArrayList<>();

        for(Path temp : paths){
            Vertex team = temp.get("team");
            Vertex coach = temp.get("coach");
            Vertex president = temp.get("president");
            Vertex stadium = temp.get("stadium");
            List<Object> birthdateList = dao.getListInValues(team,"plays for", "birthdate");
            result.add(mapper.VertexToModel(team,coach,president,stadium,birthdateList));
        }
        return result;
    }

    @RequestMapping(value = "/team/{id}", method = RequestMethod.GET)
    public Team getTeamById(@PathVariable long id) throws java.lang.IllegalStateException {
        Path p = dao.getTeamPathById(id);

        Vertex team = p.get("team");
        Vertex coach = p.get("coach");
        Vertex president = p.get("president");
        Vertex stadium = p.get("stadium");
        List<Object> list = dao.getListInValues(team,"plays for", "birthdate");

        return mapper.VertexToModel(team,coach,president,stadium,list);
    }

}
