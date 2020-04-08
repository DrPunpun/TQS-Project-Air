package tqs.project.air.airinfo;


public interface AirService {
    AirRequest getAirQualityByLocal(double lon, double lat, String[] features);
    int getMisses();
    int getHits();
    int getRequests();
    void clearCache();
}
