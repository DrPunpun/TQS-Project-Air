package tqs.project.air.airinfo;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class AirRepository {
    protected HashMap<AirCoord, AirRequest> cache;
    private int ttl;

    public AirRepository() {
        this.ttl = 500;
        this.cache = new HashMap<>();
    }

    public String getData(double lon, double lat){
        return "";
    }

    public List<AirCoord> getMostPopular(){
        return this.getMostPopular(this.cache.size());
    }

    public List<AirCoord> getMostPopular(int n){
        return null;
    }
}
