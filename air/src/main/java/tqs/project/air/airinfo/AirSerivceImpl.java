package tqs.project.air.airinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirSerivceImpl implements AirService {
    @Autowired
    private AirRepository airRepository;



}
