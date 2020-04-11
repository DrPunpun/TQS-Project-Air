package tqs.project.air.airinfo.qualitymetrics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PollutantsTest {
    private CO co;
    private SO2 so2;
    private BAQI baqi;

    @BeforeEach
    void setup(){
        baqi = new BAQI(0, "a", "b");
        co = new CO(baqi, 22.2);
        so2 = new SO2(baqi, 22.2);
    }

    @Test
    void gettersTest() {
        assertEquals(new BAQI(0, "a", "b"), co.getBaqi());
        assertEquals(22.2, co.getConcentration());
        assertEquals("ppb", co.getConcentrationunits());
        assertEquals("Carbon Monoxide", co.getName());
        assertEquals("CO", co.getShorthand());
    }

    @Test
    void equalsTest() {
        assertFalse(so2.equals(co));
        assertTrue(co.equals(new CO(baqi, 22.2)));
    }

    @Test
    void hashCodeTest() {
        assertEquals(co.hashCode(), new CO(baqi, 22.2).hashCode());
    }
}