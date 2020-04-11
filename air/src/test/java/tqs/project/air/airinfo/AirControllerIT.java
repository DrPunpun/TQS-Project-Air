package tqs.project.air.airinfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tqs.project.air.AirApplication;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    public void givenCorrectCoordsOnce_ReturnsStatus200() throws Exception {
        double lat = 48.857456;
        double lon = 2.354611;

        mockMvc.perform(get("/api/breeze")
                .param("lat", ""+lat)
                .param("lon", ""+lon)
                .param("features", "co,so2,no2,o3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$.listOfPollutants", hasKey("co")))
                .andExpect(jsonPath("$.listOfPollutants", hasKey("so2")))
                .andExpect(jsonPath("$.listOfPollutants", hasKey("o3")))
                .andExpect(jsonPath("$.listOfPollutants", hasKey("no2")))
                .andExpect(jsonPath("$.listOfPollutants", not(hasKey("pm10"))))
                .andExpect(jsonPath("$.listOfPollutants", not(hasKey("pm25"))))
        ;
    }

    @Test
    public void givenCorrectCoordsTwice_ReturnsStatus200_CountsMissAndHits() throws Exception {
        double lat = 48.857456;
        double lon = 2.354611;

        mockMvc.perform(get("/api/breeze")
                .param("lat", ""+lat)
                .param("lon", ""+lon)
                .param("features", "co,so2,no2,o3,pm10,pm25")
                .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
        assertEquals(1, airService.getMisses());
        assertEquals(0, airService.getHits());

        mockMvc.perform(get("/api/breeze")
                .param("lat", ""+lat)
                .param("lon", ""+lon)
                .param("features", "co,so2,no2,o3,pm10,pm25")
                .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
        assertEquals(1, airService.getMisses());
        assertEquals(1, airService.getHits());
    }

    @Test
    public void whenSearchingAndClearingCache_MissesReset() throws Exception {
        double lat = 48.857456;
        double lon = 2.354611;

        mockMvc.perform(get("/api/breeze")
                .param("lat", ""+lat)
                .param("lon", ""+lon)
                .param("features", "co,so2,no2,o3,pm10,pm25"));

        assertEquals(1, airService.getMisses());
        assertEquals(0, airService.getHits());

        mockMvc.perform(get("/api/clear")).andExpect(status().isOk());

        mockMvc.perform(get("/api/breeze")
                .param("lat", ""+lat)
                .param("lon", ""+lon)
                .param("features", "co,so2,no2,o3,pm10,pm25"));

        assertEquals(1, airService.getMisses());
        assertEquals(0, airService.getHits());
    }

    @Test
    public void whenSearching_GetCacheChanges() throws Exception {
        double lat = 48.857456;
        double lon = 2.354611;

        mockMvc.perform(get("/api/breeze")
                        .param("lat", ""+lat)
                        .param("lon", ""+lon)
                        .param("features", "co,so2,no2,o3,pm10,pm25"));

        mockMvc.perform(get("/api/cache")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$.Requests", is("1")))
                .andExpect(jsonPath("$.Misses", is("1")))
                .andExpect(jsonPath("$.Hits", is("0")))
        ;
        assertEquals(0, airService.getHits());
    }
}
