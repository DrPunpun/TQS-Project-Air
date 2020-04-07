package tqs.project.air.airinfo.qualitymetrics;

public class PM10 extends Pollutants {
    public PM10(BAQI baqi, double concentration) {
        super(baqi, "Inhalable particulate matter (<10um)", "PM10", concentration, "ug/m3");
    }
}
