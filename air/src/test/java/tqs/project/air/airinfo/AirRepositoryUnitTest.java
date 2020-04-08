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
        this.data = "{\"metadata\": null, \"data\": {\"datetime\": \"2020-04-07T16:00:00Z\", \"data_available\": true, \"indexes\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 51, \"aqi_display\": \"51\", \"color\": \"#F8FC02\", \"category\": \"Moderate air quality\", \"dominant_pollutant\": \"pm25\"}, \"fra_atmo\": {\"display_name\": \"AQI (FR)\", \"aqi\": 5, \"aqi_display\": \"5\", \"color\": \"#FFA500\", \"category\": \"Average air quality\", \"dominant_pollutant\": \"pm10\"}}, \"pollutants\": {\"co\": {\"display_name\": \"CO\", \"full_name\": \"Carbon monoxide\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 98, \"aqi_display\": \"98\", \"color\": \"#009E3A\", \"category\": \"Excellent air quality\"}}, \"concentration\": {\"value\": 218.87, \"units\": \"ppb\"}, \"sources_and_effects\": {\"sources\": \"Typically originates from incomplete combustion of carbon fuels, such as that which occurs in car engines and power plants.\", \"effects\": \"When inhaled, carbon monoxide can prevent the blood from carrying oxygen. Exposure may cause dizziness, nausea and headaches. Exposure to extreme concentrations can lead to loss of consciousness.\"}}, \"no2\": {\"display_name\": \"NO2\", \"full_name\": \"Nitrogen dioxide\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 89, \"aqi_display\": \"89\", \"color\": \"#009E3A\", \"category\": \"Excellent air quality\"}}, \"concentration\": {\"value\": 15.05, \"units\": \"ppb\"}, \"sources_and_effects\": {\"sources\": \"Main sources are fuel burning processes, such as those used in industry and transportation.\", \"effects\": \"Exposure may cause increased bronchial reactivity in patients with asthma, lung function decline in patients with COPD, and increased risk of respiratory infections, especially in young children.\"}}, \"o3\": {\"display_name\": \"O3\", \"full_name\": \"Ozone\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 63, \"aqi_display\": \"63\", \"color\": \"#84CF33\", \"category\": \"Good air quality\"}}, \"concentration\": {\"value\": 46.38, \"units\": \"ppb\"}, \"sources_and_effects\": {\"sources\": \"Ozone is created in a chemical reaction between atmospheric oxygen, nitrogen oxides, carbon monoxide and organic compounds, in the presence of sunlight.\", \"effects\": \"Ozone can irritate the airways and cause coughing, a burning sensation, wheezing and shortness of breath. Additionally, ozone is one of the major components of photochemical smog.\"}}, \"pm10\": {\"display_name\": \"PM10\", \"full_name\": \"Inhalable particulate matter (<10\\u00b5m)\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 77, \"aqi_display\": \"77\", \"color\": \"#84CF33\", \"category\": \"Good air quality\"}}, \"concentration\": {\"value\": 26.21, \"units\": \"ug/m3\"}, \"sources_and_effects\": {\"sources\": \"Main sources are combustion processes (e.g. indoor heating, wildfires), mechanical processes (e.g. construction, mineral dust, agriculture) and biological particles (e.g. pollen, bacteria, mold).\", \"effects\": \"Inhalable particles can penetrate into the lungs. Short term exposure can cause irritation of the airways, coughing, and aggravation of heart and lung diseases, expressed as difficulty breathing, heart attacks and even premature death.\"}}, \"pm25\": {\"display_name\": \"PM2.5\", \"full_name\": \"Fine particulate matter (<2.5\\u00b5m)\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 51, \"aqi_display\": \"51\", \"color\": \"#FFFF00\", \"category\": \"Moderate air quality\"}}, \"concentration\": {\"value\": 33.93, \"units\": \"ug/m3\"}, \"sources_and_effects\": {\"sources\": \"Main sources are combustion processes (e.g. power plants, indoor heating, car exhausts, wildfires), mechanical processes (e.g. construction, mineral dust) and biological particles (e.g. bacteria, viruses).\", \"effects\": \"Fine particles can penetrate into the lungs and bloodstream. Short term exposure can cause irritation of the airways, coughing and aggravation of heart and lung diseases, expressed as difficulty breathing, heart attacks and even premature death.\"}}, \"so2\": {\"display_name\": \"SO2\", \"full_name\": \"Sulfur dioxide\", \"aqi_information\": {\"baqi\": {\"display_name\": \"BreezoMeter AQI\", \"aqi\": 100, \"aqi_display\": \"100\", \"color\": \"#009E3A\", \"category\": \"Excellent air quality\"}}, \"concentration\": {\"value\": 0.51, \"units\": \"ppb\"}, \"sources_and_effects\": {\"sources\": \"Main sources are burning processes of sulfur-containing fuel in industry, transportation and power plants.\", \"effects\": \"Exposure causes irritation of the respiratory tract, coughing and generates local inflammatory reactions. These in turn, may cause aggravation of lung diseases, even with short term exposure.\"}}}, \"health_recommendations\": {\"general_population\": \"If you start to feel respiratory discomfort such as coughing or breathing difficulties, consider reducing the intensity of your outdoor activities. Try to limit the time you spend near busy roads, construction sites, open fires and other sources of smoke.\", \"elderly\": \"Reduce the intensity of your outdoor activities. It is recommended to limit the time you are near busy roads, construction sites, open fires and other sources of smoke. Staying indoors with an activated air filtration system would be best for your long term health.\", \"lung_diseases\": \"Reduce the intensity of your outdoor activities. Keep relevant medication(s) available and consult a doctor with any questions. It is recommended to limit the time you are near busy roads, open fires and other sources of smoke. In addition, consider reducing the time you spend near industrial emission stacks. Staying indoors with an activated air filtration system would be best for your long term health.\", \"heart_diseases\": \"Reduce the intensity of your outdoor activities. Keep relevant medication(s) available and consult a doctor with any questions. It is recommended to limit the time you are near busy roads, construction sites, open fires and other sources of smoke. In addition, consider reducing the time you spend near industrial emission stacks. Staying indoors with an activated air filtration system would be best for your long term health.\", \"active\": \"Reduce the intensity of your outdoor activities. It is recommended to limit the time you are near busy roads, construction sites, open fires and other sources of smoke. In addition, consider reducing the time you spend near industrial emission stacks. Staying indoors with an activated air filtration system would be best for your long term health.\", \"pregnant_women\": \"To keep you and your baby healthy, reduce the intensity of your outdoor activities. It is recommended to limit the time you are near busy roads, construction sites, open fires and other sources of smoke. Staying indoors with an activated air filtration system would be best for your long term health.\", \"children\": \"Reduce the intensity of your outdoor activities. It is recommended to limit the time you are near busy roads, construction sites, open fires and other sources of smoke. Staying indoors with an activated air filtration system would be best for your long term health.\"}}, \"error\": null}";

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
