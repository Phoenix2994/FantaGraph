package controller;

import dao.UserDAO;
import mapper.UserMapper;
import model.User;
import model.FantaTeam;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController extends Controller {

    private UserDAO dao = new UserDAO();
    private UserMapper mapper = new UserMapper();


    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public User[] getUsers(){
        List<Object> list = dao.getIdList();

        User[] result = new User[list.size()];
        for(int i = 0; i < list.size(); i++){
            result[i] = getUserById(Long.parseLong(String.valueOf(list.get(i))));
        }
        return result;
    }

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public long createUser(@RequestParam(value="username") String username, @RequestParam(value="email", required = true) String email){
        Vertex user = dao.addUser(username, email);

        return mapper.mapUserId(user);
    }


    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable long id){
        Vertex user = dao.getVertexById("user id", id);
        List<Vertex> fantaTeamList = dao.getListOutVertex(user,"fanta owns");
        List<List<Object>> birthdateList = new ArrayList<>();

        for(Vertex temp : fantaTeamList){
            birthdateList.add(dao.getListInValues(temp,"fanta plays for","birthdate"));
        }

        return mapper.VertexToEntity(user, fantaTeamList, birthdateList);
    }

}
