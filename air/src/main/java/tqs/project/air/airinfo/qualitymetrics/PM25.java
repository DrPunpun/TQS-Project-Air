package tqs.project.air.airinfo.qualitymetrics;

public class PM25 extends Pollutants {
    public PM25(BAQI baqi, double concentration) {
        super(baqi, "Fine particulate matter (<2.5um)", "PM2.5", concentration, "ug/m3");
    }
}
