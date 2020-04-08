package tqs.project.air.airinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class AirRestController {
    @Autowired
    private AirService airService;

    @GetMapping("/breeze")
    public AirRequest getAirRequest(@RequestParam double lon, @RequestParam double lat, @RequestParam String features){
        return airService.getAirQualityByLocal(lat, lon, features.split(","));
    }
    @GetMapping("/cache")
    public HashMap<String, String> getCache(){
        HashMap<String, String> cache = new HashMap<>();
        cache.put("Requests", ""+airService.getRequests());
        cache.put("Hits", ""+airService.getHits());
        cache.put("Misses", ""+airService.getMisses());
        return cache;
    }

    @GetMapping("/clear")
    public void clearCache(){
        airService.clearCache();
    }
}
