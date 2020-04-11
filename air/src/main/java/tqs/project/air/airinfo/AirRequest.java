package tqs.project.air.airinfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import tqs.project.air.airinfo.qualitymetrics.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AirRequest {
    private long requestDate;
    private BAQI baqi;
    private Map<String, Pollutants> listOfPollutants;
    private ObjectMapper objectMapper;


    public AirRequest(String data) {
        this.requestDate = System.currentTimeMillis();
        objectMapper = new ObjectMapper();
        listOfPollutants = new HashMap<>();
        this.parseData(data);
    }

    public BAQI getBaqi() {
        return baqi;
    }

    public Pollutants getAirMetric(String metric) {return listOfPollutants.get(metric);}

    public Pollutants excludeAirMetric(String metric) {return listOfPollutants.remove(metric);}

    private BAQI getPollutantBAQI(JsonNode jn){
        JsonNode jsonNode = jn.get("aqi_information").get("baqi");

        return new BAQI(jsonNode.get("aqi").asInt(), jsonNode.get("color").asText(), jsonNode.get("category").asText());
    }

    private double getPollutantConcentration(JsonNode jn){
        return jn.get("concentration").get("value").asDouble();
    }

    protected void parseData(String data){
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(data);
        } catch (JsonProcessingException e) {
            return ;
        }
        JsonNode baqiNode = jsonNode.get("data").get("indexes").get("baqi");
        this.baqi = new BAQI(baqiNode.get("aqi").asInt(), baqiNode.get("color").asText(), baqiNode.get("category").asText());

        JsonNode pollutants = jsonNode.get("data").get("pollutants");

        JsonNode co = pollutants.get("co");
        BAQI coBAQI = getPollutantBAQI(co);
        listOfPollutants.put(
                "co", new CO(coBAQI, getPollutantConcentration(co))
        );

        JsonNode no2 = pollutants.get("no2");
        BAQI no2BAQI = getPollutantBAQI(no2);
        listOfPollutants.put(
                "no2", new NO2(no2BAQI, getPollutantConcentration(no2))
        );

        JsonNode o3 = pollutants.get("o3");
        BAQI o3BAQI = getPollutantBAQI(o3);
        listOfPollutants.put(
                "o3", new O3(o3BAQI, getPollutantConcentration(o3))
        );

        JsonNode pm10 = pollutants.get("pm10");
        BAQI pm10BAQI = getPollutantBAQI(pm10);
        listOfPollutants.put(
                "pm10", new PM10(pm10BAQI, getPollutantConcentration(pm10))
        );

        JsonNode pm25 = pollutants.get("pm25");
        BAQI pm25BAQI = getPollutantBAQI(pm25);
        listOfPollutants.put(
                "pm25", new PM25(pm25BAQI, getPollutantConcentration(pm25))
        );

        JsonNode so2 = pollutants.get("so2");
        BAQI so2BAQI= getPollutantBAQI(so2);
        listOfPollutants.put(
                "so2", new SO2(so2BAQI, getPollutantConcentration(so2))
        );

    }

    @Override
    public String toString() {
        return "AirRequest{" +
                "requestDate=" + requestDate +
                ", baqi=" + baqi +
                ", listOfPollutants=" + listOfPollutants +
                '}';
    }

    public long getRequestDate(){
        return this.requestDate;
    }

    public void setRequestDate(long requestDate){
        this.requestDate = requestDate;
    }

    public Map<String, Pollutants> getListOfPollutants() {
        return listOfPollutants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AirRequest)) return false;
        AirRequest that = (AirRequest) o;
        return  Objects.equals(baqi, that.baqi) &&
                Objects.equals(listOfPollutants, that.listOfPollutants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestDate, baqi, listOfPollutants, objectMapper);
    }
}
