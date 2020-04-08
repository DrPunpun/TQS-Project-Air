package tqs.project.air.airinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Service
public class AirServiceImpl implements AirService {

    @Value("${breezometer.api}")
    private static String url;

    @Value("${breezometer.key}")
    private static String key;

    @Value("${breezometer.features}")
    private static String features;

    @Autowired
    private AirRepository airRepository;

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    protected String sendGET(String lat, String lon) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(
                        url+
                        "lat="+lat +
                        "&lon="+lon +
                        "&key="+key+
                        "&features=" + features))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print response body
        return response.body();
    }

    @Override
    public AirRequest getAirQualityByLocal(float lat, float lon, String[] features) {
        AirRequest airRequest = airRepository.getData(lon, lat);
        if (airRequest == null){
            try {
                String getResult = sendGET("" + lat, "" + lon);
                airRequest = new AirRequest(getResult);

                this.airRepository.putData(lat, lat, getResult);
            } catch (Exception e){
                return null;
            }
        }

        List<String> allFeatures = Arrays.asList("co", "no2", "o3", "pm10", "pm25", "so2");
        List<String> featuresList = Arrays.asList(features);

        for (String feature: allFeatures) {
            if (!featuresList.contains(feature)) {
                airRequest.excludeAirMetric(feature);
            }
        }

        return airRequest;
    }

}
