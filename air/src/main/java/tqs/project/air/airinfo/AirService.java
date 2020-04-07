package tqs.project.air.airinfo;


public interface AirService {
    AirRequest getAirQualityByLocal(float lon, float lat, String[] features);

}
