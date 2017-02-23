/**
 * Created by Mihai on 23.02.2017.
 */
public class Cache {
    Integer id;
    Integer size;

    public Cache(Integer id) {
        this.id = id;
    }

    public Cache(Integer id, Integer size) {
        this.id = id;
        this.size = size;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
