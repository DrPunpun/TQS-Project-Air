package tqs.project.air.airinfo.qualitymetrics;

public class SO2 extends Pollutants {
    public SO2(BAQI baqi, double concentration) {
        super(baqi, "Sulfur Dioxide", "SO2", concentration, "ppb");
    }
}
