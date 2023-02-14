public class State {
    private String name;
    private Double latitude; 
    private Double longitude; 

    public State(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude; 
        this.longitude = longitude; 
    }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude(){
        return longitude;
    }
}
