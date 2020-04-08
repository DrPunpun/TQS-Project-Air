package tqs.project.air.airinfo;

import java.util.Objects;

public class AirCoord {
    private double lon;
    private double lat;

    public AirCoord(double lat, double lon) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AirCoord)) return false;
        AirCoord airCoord = (AirCoord) o;
        return Double.compare(airCoord.lon, lon) == 0 &&
                Double.compare(airCoord.lat, lat) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lon, lat);
    }
}
