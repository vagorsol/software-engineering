package phillydata.data;

import java.io.File;
import java.util.*;

import phillydata.common.ParkingViolation;

public class CSVParkingReader implements ParkingReader {
    private String filename;

    public CSVParkingReader(String filename) {
        this.filename = filename;
    }

    @SuppressWarnings("deprecation")
    public Map<String, List<ParkingViolation>> readParkingData() {
        if (filename == null) {
            throw new IllegalStateException("Filename not specified!");
        } 
        
        Map<String, List<ParkingViolation>> ret = new TreeMap<>();

        try (Scanner in = new Scanner(new File(filename))) {
            while (in.hasNext()) {
                String line = in.nextLine();
                String[] linesplit = line.split(",");

                // get and format the timestap
                String[] timeSplit = linesplit[0].split("T");
                String[] timeDate = timeSplit[0].split("-"); // get year/month/day
                String[] timeTime = timeSplit[1].split(":"); // get hour/min/sec
                Date timestap = new Date(
                    Integer.parseInt(timeDate[0]), Integer.parseInt(timeDate[1]), Integer.parseInt(timeDate[2]), 
                    Integer.parseInt(timeTime[0]), Integer.parseInt(timeTime[1]), Integer.parseInt(timeTime[2].substring(0, 2)));
                String fine = linesplit[1]; // get fine
                String vioDesc = linesplit[2]; // get violation description
                String vehicleID = linesplit[3]; // get vehicle ID
                String state = linesplit[4]; // get state
                String vioID = linesplit[5]; // get violation ID
                Integer zipCode; 
                if (linesplit.length <= 6) {
                    zipCode = null; // if there is no zipe code, set it to a negative value (which is abt the same as setting it as null here)
                } else {
                    zipCode = Integer.parseInt(linesplit[6]);
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

    public static void main(String[] args){
        CSVParkingReader js = new CSVParkingReader("parking.csv");
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