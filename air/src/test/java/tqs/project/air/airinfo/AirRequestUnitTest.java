package tqs.project.air.airinfo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AirRequestUnitTest {
    private AirRequest airRequest;
    private AirCoord air;

    @BeforeAll
    private void generalSetup(){
        this.air = new AirCoord(0,0);
        System.out.println("Setup complete");
    }

    @BeforeEach
    private void setup(){
        this.airRequest = new AirRequest("data");
    }

    @Test
    private void getDataTest(){
        assertEquals(this.airRequest.getData(), "data");
    }

    @Test
    private void getHitTest(){
        assertEquals(airRequest.getHit(), 0);
    }

    @Test
    private void getMissTest(){
        assertEquals(airRequest.getMiss(), 1);
    }

    @Test
    private void missTest(){
        this.airRequest.miss();
        assertEquals(airRequest.getMiss(), 2);
    }

    @Test
    private void hitTest(){
        this.airRequest.hit();
        assertEquals(airRequest.getHit(), 1);
    }

    @Test
    private void comparesTest(){
        this.airRequest.hit();
        AirRequest airRequest2 = new AirRequest("data");
        assertTrue(airRequest.compareTo(airRequest2) > 0);
    }
}
