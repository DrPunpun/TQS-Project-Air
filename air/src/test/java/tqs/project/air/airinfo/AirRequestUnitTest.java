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
        assertEquals("data", this.airRequest.getData());
    }

    @Test
    public void getHitTest(){
        assertEquals(0, airRequest.getHit());
    }

    @Test
    public void getMissTest(){
        assertEquals(1, airRequest.getMiss());
    }

    @Test
    public void missTest(){
        this.airRequest.miss();
        assertEquals(2, airRequest.getMiss());
    }

    @Test
    public void hitTest(){
        this.airRequest.hit();
        assertEquals(1, airRequest.getHit());
    }

    @Test
    public void comparesTest(){
        this.airRequest.hit();
        AirRequest airRequest2 = new AirRequest("data");
        assertTrue(airRequest.compareTo(airRequest2) > 0);
    }

    @Test
    public void getAndSetDataTest(){
        assertEquals("data", this.airRequest.getData());
        this.airRequest.setData("data2");
        assertEquals("data2", this.airRequest.getData());
    }

    @Test
    public void getAndSetTimestampTest(){
        long currentTimestamp = System.currentTimeMillis();
        this.airRequest.setRequestDate(currentTimestamp);
        assertEquals(currentTimestamp, this.airRequest.getRequestDate());
    }
}
