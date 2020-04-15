package tqs.project.air.airinfo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AirServiceImplTest {
    private static AirRequest airRequest;
    private static String data;
    @Mock
    private AirRepository airRepository;

    @InjectMocks
    private AirServiceImpl airServiceImpl;

    @BeforeAll
    public static void setupAirRequest() {
        data = "{\"metadata\": null, \"data\": {\"datetime\": \"2020-04-15T20:00:00Z\", \"data_available\": true, \"indexes\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 70, \"aqi_display\": \"70\", \"color\": \"#84CF33\", \"category\": \"Good air quality\", \"dominant_pollutant\": \"o3\"}, \"fra_atmo\": {\"display_name\": \"AQI (FR)\", \"aqi\": 5, \"aqi_display\": \"5\", \"color\": \"#FFA500\", \"category\": \"Average air quality\", \"dominant_pollutant\": \"pm10\"}}, \"pollutants\": {\"co\": {\"display_name\": \"CO\", \"full_name\": \"Carbon monoxide\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 99, \"aqi_display\": \"99\", \"color\": \"#009E3A\", \"category\": \"Excellent air quality\"}}, \"concentration\": {\"value\": 115.43, \"units\": \"ppb\"}, \"sources_and_effects\": {\"sources\": \"Typically originates from incomplete combustion of carbon fuels, such as that which occurs in car engines and power plants.\", \"effects\": \"When inhaled, carbon monoxide can prevent the blood from carrying oxygen. Exposure may cause dizziness, nausea and headaches. Exposure to extreme concentrations can lead to loss of consciousness.\"}}, \"no2\": {\"display_name\": \"NO2\", \"full_name\": \"Nitrogen dioxide\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 84, \"aqi_display\": \"84\", \"color\": \"#009E3A\", \"category\": \"Excellent air quality\"}}, \"concentration\": {\"value\": 21.1, \"units\": \"ppb\"}, \"sources_and_effects\": {\"sources\": \"Main sources are fuel burning processes, such as those used in industry and transportation.\", \"effects\": \"Exposure may cause increased bronchial reactivity in patients with asthma, lung function decline in patients with COPD, and increased risk of respiratory infections, especially in young children.\"}}, \"o3\": {\"display_name\": \"O3\", \"full_name\": \"Ozone\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 70, \"aqi_display\": \"70\", \"color\": \"#84CF33\", \"category\": \"Good air quality\"}}, \"concentration\": {\"value\": 37.96, \"units\": \"ppb\"}, \"sources_and_effects\": {\"sources\": \"Ozone is created in a chemical reaction between atmospheric oxygen, nitrogen oxides, carbon monoxide and organic compounds, in the presence of sunlight.\", \"effects\": \"Ozone can irritate the airways and cause coughing, a burning sensation, wheezing and shortness of breath. Additionally, ozone is one of the major components of photochemical smog.\"}}, \"pm10\": {\"display_name\": \"PM10\", \"full_name\": \"Inhalable particulate matter (<10\\u00b5m)\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 71, \"aqi_display\": \"71\", \"color\": \"#84CF33\", \"category\": \"Good air quality\"}}, \"concentration\": {\"value\": 32.68, \"units\": \"ug/m3\"}, \"sources_and_effects\": {\"sources\": \"Main sources are combustion processes (e.g. indoor heating, wildfires), mechanical processes (e.g. construction, mineral dust, agriculture) and biological particles (e.g. pollen, bacteria, mold).\", \"effects\": \"Inhalable particles can penetrate into the lungs. Short term exposure can cause irritation of the airways, coughing, and aggravation of heart and lung diseases, expressed as difficulty breathing, heart attacks and even premature death.\"}}, \"pm25\": {\"display_name\": \"PM2.5\", \"full_name\": \"Fine particulate matter (<2.5\\u00b5m)\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 73, \"aqi_display\": \"73\", \"color\": \"#84CF33\", \"category\": \"Good air quality\"}}, \"concentration\": {\"value\": 16.68, \"units\": \"ug/m3\"}, \"sources_and_effects\": {\"sources\": \"Main sources are combustion processes (e.g. power plants, indoor heating, car exhausts, wildfires), mechanical processes (e.g. construction, mineral dust) and biological particles (e.g. bacteria, viruses).\", \"effects\": \"Fine particles can penetrate into the lungs and bloodstream. Short term exposure can cause irritation of the airways, coughing and aggravation of heart and lung diseases, expressed as difficulty breathing, heart attacks and even premature death.\"}}, \"so2\": {\"display_name\": \"SO2\", \"full_name\": \"Sulfur dioxide\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 100, \"aqi_display\": \"100\", \"color\": \"#009E3A\", \"category\": \"Excellent air quality\"}}, \"concentration\": {\"value\": 0.42, \"units\": \"ppb\"}, \"sources_and_effects\": {\"sources\": \"Main sources are burning processes of sulfur-containing fuel in industry, transportation and power plants.\", \"effects\": \"Exposure causes irritation of the respiratory tract, coughing and generates local inflammatory reactions. These in turn, may cause aggravation of lung diseases, even with short term exposure.\"}}}, \"health_recommendations\": {\"general_population\": \"With this level of air quality, you have no limitations. Enjoy the outdoors!\", \"elderly\": \"If you start to feel respiratory discomfort such as coughing or breathing difficulties, consider reducing the intensity of your outdoor activities. Try to limit the time you spend near busy roads, construction sites, open fires and other sources of smoke.\", \"lung_diseases\": \"If you start to feel respiratory discomfort such as coughing or breathing difficulties, consider reducing the intensity of your outdoor activities. Try to limit the time you spend near busy roads, industrial emission stacks, open fires and other sources of smoke.\", \"heart_diseases\": \"If you start to feel respiratory discomfort such as coughing or breathing difficulties, consider reducing the intensity of your outdoor activities. Try to limit the time you spend near busy roads, construction sites, industrial emission stacks, open fires and other sources of smoke.\", \"active\": \"If you start to feel respiratory discomfort such as coughing or breathing difficulties, consider reducing the intensity of your outdoor activities. Try to limit the time you spend near busy roads, construction sites, industrial emission stacks, open fires and other sources of smoke.\", \"pregnant_women\": \"To keep you and your baby healthy, consider reducing the intensity of your outdoor activities. Try to limit the time you spend near busy roads, construction sites, open fires and other sources of smoke.\", \"children\": \"If you start to feel respiratory discomfort such as coughing or breathing difficulties, consider reducing the intensity of your outdoor activities. Try to limit the time you spend near busy roads, construction sites, open fires and other sources of smoke.\"}}, \"error\": null}";
        airRequest = new AirRequest(data);
    }

    @BeforeEach
    public void setupMocks(){

        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField( airServiceImpl, "key", "f8e686b5d7e145b1b64752921eb03f25");
        ReflectionTestUtils.setField( airServiceImpl, "features", "breezometer_aqi,local_aqi,health_recommendations,sources_and_effects,pollutants_concentrations,pollutants_aqi_information");
        ReflectionTestUtils.setField( airServiceImpl, "url", "https://api.breezometer.com/air-quality/v2/current-conditions?");

    }

    @Test
    public void getAirQualityByLocalNFeatures_NotInCache_Test(){
        when(airRepository.getData(48.857456, 2.354611)).thenReturn(null);

        String[] features = new String[3];
        features[0] = "co";
        features[1] = "so2";
        features[2] = "pm25";

        AirRequest airResult = airServiceImpl.getAirQualityByLocal(48.857456, 2.354611, features);

        AirRequest airExpected = new AirRequest(data);
        airExpected.excludeAirMetric("no2");
        airExpected.excludeAirMetric("o3");
        airExpected.excludeAirMetric("pm10");

        assertEquals(airExpected, airResult);
        verify(airRepository, times(1)).putData(anyDouble(), anyDouble(), anyString());
        verify(airRepository, times(1)).getData(anyDouble(), anyDouble());

    }

    @Test
    public void getAirQualityByLocalNFeatures_InCache_Test(){
        when(airRepository.getData(0, 0)).thenReturn(airRequest);
        String[] features = new String[2];
        features[0] = "co";
        features[1] = "so2";

        AirRequest airResult = airServiceImpl.getAirQualityByLocal(0, 0, features);

        AirRequest airExpected = new AirRequest(data);
        airExpected.excludeAirMetric("no2");
        airExpected.excludeAirMetric("o3");
        airExpected.excludeAirMetric("pm10");
        airExpected.excludeAirMetric("pm25");

        assertEquals(airExpected, airResult);
        verify(airRepository, times(0)).putData(anyDouble(), anyDouble(), anyString());
        verify(airRepository, times(1)).getData(anyDouble(), anyDouble());

    }

}
