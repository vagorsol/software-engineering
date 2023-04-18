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
    public Map<String, List<ParkingViolation>> readParkingData() {
        if (filename == null) {
            throw new IllegalThreadStateException("Filename not specificed!");
        }

        Map<String, List<ParkingViolation>> ret = new TreeMap<>(); 

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
                    Integer.parseInt(timeTime[0]), Integer.parseInt(timeTime[1]), Integer.parseInt(timeTime[2].substring(0, 2)));
                String fine = Long.toString((Long) o.get("fine")); // get fine
                String vioDesc = (String) o.get("violation"); // get violation description
                String vehicleID = (String) o.get("plate_id"); // get vehicle ID
                String state = (String) o.get("state"); // get state
                String vioID = Long.toString((Long) o.get("ticket_number")); // get violation ID
                Integer zipCode; // get zipcode, if none then set it to -1
                if (o.get("zip_code") != null) {
                    String zipString = (String) o.get("zip_code");
                    if (zipString.isEmpty()) {
                        zipCode = null;
                    } else {
                        zipCode = Integer.parseInt(zipString);
                    }
                } else {
                    zipCode = null;
                }
                ParkingViolation vioToAdd = new ParkingViolation(timestap, fine, vioDesc, vehicleID, state, vioID, zipCode);

                if (ret.containsKey(vehicleID)){
                    List<ParkingViolation> lst = ret.get(vehicleID);
                    lst.add(vioToAdd);
                    ret.put(vehicleID, lst);
                } else {
                    List<ParkingViolation> lst = new ArrayList<>();
                    lst.add(vioToAdd);
                    ret.put(vehicleID, lst);
                }
            }

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        return ret; 
    }

    // testing
    public static void main(String[] args) {
        JSONParkingReader js = new JSONParkingReader("parking.json");
        Map<String, List<ParkingViolation>> lst = js.readParkingData(); 
        List<ParkingViolation> plst = lst.get("1322731");

        // verifying data
        for (ParkingViolation p : plst){
            System.out.println("Timestap: " + p.getTimeStamp()); // 2013 04 03 / 15:15:00
            System.out.println("Fine: " + p.getFine()); // 36
            System.out.println("Violation Description: " + p.getViolationDesc()); // meter expired CC
            System.out.println("Vehicle ID: " + p.getVehicleID()); // 1322731
            System.out.println("State: " + p.getState()); // PA
            System.out.println("Violation ID: " + p.getViolationID()); // 2905928
            System.out.println("Vehicle ID: " + p.getZip()); // 19104
        }
    }
}
