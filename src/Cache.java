import java.util.ArrayList;

/**
 * Created by Mihai on 23.02.2017.
 */
public class Cache {
    Integer id;
    Integer size;
    ArrayList<Video> videos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cache cache = (Cache) o;

        return id.equals(cache.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Cache(Integer id) {
        this.id = id;
    }

    public Cache(Integer id, Integer size) {
        this.id = id;
        this.size = size;
        this.videos = new ArrayList<>();
    }

}
