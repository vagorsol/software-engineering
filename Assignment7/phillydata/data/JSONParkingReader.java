package phillydata.data;

import java.io.FileReader;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import phillydata.common.ParkingViolation;

public class JSONParkingReader implements ParkingReader {
    private String filename;

    public JSONParkingReader(String filename) {
        this.filename = filename;
    }

    @SuppressWarnings({"deprecation", "unchecked"})
    public List<ParkingViolation> readParkingData() {
        if (filename == null) {
            throw new IllegalThreadStateException("Filename not specificed!");
        }

        List<ParkingViolation> ret = new LinkedList<>(); 

        try {
            Object obj = new JSONParser().parse(new FileReader(filename));
            JSONArray arr = (JSONArray) obj;
            Iterator<JSONObject> iter = arr.iterator(); 
            while (iter.hasNext()) {
                JSONObject o  = iter.next(); 

                String time = (String) o.get("date");
                String[] timeSplit = time.split("T");
                String[] timeDate = timeSplit[0].split("-"); // get year/month/day
                String[] timeTime = timeSplit[1].split(":"); // get hour/min/sec
                Date timestap = new Date(
                    Integer.parseInt(timeDate[0]), Integer.parseInt(timeDate[1]), Integer.parseInt(timeDate[2]), 
                    Integer.parseInt(timeTime[0]), Integer.parseInt(timeTime[1]), Integer.parseInt(timeTime[2]));
                int fine = (int) o.get("fine"); // get fine
                String vioDesc = (String) o.get("violation"); // get violation description
                int vehicleID = (int) o.get("plate_id"); // get vehicle ID
                String state = (String) o.get("state"); // get state
                int vioID = (int) o.get("ticket_number"); // get violation ID
                int zipCode; // get zipcode, if none then set it to -1
                if (o.get("zip_code") != null) {
                    zipCode = (int) o.get("zip_code");
                } else {
                    zipCode = -1;
                }
                ParkingViolation vioToAdd = new ParkingViolation(timestap, fine, vioDesc, vehicleID, state, vioID, zipCode);

                ret.add(vioToAdd);
            }

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        return ret; 
    }
}
