package tqs.project.air.airinfo;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AirRestController {
    @Autowired
    private AirService airService;

    @GetMapping("/api/currentbreeze")

}
