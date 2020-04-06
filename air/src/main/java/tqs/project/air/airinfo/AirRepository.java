package tqs.project.air.airinfo;

import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Repository
public class AirRepository {
    protected HashMap<AirCoord, AirRequest> cache;
    private int ttl;

    public AirRepository(int ttl){
        this.ttl = ttl;
        this.cache = new HashMap<>();
    }

    public AirRepository() {
        this(500);
    }

    public String getData(double lon, double lat){
        AirCoord air = new AirCoord(lon, lat);
        AirRequest airRequest;
        if (cache.containsKey(air)){
            airRequest = cache.get(air);
            if (System.currentTimeMillis() > airRequest.getRequestDate() + this.ttl){
                airRequest.miss();
            } else {
                airRequest.hit();
            }
        } else {
            airRequest = new AirRequest("data");
            this.cache.put(air, airRequest);
        }
                
        return airRequest.getData();
    }

    public List<AirCoord> getMostPopular(){
        return this.getMostPopular(this.cache.size());
    }

    public List<AirCoord> getMostPopular(int n){
        TreeMap<AirCoord, AirRequest> sorted = new TreeMap<>(Comparator.comparing(o -> cache.get(o)).reversed());
        sorted.putAll(this.cache);

        return sorted.keySet().stream().limit(n).collect(Collectors.toList());
    }
}
