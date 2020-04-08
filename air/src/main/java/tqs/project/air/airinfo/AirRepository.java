package tqs.project.air.airinfo;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class AirRepository {
    protected HashMap<AirCoord, AirRequest> cache;
    private int ttl;
    private int miss;
    private int hit;

    public AirRepository(int ttl){
        this.ttl = ttl;
        this.cache = new HashMap<>();
        this.miss = 0;
        this.hit = 0;
    }

    public AirRepository() {
        this(60000);
    }

    public AirRequest getData(double lat, double lon){
        AirCoord air = new AirCoord(lat, lon);

        if (cache.containsKey(air) && System.currentTimeMillis() < cache.get(air).getRequestDate() + this.ttl){
            this.hit++;
            return cache.get(air);
        }
        return null;
    }

    public int getMiss() {
        return miss;
    }

    public int getHit() {
        return hit;
    }

    public void clear(){
        this.cache.clear();
        this.miss = 0;
        this.hit = 0;
    }

    public AirRequest putData(double lat, double lon, String data){
        this.miss++;
        AirRequest airRequest = new AirRequest(data);
        this.cache.put(new AirCoord(lat, lon), airRequest);
        return airRequest;
    }
}
