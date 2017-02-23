import java.util.HashMap;

/**
 * Created by Mihai on 23.02.2017.
 */
public class EndPoint {
    Integer id;
    Integer dataCenterLatency;
    Integer cacheNumber;
    HashMap<Cache, Integer> latencys = new HashMap<>();


    public EndPoint(Integer id, Integer dataCenterLatency, Integer cacheNumber) {
        this.id = id;
        this.dataCenterLatency = dataCenterLatency;
        this.cacheNumber = cacheNumber;
    }

    public EndPoint(Integer id, Integer dataCenterLatency) {
        this.id = id;
        this.dataCenterLatency = dataCenterLatency;
        latencys = new HashMap<>();
    }
}
