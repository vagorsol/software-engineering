import java.io.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FluTweets {
    // javac --release 8 -cp "json-simple-1.1.1.jar;." FluTweets.java
    // java -cp "json-simple-1.1.1.jar;." FluTweets

    /**
     * Goes through a list of tweets and adds only if they have the word "flu" in them
     * A tweet is considered to contain "flu" if:
     *      - contains word "flu" in start, middle, or end
     *      - contains the hashtag #flu 
     *      - contains a string that starts with "flu" and followed by a character that is not a letter
     *        (e.g. "flu!")
     * @param tweets A list of tweets of the Tweet type
     * @return a list of Tweet objects that contain "flu" according to the above parameters. 
     */
    public static List<Tweet> findFluTweets(List<Tweet> tweets){
        List<Tweet> fluTweets = new ArrayList<>();

        // returns an empty list if tweets is empty or null
        if (tweets == null || tweets.isEmpty()) {
            return fluTweets; 
        }

        for (Tweet tweet : tweets) {
            // check if there even is a sentence there
            if (tweet.getText().isEmpty() || tweet.getText() == null) {
                continue; 
            }

            // get and splice the string in one go - should protect from bad code later 
            String[] text = tweet.getText().split(" ");
            // System.out.println(text);
            for (String word : text) {
                if (word.matches("[fF]lu\\W*") || word.matches("#[fF]lu")) {
                    fluTweets.add(tweet);
                    break; 
                }
            }
        }

        return fluTweets;
    }
    
    public static void main(String[] args) {         
        // get runtime (CL?) arguments 
        if (args.length < 3) {
            System.out.println("Missing file information!");
            return;
        }

        String statefn = ""; 
        String datafn = ""; 
        String logfn = ""; 

        // get the filenames
        for (int i = 0; i < args.length; i++){
            if (args[i].contains("-datafile=") && datafn.isEmpty()) {
                String[] splitStr = args[i].split("=");
                // check if filename was include (ie. if empty or not)
                if (splitStr.length < 2) {
                    break;
                } else {
                    datafn = splitStr[1];
                }
            } else if (args[i].contains("-statesfile=") && statefn.isEmpty()) {
                String[] splitStr = args[i].split("=");

                if (splitStr.length < 2) {
                    break;
                } else {
                    statefn = splitStr[1];
                }
            } else if (args[i].contains("-logfile=") && logfn.isEmpty()) {
                String[] splitStr = args[i].split("=");

                if (splitStr.length < 2) {
                    break;
                } else {
                    logfn = splitStr[1];
                }
            }
        }

        // check if all the filenames have been given. if not, error message and exit
        if (datafn.isEmpty() || statefn.isEmpty() || logfn.isEmpty()) {
            System.out.println("One or more files is missing!");
            return;
        }

        JSONParser parser = new JSONParser();
        
        // read the data file and put it in a data structure
        HashMap<String, Double[]> states = new HashMap<String, Double[]>();

        try {
            Object obj = parser.parse(new FileReader(statefn));
            JSONArray ja = (JSONArray) obj;

            Iterator itt = ja.iterator();

            while (itt.hasNext()){
                JSONObject jo = (JSONObject) itt.next();
                states.put((String) jo.get("name"), new Double[]{(Double) jo.get("latitude"), (Double) jo.get("longitude")});
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("Error reading states file");
            return;
        } catch (IOException e) {
            System.out.println("Error reading states file");
            return;
        } catch (org.json.simple.parser.ParseException e) {
            System.out.println("Error reading states file");
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("Error reading states file");
            return;
        }

        // read the tweets file and put them in a list
        List<Tweet> tweets = new ArrayList<>(); 
        
        try {
            Object obj = parser.parse(new FileReader(datafn));
            JSONArray ja = (JSONArray) obj;

            Iterator itt = ja.iterator();

            while (itt.hasNext()) {
                JSONObject jo = (JSONObject) itt.next(); 

                // get location
                JSONArray jar = (JSONArray) jo.get("location");
                Double[] loc = new Double[2];
                for (int i = 0; i < 2; i++) {
                    loc[i] = (Double) jar.get(i);
                }

                tweets.add(new Tweet(loc, (String) jo.get("time"), (String) jo.get("text")));
            }

        } catch (FileNotFoundException fnf) {
            System.out.println("Error reading tweets file");
            return;
        } catch (IOException e) {
            System.out.println("Error reading tweets file");
            return;
        } catch (org.json.simple.parser.ParseException e) {
            System.out.println("Error reading tweets file");
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("Error reading tweets file");
            return;
        }

        // go through tweets and get all ones with "flu" in them
        List<Tweet> fluTweets = findFluTweets(tweets);

        // TODO: logging tweets
        try {
            BufferedWriter log = new BufferedWriter(new FileWriter(logfn));
            log.write("test successful!"); // test text
            log.close(); 
            System.out.println("Check your files");
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }
}
