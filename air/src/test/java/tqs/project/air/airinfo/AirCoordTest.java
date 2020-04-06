package tqs.project.air.airinfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AirCoordTest {
    private AirCoord airCoord;

    @BeforeEach
    public void setupAirCoord(){
        airCoord = new AirCoord(0, 0);
    }

    @Test
    public void testGetLon(){
        assertEquals(0, airCoord.getLon());
    }

    @Test
    public void testGetLat(){
        assertEquals(0, airCoord.getLat());
    }

    @Test
    public void testEquals(){
        assertTrue(airCoord.equals(new AirCoord(0, 0)));
    }
}
