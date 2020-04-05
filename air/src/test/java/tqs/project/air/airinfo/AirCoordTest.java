package tqs.project.air.airinfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AirCoordTest {
    private AirCoord airCoord;

    @BeforeEach
    private void setupAirCoord(){
        airCoord = new AirCoord(0, 0);
    }

    @Test
    private void testGetLon(){
        assertEquals(airCoord.getLon(), 0);
    }

    @Test
    private void testGetLat(){
        assertEquals(airCoord.getLat(), 0);
    }

    @Test
    private void testEquals(){
        assertTrue(airCoord.equals(new AirCoord(0, 0)));
    }
}
