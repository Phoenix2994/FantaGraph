package controller;

import dao.TeamDAO;
import mapper.TeamMapper;
import model.Team;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.web.bind.annotation.*;
import utility.labels.Branch;
import utility.labels.Node;
import utility.labels.Property;

import java.util.List;

@RestController
public class TeamController extends Controller {
    TeamMapper mapper = new TeamMapper();
    TeamDAO dao = new TeamDAO();

    public TeamController(){
    }

    @RequestMapping(value = "/team/", method = RequestMethod.GET)
    public Team[] getAllTeams() {
        List<Object> list = dao.getIdList();

        Team[] allTeams = new Team[list.size()];
        for(int i = 0; i < list.size(); i++){
            allTeams[i] = getTeamById(Long.parseLong(String.valueOf(list.get(i))));
        }
        return allTeams;
    }

    @RequestMapping(value = "/team/{id}", method = RequestMethod.GET)
    public Team getTeamById(@PathVariable long id) throws java.lang.IllegalStateException {
        Path p = dao.getTeamPathById(id);

        Vertex team = p.get(Node.TEAM);
        Vertex coach = p.get(Node.COACH);
        Vertex president = p.get(Node.PRESIDENT);
        Vertex stadium = p.get(Node.STADIUM);
        List<Object> list = dao.getListInValues(team, Branch.PLAYER_TO_TEAM, Property.BIRTHDATE[0]);

        return mapper.VertexToTeam(team,coach,president,stadium,list);
    }

}
