package tqs.project.air.airinfo.qualitymetrics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class BAQITest {
    private BAQI baqi;

    @BeforeEach
    void setUp() {
        baqi = new BAQI(0, "a", "b");
    }

    @Test
    void equalsTest() {
        assertEquals(baqi, new BAQI(0, "a", "b"));

    }

    @Test
    void hashCodeTest() {
        HashMap<BAQI, Integer> test = new HashMap<>();
        test.put(baqi, 0);
        assertTrue(test.containsKey(new BAQI(0, "a", "b")));
    }

    @Test
    void gettersTest(){
        assertEquals(0, baqi.getAqi());
        assertEquals("a", baqi.getColor());
        assertEquals("b", baqi.getCategory());
    }
}
