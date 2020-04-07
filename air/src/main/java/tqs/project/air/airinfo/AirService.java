package tqs.project.air.airinfo;

import java.util.Date;

public interface AirService {
    public AirRequest getAirQualityByLocal(float lon, float lat, String[] features);

}
