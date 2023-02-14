/**
 * Object type to store the information of a tweet
 */

public class Tweet {
    // information in the tweet
    private Double latitude;
    private Double longitude; 
    private String time;
    private String text;

    public Tweet(Double latitude, Double longitude, String time, String text) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
        this.text = text;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }


    public String getTime() {
        return time;
    }

    public String getText() {
        return text;
    }


}
