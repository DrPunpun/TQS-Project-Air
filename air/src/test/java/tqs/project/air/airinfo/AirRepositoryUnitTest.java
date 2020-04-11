package tqs.project.air.airinfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class AirRepositoryUnitTest {
    private AirRepository airRepository;
    private String data;

    @BeforeEach
    public void setupRepo(){
        this.data = "{\"metadata\": null, \"data\": {\"datetime\": \"2020-04-11T15:00:00Z\", \"data_available\": true, \"indexes\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 60, \"aqi_display\": \"60\", \"color\": \"#C1E719\", \"category\": \"Good air quality\", \"dominant_pollutant\": \"o3\"}, \"fra_atmo\": {\"display_name\": \"AQI (FR)\", \"aqi\": 6, \"aqi_display\": \"6\", \"color\": \"#FFA500\", \"category\": \"Mediocre air quality\", \"dominant_pollutant\": \"pm10\"}}, \"pollutants\": {\"co\": {\"display_name\": \"CO\", \"full_name\": \"Carbon monoxide\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 99, \"aqi_display\": \"99\", \"color\": \"#009E3A\", \"category\": \"Excellent air quality\"}}, \"concentration\": {\"value\": 109.04, \"units\": \"ppb\"}, \"sources_and_effects\": {\"sources\": \"Typically originates from incomplete combustion of carbon fuels, such as that which occurs in car engines and power plants.\", \"effects\": \"When inhaled, carbon monoxide can prevent the blood from carrying oxygen. Exposure may cause dizziness, nausea and headaches. Exposure to extreme concentrations can lead to loss of consciousness.\"}}, \"no2\": {\"display_name\": \"NO2\", \"full_name\": \"Nitrogen dioxide\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 74, \"aqi_display\": \"74\", \"color\": \"#84CF33\", \"category\": \"Good air quality\"}}, \"concentration\": {\"value\": 34.5, \"units\": \"ppb\"}, \"sources_and_effects\": {\"sources\": \"Main sources are fuel burning processes, such as those used in industry and transportation.\", \"effects\": \"Exposure may cause increased bronchial reactivity in patients with asthma, lung function decline in patients with COPD, and increased risk of respiratory infections, especially in young children.\"}}, \"o3\": {\"display_name\": \"O3\", \"full_name\": \"Ozone\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 60, \"aqi_display\": \"60\", \"color\": \"#84CF33\", \"category\": \"Good air quality\"}}, \"concentration\": {\"value\": 50.65, \"units\": \"ppb\"}, \"sources_and_effects\": {\"sources\": \"Ozone is created in a chemical reaction between atmospheric oxygen, nitrogen oxides, carbon monoxide and organic compounds, in the presence of sunlight.\", \"effects\": \"Ozone can irritate the airways and cause coughing, a burning sensation, wheezing and shortness of breath. Additionally, ozone is one of the major components of photochemical smog.\"}}, \"pm10\": {\"display_name\": \"PM10\", \"full_name\": \"Inhalable particulate matter (<10\\u00b5m)\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 60, \"aqi_display\": \"60\", \"color\": \"#84CF33\", \"category\": \"Good air quality\"}}, \"concentration\": {\"value\": 44.4, \"units\": \"ug/m3\"}, \"sources_and_effects\": {\"sources\": \"Main sources are combustion processes (e.g. indoor heating, wildfires), mechanical processes (e.g. construction, mineral dust, agriculture) and biological particles (e.g. pollen, bacteria, mold).\", \"effects\": \"Inhalable particles can penetrate into the lungs. Short term exposure can cause irritation of the airways, coughing, and aggravation of heart and lung diseases, expressed as difficulty breathing, heart attacks and even premature death.\"}}, \"pm25\": {\"display_name\": \"PM2.5\", \"full_name\": \"Fine particulate matter (<2.5\\u00b5m)\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 61, \"aqi_display\": \"61\", \"color\": \"#84CF33\", \"category\": \"Good air quality\"}}, \"concentration\": {\"value\": 24.02, \"units\": \"ug/m3\"}, \"sources_and_effects\": {\"sources\": \"Main sources are combustion processes (e.g. power plants, indoor heating, car exhausts, wildfires), mechanical processes (e.g. construction, mineral dust) and biological particles (e.g. bacteria, viruses).\", \"effects\": \"Fine particles can penetrate into the lungs and bloodstream. Short term exposure can cause irritation of the airways, coughing and aggravation of heart and lung diseases, expressed as difficulty breathing, heart attacks and even premature death.\"}}, \"so2\": {\"display_name\": \"SO2\", \"full_name\": \"Sulfur dioxide\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 100, \"aqi_display\": \"100\", \"color\": \"#009E3A\", \"category\": \"Excellent air quality\"}}, \"concentration\": {\"value\": 0.53, \"units\": \"ppb\"}, \"sources_and_effects\": {\"sources\": \"Main sources are burning processes of sulfur-containing fuel in industry, transportation and power plants.\", \"effects\": \"Exposure causes irritation of the respiratory tract, coughing and generates local inflammatory reactions. These in turn, may cause aggravation of lung diseases, even with short term exposure.\"}}}, \"health_recommendations\": {\"general_population\": \"With this level of air quality, you have no limitations. Enjoy the outdoors!\", \"elderly\": \"If you start to feel respiratory discomfort such as coughing or breathing difficulties, consider reducing the intensity of your outdoor activities. Try to limit the time you spend near busy roads, construction sites, open fires and other sources of smoke.\", \"lung_diseases\": \"If you start to feel respiratory discomfort such as coughing or breathing difficulties, consider reducing the intensity of your outdoor activities. Try to limit the time you spend near busy roads, industrial emission stacks, open fires and other sources of smoke.\", \"heart_diseases\": \"If you start to feel respiratory discomfort such as coughing or breathing difficulties, consider reducing the intensity of your outdoor activities. Try to limit the time you spend near busy roads, construction sites, industrial emission stacks, open fires and other sources of smoke.\", \"active\": \"If you start to feel respiratory discomfort such as coughing or breathing difficulties, consider reducing the intensity of your outdoor activities. Try to limit the time you spend near busy roads, construction sites, industrial emission stacks, open fires and other sources of smoke.\", \"pregnant_women\": \"To keep you and your baby healthy, consider reducing the intensity of your outdoor activities. Try to limit the time you spend near busy roads, construction sites, open fires and other sources of smoke.\", \"children\": \"If you start to feel respiratory discomfort such as coughing or breathing difficulties, consider reducing the intensity of your outdoor activities. Try to limit the time you spend near busy roads, construction sites, open fires and other sources of smoke.\"}}, \"error\": null}";
        this.airRepository = new AirRepository(500);

    }

    @Test
    public void testGetData() throws InterruptedException {
        assertNull(this.airRepository.getData(0, 0));

        AirRequest airRequest = this.airRepository.putData(0, 0, this.data);

        assertEquals(1, this.airRepository.getMiss());
        assertEquals(0, this.airRepository.getHit());

        AirRequest airRequest1 = this.airRepository.getData(0, 0);
        assertTrue(this.airRepository.cache.containsKey(new AirCoord(0, 0)));
        assertEquals(1, this.airRepository.getMiss());
        assertEquals(1, this.airRepository.getHit());
        assertEquals(airRequest, airRequest1);

        TimeUnit.MILLISECONDS.sleep(600);

        assertNull(this.airRepository.getData(0, 0));

    }

}
