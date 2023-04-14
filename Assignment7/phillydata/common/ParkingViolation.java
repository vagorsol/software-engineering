package phillydata.common;

import java.util.Date;

public class ParkingViolation {
    private Date timestap;
    private int fine; 
    private String vioDesc; 
    private int vehicleID;
    private String state; 
    private int vioID;
    private int zipCode;

    public ParkingViolation(Date timestap, int fine, String vioDesc, int vehicleID, String state, int vioID, int zipCode) {
        this.timestap = timestap;
        this.fine = fine; 
        this.vioDesc = vioDesc; 
        this.vehicleID = vehicleID;
        this.state =  state; 
        this.vioID = vioID;
        this.zipCode = zipCode;
    }

    public Date getTimeStamp() {
        return timestap;
    }

    public int getFine() {
        return fine;
    }

    public String getViolationDesc() {
        return vioDesc;
    }
    
    public int getVehicleID() {
        return vehicleID;
    }

    public String getState() {
        return state; 
    }

    public int getViolationID() {
        return vioID;
    }

    public int getZip() {
        return zipCode;
    }
}
