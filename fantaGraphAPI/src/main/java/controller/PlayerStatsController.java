package controller;

import dao.BaseDAO;
import mapper.PlayerStatsMapper;
import model.PlayerStats;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerStatsController extends Controller {
    private BaseDAO dao = new BaseDAO();
    private PlayerStatsMapper mapper = new PlayerStatsMapper();

    public PlayerStats[] getStatsByPlayerId(long id){

        return null;
    }

    public PlayerStats[] getStatsFromPlayerVertex(Vertex player){
        List<Vertex> list = dao.getListOutVertex(player,"stats");
        PlayerStats[] result = new PlayerStats[list.size()];

        for (int i = 0; i < list.size(); i++) {
            Vertex v = list.get(i);
            Vertex team;
            try{
                team = dao.getOutVertex(v, "stats");
            } catch (Exception e){
                team = null;
            }
            result[i] = mapper.VertexToStats(list.get(i), team);
        }
        dao.commit();
        return result;
    }
}
