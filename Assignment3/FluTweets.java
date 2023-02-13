import java.io.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FluTweets {
    // javac --release 8 -cp "json-simple-1.1.1.jar;." FluTweets.java
    // java -cp "json-simple-1.1.1.jar;." FluTweets
    
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
