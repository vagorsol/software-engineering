/**
 * Object type to store the information of a tweet
 */

public class Tweet {
    // information in the tweet
    private Double[] location; // formatted [latitude, longitude]
    private String time;
    private String text;

    public Tweet(Double[] location, String time, String text) {
        this.location = location;
        this.time = time;
        this.text = text;
    }

    public Double[] getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public void setLattitude(double lat) {
        this.location[0] = lat;
    }

    public void setLongitude(double longitude) {
        this.location[1] = longitude;
    }
}
