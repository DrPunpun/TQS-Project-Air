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

    @BeforeEach
    public void setupRepo(){
        this.airRepository = new AirRepository();
    }

    @Test
    public void testGetData() throws InterruptedException {
        this.airRepository.getData(0, 0);

        assertEquals(1, this.airRepository.cache.get(new AirCoord(0,0)).getMiss());
        assertEquals(0, this.airRepository.cache.get(new AirCoord(0,0)).getHit());

        this.airRepository.getData(0, 0);
        assertEquals(1, this.airRepository.cache.get(new AirCoord(0,0)).getMiss());
        assertEquals(1, this.airRepository.cache.get(new AirCoord(0,0)).getHit());

        TimeUnit.MILLISECONDS.sleep(600);

        this.airRepository.getData(0, 0);
        assertEquals(2, this.airRepository.cache.get(new AirCoord(0,0)).getMiss());
        assertEquals(1, this.airRepository.cache.get(new AirCoord(0,0)).getHit());

    }

    @Test
    public void testGetMostPopular(){
        this.airRepository.getData(0, 0);
        this.airRepository.getData(0, 0);
        this.airRepository.getData(1, 1);
        this.airRepository.getData(1, 1);
        this.airRepository.getData(0, 0);
        this.airRepository.getData(2, 2);

        ArrayList<AirCoord> airCoordsList = new ArrayList<>();
        for (int i = 0; i < 3; i++) airCoordsList.add(new AirCoord(i,i));

        List<AirCoord> result = this.airRepository.getMostPopular();
        assertEquals(result, airCoordsList);
    }

    @Test
    public void testGetMostPopularN(){
        this.airRepository.getData(0, 0);
        this.airRepository.getData(0, 0);
        this.airRepository.getData(1, 1);
        this.airRepository.getData(1, 1);
        this.airRepository.getData(0, 0);
        this.airRepository.getData(2, 2);

        List<AirCoord> result = this.airRepository.getMostPopular(1);
        assertEquals(result, new ArrayList<>(Arrays.asList(new AirCoord(0, 0))));

    }

}
