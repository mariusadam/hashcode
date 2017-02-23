import java.util.HashMap;

/**
 * Created by Mihai on 23.02.2017.
 */
public class EndPoint {
    Integer id;
    Integer dataCenterLatency;
    HashMap<Cache, Integer> latencys;

    public EndPoint(Integer id, Integer dataCenterLatency) {
        this.id = id;
        this.dataCenterLatency = dataCenterLatency;
        latencys = new HashMap<>();
    }
}
