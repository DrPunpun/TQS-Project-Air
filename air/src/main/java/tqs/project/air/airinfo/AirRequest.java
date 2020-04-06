package tqs.project.air.airinfo;

import java.util.Objects;

public class AirRequest implements Comparable<AirRequest>{
    private int miss;
    private int hit;
    private long requestDate;
    private String data;


    public AirRequest(String data) {
        this.data = data;
        this.miss = 1;
        this.requestDate = System.currentTimeMillis();
        this.hit = 0;
    }

    public String getData(){
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getRequestDate(){
        return this.requestDate;
    }

    public void setRequestDate(long requestDate){
        this.requestDate = requestDate;
    }

    public int getMiss() {
        return miss;
    }

    public int getHit() {
        return hit;
    }

    public void hit(){
        this.hit++;
    }

    public void miss() {
        this.miss++;
    }

    @Override
    public int compareTo(AirRequest o) {
        return (this.hit+this.miss) - (o.miss + o.hit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AirRequest)) return false;
        AirRequest that = (AirRequest) o;
        return miss == that.miss &&
                hit == that.hit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(miss, hit);
    }
}
