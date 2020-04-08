package tqs.project.air.airinfo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tqs.project.air.AirApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = AirApplication.class)
@AutoConfigureMockMvc
public class AirControllerIT {
    @Autowired
    private AirServiceImpl airService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void resetCache(){
        this.airService.clearCache();
    }

    @Test
    public void givenCorrectCoords_AirServiceReturnsStatus200() {
        double lat = 48.857456;
        double lon = 2.354611;

        try {
            airService.sendGET(""+lat, ""+lon);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void givenCorrectCoordsOnce_ReturnsStatus200_CountsMiss() throws Exception {
        double lat = 48.857456;
        double lon = 2.354611;

        mockMvc.perform(get("/api/breeze")
                .param("lat", ""+lat)
                .param("lon", ""+lon)
                .param("features", "breezometer_aqi,local_aqi,health_recommendations,sources_and_effects,pollutants_concentrations,pollutants_aqi_information\n")
                .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
        assertEquals(0, airService.getHits());
        assertEquals(1, airService.getMisses());
    }

    @Test
    public void givenCorrectCoordsTwice_ReturnsStatus200_CountsMissAndHits() throws Exception {
        double lat = 48.857456;
        double lon = 2.354611;

        mockMvc.perform(get("/api/breeze")
                .param("lat", ""+lat)
                .param("lon", ""+lon)
                .param("features", "breezometer_aqi,local_aqi,health_recommendations,sources_and_effects,pollutants_concentrations,pollutants_aqi_information\n")
                .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
        assertEquals(1, airService.getMisses());
        assertEquals(0, airService.getHits());

        mockMvc.perform(get("/api/breeze")
                .param("lat", ""+lat)
                .param("lon", ""+lon)
                .param("features", "breezometer_aqi,local_aqi,health_recommendations,sources_and_effects,pollutants_concentrations,pollutants_aqi_information\n")
                .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
        assertEquals(1, airService.getMisses());
        assertEquals(1, airService.getHits());
    }
}