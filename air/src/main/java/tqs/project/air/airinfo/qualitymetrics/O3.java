package tqs.project.air.airinfo.qualitymetrics;

public class O3 extends Pollutants {
    public O3(BAQI baqi, double concentration) {
        super(baqi, "Ozone", "O3", concentration, "ppb");
    }
}
