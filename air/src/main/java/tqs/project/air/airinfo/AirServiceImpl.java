package tqs.project.air.airinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Service
public class AirServiceImpl implements AirService {

    @Value("${breezometer.api}")
    private String url;

    @Value("${breezometer.key}")
    private String key;

    @Value("${breezometer.features}")
    private String features;

    @Autowired
    private AirRepository airRepository;

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    protected String sendGET(String lat, String lon) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(
                        "https://api.breezometer.com/air-quality/v2/current-conditions?"+
                        "lat="+lat +
                        "&lon="+lon +
                        "&key="+"f8e686b5d7e145b1b64752921eb03f25"+
                        "&features=" + "breezometer_aqi,local_aqi,health_recommendations,sources_and_effects,pollutants_concentrations,pollutants_aqi_information"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println(response.statusCode());

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
                System.out.println("Mission failed");
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
