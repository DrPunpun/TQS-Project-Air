package tqs.project.air.airinfo;

public class AirRequest implements Comparable<AirRequest>{
    private int miss, hit;
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
}
