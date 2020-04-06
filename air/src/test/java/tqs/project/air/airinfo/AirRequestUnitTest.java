package tqs.project.air.airinfo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        this.airRequest = new AirRequest("data");
    }

    @Test
    public void getDataTest(){
        assertEquals(this.airRequest.getData(), "data");
    }

    @Test
    public void getHitTest(){
        assertEquals(airRequest.getHit(), 0);
    }

    @Test
    public void getMissTest(){
        assertEquals(airRequest.getMiss(), 1);
    }

    @Test
    public void missTest(){
        this.airRequest.miss();
        assertEquals(airRequest.getMiss(), 2);
    }

    @Test
    public void hitTest(){
        this.airRequest.hit();
        assertEquals(airRequest.getHit(), 1);
    }

    @Test
    public void comparesTest(){
        this.airRequest.hit();
        AirRequest airRequest2 = new AirRequest("data");
        assertTrue(airRequest.compareTo(airRequest2) > 0);
    }

    @Test
    public void getAndSetDataTest(){
        assertEquals(this.airRequest.getData(), "data");
        this.airRequest.setData("data2");
        assertEquals(this.airRequest.getData(), "data2");
    }

    @Test
    public void getAndSetTimestampTest(){
        long currentTimestamp = System.currentTimeMillis();
        this.airRequest.setRequestDate(currentTimestamp);
        assertEquals(this.airRequest.getRequestDate(), currentTimestamp);
    }
}
