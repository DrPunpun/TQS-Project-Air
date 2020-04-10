package tqs.project.air.airinfo.qualitymetrics;

import java.util.Objects;

public class BAQI {
    private int aqi;
    private String color;
    private String category;

    public BAQI(int aqi, String color, String category) {
        this.aqi = aqi;
        this.color = color;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BAQI)) return false;
        BAQI baqi = (BAQI) o;
        return aqi == baqi.aqi &&
                Objects.equals(color, baqi.color) &&
                Objects.equals(category, baqi.category);
    }

    @Override
    public String toString() {
        return "BAQI{" +
                "aqi=" + aqi +
                ", color='" + color + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(aqi, color, category);
    }

    public int getAqi() {
        return aqi;
    }

    public String getColor() {
        return color;
    }

    public String getCategory() {
        return category;
    }
}
