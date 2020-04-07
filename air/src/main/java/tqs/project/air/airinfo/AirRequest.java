package tqs.project.air.airinfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.jni.Poll;
import tqs.project.air.airinfo.qualitymetrics.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AirRequest {
    private long requestDate;
    private BAQI baqi;
    private Map<String, Pollutants> list_of_pollutants;
    private ObjectMapper objectMapper;


    public AirRequest(String data) {
        this.requestDate = System.currentTimeMillis();
        objectMapper = new ObjectMapper();
        list_of_pollutants = new HashMap<>();
        this.parseData(data);
    }

    public BAQI getBaqi() {
        return baqi;
    }

    public Pollutants getAirMetric(String metric) {return list_of_pollutants.get(metric);}

    public Pollutants excludeAirMetric(String metric) {return list_of_pollutants.remove(metric);}

    protected void parseData(String data){
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ;
        }
        JsonNode baqiNode = jsonNode.get("data").get("indexes").get("baqi");
        this.baqi = new BAQI(baqiNode.get("aqi").asInt(), baqiNode.get("color").asText(), baqiNode.get("category").asText());

        JsonNode pollutants = jsonNode.get("data").get("pollutants");

        JsonNode co = pollutants.get("co");
        JsonNode coBAQINode = co.get("aqi_information").get("baqi");
        BAQI coBAQI = new BAQI(coBAQINode.get("aqi").asInt(), coBAQINode.get("color").asText(), coBAQINode.get("category").asText());
        list_of_pollutants.put(
                "co", new CO(coBAQI, co.get("concentration").get("value").asDouble())
        );

        JsonNode no2 = pollutants.get("no2");
        JsonNode no2BAQINode = no2.get("aqi_information").get("baqi");
        BAQI no2BAQI = new BAQI(no2BAQINode.get("aqi").asInt(), no2BAQINode.get("color").asText(), no2BAQINode.get("category").asText());
        list_of_pollutants.put(
                "no2", new NO2(no2BAQI, no2.get("concentration").get("value").asDouble())
        );

        JsonNode o3 = pollutants.get("o3");
        JsonNode o3BAQINode = o3.get("aqi_information").get("baqi");
        BAQI o3BAQI = new BAQI(o3BAQINode.get("aqi").asInt(), o3BAQINode.get("color").asText(), o3BAQINode.get("category").asText());
        list_of_pollutants.put(
                "o3", new O3(o3BAQI, o3.get("concentration").get("value").asDouble())
        );

        JsonNode pm10 = pollutants.get("pm10");
        JsonNode pm10BAQINode = pm10.get("aqi_information").get("baqi");
        BAQI pm10BAQI = new BAQI(pm10BAQINode.get("aqi").asInt(), pm10BAQINode.get("color").asText(), pm10BAQINode.get("category").asText());
        list_of_pollutants.put(
                "pm10", new PM10(pm10BAQI, pm10.get("concentration").get("value").asDouble())
        );

        JsonNode pm25 = pollutants.get("pm25");
        JsonNode pm25BAQINode = pm25.get("aqi_information").get("baqi");
        BAQI pm25BAQI = new BAQI(pm25BAQINode.get("aqi").asInt(), pm25BAQINode.get("color").asText(), pm25BAQINode.get("category").asText());
        list_of_pollutants.put(
                "pm25", new PM25(pm25BAQI, pm25.get("concentration").get("value").asDouble())
        );

        JsonNode so2 = pollutants.get("so2");
        JsonNode so2BAQINode = so2.get("aqi_information").get("baqi");
        BAQI so2BAQI= new BAQI(so2BAQINode.get("aqi").asInt(), so2BAQINode.get("color").asText(), so2BAQINode.get("category").asText());
        list_of_pollutants.put(
                "so2", new SO2(so2BAQI, so2.get("concentration").get("value").asDouble())
        );

    }

    public long getRequestDate(){
        return this.requestDate;
    }

    public void setRequestDate(long requestDate){
        this.requestDate = requestDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AirRequest)) return false;
        AirRequest that = (AirRequest) o;
        return  Objects.equals(baqi, that.baqi) &&
                Objects.equals(list_of_pollutants, that.list_of_pollutants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestDate, baqi, list_of_pollutants, objectMapper);
    }
}
