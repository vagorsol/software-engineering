public class FluTweet {
    // my class naming will *definitly* not confuse anyone (lying)

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
