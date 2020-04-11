package tqs.project.air.airinfo.qualitymetrics;

import java.util.Objects;

public abstract class Pollutants {
    private BAQI baqi;
    private String name;
    private String shorthand;
    private double concentration;
    private String concentrationunits;

    public Pollutants(BAQI baqi, String name, String shorthand, double concentration, String concentrationUnits) {
        this.baqi = baqi;
        this.name = name;
        this.shorthand = shorthand;
        this.concentration = concentration;
        this.concentrationunits = concentrationUnits;
    }

    public BAQI getBaqi() {
        return baqi;
    }

    public String getName() {
        return name;
    }

    public String getShorthand() {
        return shorthand;
    }

    public double getConcentration() {
        return concentration;
    }

    public String getConcentrationunits() {
        return concentrationunits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pollutants)) return false;
        Pollutants that = (Pollutants) o;
        return Double.compare(that.concentration, concentration) == 0 &&
                baqi.equals(that.baqi) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baqi, name, concentration);
    }
}
