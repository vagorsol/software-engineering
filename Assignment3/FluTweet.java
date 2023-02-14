/**
 * Object type to store the contents of and the state that a 
 * "flu tweet" is from
 * @author aqyang
 */

public class FluTweet {

    private String state;
    private String text;

    public FluTweet(String state, String text){
        this.state = state;
        this.text = text;
    }

    public void setState(String state) {
        this.state = state; 
    }

    public String getState() {
        return state;
    }

    public String getText() {
        return text;
    }
}
