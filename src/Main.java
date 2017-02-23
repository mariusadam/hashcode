import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class Main {
    static HashMap<Integer,Video> videos = new HashMap<>();
    static Map<Integer, EndPoint> endPoints;
    public static Pair getCache(Request request){
        Integer min = Integer.MAX_VALUE;
        Cache c = null;
        Iterator it = endPoints.get(request.endpoint).latencys.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if (((Integer) pair.getValue() < min) && ((Cache)pair.getKey()).size>=videos.get(request.video).size){
                min = (Integer) pair.getValue();
                c = (Cache) pair.getKey();
            }
        }

        return new Pair(min, c);
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new InputStreamReader(new FileInputStream("C:\\Users\\glinut\\Desktop\\trending_today.in")));

        Integer videosNumber = in.nextInt();
        Integer endPointNumber = in.nextInt();
        Integer requestNumber = in.nextInt();
        Integer cacheNumber = in.nextInt();
        Integer cacheSize = in.nextInt();
        HashMap<Integer,Cache> caches = new HashMap<>();


        for (Integer i=0; i<videosNumber; i++){
            videos.put(i,new Video(i,in.nextInt()));
        }

         endPoints = new HashMap<>();

        for (Integer i=0; i<endPointNumber; i++){
            EndPoint e = new EndPoint(i,in.nextInt(), in.nextInt());
            for (Integer j=0; j<e.cacheNumber; j++){
                Cache cache = new Cache(in.nextInt(),cacheSize);
                if (caches.get(cache.id)==null){
                    caches.put(cache.id,cache);
                }
                e.latencys.put(caches.get(cache.id),in.nextInt());

            }
            endPoints.put(e.id,e);
        }

        ArrayList<Request> requests = new ArrayList<>();
        for (Integer i=0; i<requestNumber; i++){
            requests.add(new Request(in.nextInt(),in.nextInt(),in.nextInt()));
        }
        /*
        requests.sort((o1, o2) -> {
            Pair pp1 = getCache(o1);
            Pair pp2 = getCache(o2);
            Integer p1 = endPoints.get(o1.endpoint).dataCenterLatency * o1.reqNr - pp1.l * o1.reqNr;
            Integer p2 = endPoints.get(o2.endpoint).dataCenterLatency * o2.reqNr - pp2.l * o2.reqNr;
            o1.cl_cache = pp1.c;
            o2.cl_cache = pp2.c;
            return p1.compareTo(p2);
        });
        */
        Boolean ok = false;
        Integer cont = 1000;
        while (! ok ){
            if (cont == 0) break;
            cont --;
            ok = true;
            Integer maxx = Integer.MIN_VALUE;
            Request rr = null;
            Integer index = 0;
            int toDel = -1;
            for (Request r: requests){
                if (endPoints.get(r.endpoint).cacheNumber > 0) {
                    Pair pp1 = getCache(r);
                    if (null != pp1.c) {
                        Integer p1 = endPoints.get(r.endpoint).dataCenterLatency * r.reqNr - pp1.l * r.reqNr;
                        if (p1 > maxx) {
                            maxx = p1;
                            rr = r;
                            rr.cl_cache = pp1.c;
                            toDel = index;
                        }
                    }
                }
                index ++;
            }

            if (null != rr){

                rr.cl_cache.videos.add(videos.get(rr.video));
                rr.cl_cache.size -= videos.get(rr.video).size;
                ok = false;
                requests.remove(toDel);
            }
        }

        ArrayList<Cache> ch = new ArrayList<>();
        Iterator it = caches.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Cache ccc = (Cache)pair.getValue();
            if (ccc.size != cacheSize ){
                ch.add(ccc);
            }
        }

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\glinut\\Desktop\\gay.out")));
        out.write(ch.size() + "\n");

        for (Cache c: ch)
        {
            out.write(c.id + " ");
            HashSet<Video> v = new HashSet<>(c.videos);
            for (Video vi:v){
                out.write(vi.id + " ");
            }
            out.write("\n");
        }
            out.close();
            in.close();

    }
}
