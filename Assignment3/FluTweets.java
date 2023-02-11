import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class FluTweets {
    
    public static void main(String[] args) {         
        // // get runtime (CL?) arguments 
        // if (args.length < 3) {
        //     System.out.println("Missing file information!");
        //     return;
        // }

        String datafn = "states.json"; // "";
        // String statefn = "";
        // String logfn = "";

        // // get the filenames
        // for (int i = 0; i < args.length; i++){
        //     if (args[i].contains("-datafile=") && datafn.isEmpty()) {
        //         String[] splitStr = args[i].split("=");
        //         // check if filename was include (ie. if empty or not)
        //         if (splitStr.length < 2) {
        //             break;
        //         } else {
        //             datafn = splitStr[1];
        //         }
        //     } else if (args[i].contains("-statesfile=") && statefn.isEmpty()) {
        //         String[] splitStr = args[i].split("=");

        //         if (splitStr.length < 2) {
        //             break;
        //         } else {
        //             statefn = splitStr[1];
        //         }
        //     } else if (args[i].contains("-logfile=") && logfn.isEmpty()) {
        //         String[] splitStr = args[i].split("=");

        //         if (splitStr.length < 2) {
        //             break;
        //         } else {
        //             logfn = splitStr[1];
        //         }
        //     }
        // }

        // // check if all the filenames have been given. if not, error message and exit
        // if (datafn.isEmpty() || statefn.isEmpty() || logfn.isEmpty()) {
        //     System.out.println("One or more file names is missing! Please check that you included them all!");
        //     return;
        // }
    
        // System.out.println("Data file name: " + datafn);
        // System.out.println("State file name: " + statefn);
        // System.out.println("Log file name: " + logfn); 


        // read data file - make DS?
        // TODO - figure out CL inputs + this because it got WEIRD :(
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(datafn));
            JSONArray ja = (JSONArray) obj;
            System.out.println(ja);

            // Iterator itt = ja.iterator();

            // while (itt.hasNext()){
            //     System.out.println(itt.next());
            // }
        } catch (FileNotFoundException fnf) {
            System.out.println("Could not find the file! Make sure you entered the right file name!");
            return;
        } catch (IOException e) {
            System.out.println("Cannot open the file!");
            return;
        } catch (org.json.simple.parser.ParseException e) {
            System.out.println("Cannot open the file!");
            return;
        }
    }
}
