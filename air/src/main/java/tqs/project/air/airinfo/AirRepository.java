package tqs.project.air.airinfo;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class AirRepository {
    private HashMap<String, Air> cache;

    public AirRepository() {
        this.cache = new HashMap<>();
    }
}
