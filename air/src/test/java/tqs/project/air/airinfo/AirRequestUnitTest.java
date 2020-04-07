package tqs.project.air.airinfo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tqs.project.air.airinfo.qualitymetrics.*;

import static org.junit.jupiter.api.Assertions.*;

public class AirRequestUnitTest {
    private AirRequest airRequest;
    private static AirCoord air;

    @BeforeAll
    static public void generalSetup(){
        air = new AirCoord(0,0);
        System.out.println("Setup complete");
    }

    @BeforeEach
    public void setup(){
        String data = "{\"metadata\": null, \"data\": {\"datetime\": \"2020-04-06T14:00:00Z\", \"data_available\": true, \"indexes\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 63, \"aqi_display\": \"63\", \"color\": \"#AFDF21\", \"category\": \"Good air quality\", \"dominant_pollutant\": \"pm25\"}, \"fra_atmo\": {\"display_name\": \"AQI (FR)\", \"aqi\": 4, \"aqi_display\": \"4\", \"color\": \"#008000\", \"category\": \"Good air quality\", \"dominant_pollutant\": \"pm10\"}}, \"pollutants\": {\"co\": {\"display_name\": \"CO\", \"full_name\": \"Carbon monoxide\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 99, \"aqi_display\": \"99\", \"color\": \"#009E3A\", \"category\": \"Excellent air quality\"}}, \"concentration\": {\"value\": 207.1, \"units\": \"ppb\"}, \"sources_and_effects\": {\"sources\": \"Typically originates from incomplete combustion of carbon fuels, such as that which occurs in car engines and power plants.\", \"effects\": \"When inhaled, carbon monoxide can prevent the blood from carrying oxygen. Exposure may cause dizziness, nausea and headaches. Exposure to extreme concentrations can lead to loss of consciousness.\"}}, \"no2\": {\"display_name\": \"NO2\", \"full_name\": \"Nitrogen dioxide\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 78, \"aqi_display\": \"78\", \"color\": \"#84CF33\", \"category\": \"Good air quality\"}}, \"concentration\": {\"value\": 28.78, \"units\": \"ppb\"}, \"sources_and_effects\": {\"sources\": \"Main sources are fuel burning processes, such as those used in industry and transportation.\", \"effects\": \"Exposure may cause increased bronchial reactivity in patients with asthma, lung function decline in patients with COPD, and increased risk of respiratory infections, especially in young children.\"}}, \"o3\": {\"display_name\": \"O3\", \"full_name\": \"Ozone\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 72, \"aqi_display\": \"72\", \"color\": \"#84CF33\", \"category\": \"Good air quality\"}}, \"concentration\": {\"value\": 35.5, \"units\": \"ppb\"}, \"sources_and_effects\": {\"sources\": \"Ozone is created in a chemical reaction between atmospheric oxygen, nitrogen oxides, carbon monoxide and organic compounds, in the presence of sunlight.\", \"effects\": \"Ozone can irritate the airways and cause coughing, a burning sensation, wheezing and shortness of breath. Additionally, ozone is one of the major components of photochemical smog.\"}}, \"pm10\": {\"display_name\": \"PM10\", \"full_name\": \"Inhalable particulate matter (<10\\u00b5m)\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 69, \"aqi_display\": \"69\", \"color\": \"#84CF33\", \"category\": \"Good air quality\"}}, \"concentration\": {\"value\": 34.39, \"units\": \"ug/m3\"}, \"sources_and_effects\": {\"sources\": \"Main sources are combustion processes (e.g. indoor heating, wildfires), mechanical processes (e.g. construction, mineral dust, agriculture) and biological particles (e.g. pollen, bacteria, mold).\", \"effects\": \"Inhalable particles can penetrate into the lungs. Short term exposure can cause irritation of the airways, coughing, and aggravation of heart and lung diseases, expressed as difficulty breathing, heart attacks and even premature death.\"}}, \"pm25\": {\"display_name\": \"PM2.5\", \"full_name\": \"Fine particulate matter (<2.5\\u00b5m)\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 63, \"aqi_display\": \"63\", \"color\": \"#84CF33\", \"category\": \"Good air quality\"}}, \"concentration\": {\"value\": 22.64, \"units\": \"ug/m3\"}, \"sources_and_effects\": {\"sources\": \"Main sources are combustion processes (e.g. power plants, indoor heating, car exhausts, wildfires), mechanical processes (e.g. construction, mineral dust) and biological particles (e.g. bacteria, viruses).\", \"effects\": \"Fine particles can penetrate into the lungs and bloodstream. Short term exposure can cause irritation of the airways, coughing and aggravation of heart and lung diseases, expressed as difficulty breathing, heart attacks and even premature death.\"}}, \"so2\": {\"display_name\": \"SO2\", \"full_name\": \"Sulfur dioxide\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 100, \"aqi_display\": \"100\", \"color\": \"#009E3A\", \"category\": \"Excellent air quality\"}}, \"concentration\": {\"value\": 0.39, \"units\": \"ppb\"}, \"sources_and_effects\": {\"sources\": \"Main sources are burning processes of sulfur-containing fuel in industry, transportation and power plants.\", \"effects\": \"Exposure causes irritation of the respiratory tract, coughing and generates local inflammatory reactions. These in turn, may cause aggravation of lung diseases, even with short term exposure.\"}}}, \"health_recommendations\": {\"general_population\": \"With this level of air quality, you have no limitations. Enjoy the outdoors!\", \"elderly\": \"If you start to feel respiratory discomfort such as coughing or breathing difficulties, consider reducing the intensity of your outdoor activities. Try to limit the time you spend near busy roads, construction sites, open fires and other sources of smoke.\", \"lung_diseases\": \"If you start to feel respiratory discomfort such as coughing or breathing difficulties, consider reducing the intensity of your outdoor activities. Try to limit the time you spend near busy roads, industrial emission stacks, open fires and other sources of smoke.\", \"heart_diseases\": \"If you start to feel respiratory discomfort such as coughing or breathing difficulties, consider reducing the intensity of your outdoor activities. Try to limit the time you spend near busy roads, construction sites, industrial emission stacks, open fires and other sources of smoke.\", \"active\": \"If you start to feel respiratory discomfort such as coughing or breathing difficulties, consider reducing the intensity of your outdoor activities. Try to limit the time you spend near busy roads, construction sites, industrial emission stacks, open fires and other sources of smoke.\", \"pregnant_women\": \"To keep you and your baby healthy, consider reducing the intensity of your outdoor activities. Try to limit the time you spend near busy roads, construction sites, open fires and other sources of smoke.\", \"children\": \"If you start to feel respiratory discomfort such as coughing or breathing difficulties, consider reducing the intensity of your outdoor activities. Try to limit the time you spend near busy roads, construction sites, open fires and other sources of smoke.\"}}, \"error\": null}";

        this.airRequest = new AirRequest(data);
    }

    @Test
    public void parseDataTest(){
        BAQI baqi = new BAQI(63, "#AFDF21", "Good air quality");

        assertEquals(baqi, this.airRequest.getBaqi());

        CO co = new CO(new BAQI(99, "#009E3A", "Excellent air quality"), 207.1);
        assertEquals(co, this.airRequest.getAirMetric("co"));

        NO2 no2 = new NO2(new BAQI(78, "#84CF33", "Good air quality"), 28.78);
        assertEquals(no2, this.airRequest.getAirMetric("no2"));

        O3 o3 = new O3(new BAQI(72, "#84CF33",  "Good air quality"), 35.5);
        assertEquals(o3, this.airRequest.getAirMetric("o3"));

        PM10 pm10 = new PM10(new BAQI(69, "#84CF33", "Good air quality"), 34.39);
        assertEquals(pm10, this.airRequest.getAirMetric("pm10"));

        PM25 pm25 = new PM25(new BAQI(63, "#84CF33", "Good air quality"), 22.64);
        assertEquals(pm25, this.airRequest.getAirMetric("pm25"));

        SO2 so2 = new SO2(new BAQI(100, "#009E3A", "Excellent air quality"), 0.39);
        assertEquals(so2, this.airRequest.getAirMetric("so2"));

    }

    @Test
    public void getAndSetTimestampTest(){
        long currentTimestamp = System.currentTimeMillis();
        this.airRequest.setRequestDate(currentTimestamp);
        assertEquals(currentTimestamp, this.airRequest.getRequestDate());
    }
}
