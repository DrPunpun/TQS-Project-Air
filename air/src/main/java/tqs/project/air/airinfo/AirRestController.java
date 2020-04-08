package tqs.project.air.airinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AirRestController {
    @Autowired
    private AirService airService;

    @GetMapping("/breeze")
    public AirRequest getAirRequest(@RequestParam float lon, @RequestParam float lat, @RequestParam String features){
        return airService.getAirQualityByLocal(lon, lat, features.split(","));
    }
}
