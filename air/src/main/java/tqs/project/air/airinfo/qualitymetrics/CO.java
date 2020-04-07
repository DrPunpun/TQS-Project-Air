package tqs.project.air.airinfo.qualitymetrics;

public class CO extends Pollutants{
    public CO(BAQI baqi, double concentration) {
        super(baqi, "Carbon Monoxide", "CO", concentration, "ppb");
    }

}
