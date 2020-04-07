package tqs.project.air.airinfo.qualitymetrics;

public class NO2 extends Pollutants {
    public NO2(BAQI baqi, double concentration) {
        super(baqi, "Nitrogen Dioxide", "NO2", concentration, "ppb");
    }
}
