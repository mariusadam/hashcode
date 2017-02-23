/**
 * Created by glinut on 2/23/2017.
 */
public class Request {
    public Integer endpoint;
    public Integer reqNr;
    public Integer video;
    public Cache cl_cache;

    public Request(Integer video, Integer endpoint, Integer reqNr) {
        this.endpoint = endpoint;
        this.reqNr = reqNr;
        this.video = video;
        this.cl_cache = null;
    }
}
