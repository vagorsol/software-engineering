package phillydata.common;

import java.util.Date;

public class ParkingViolation {
    private Date timestap;
    private Integer fine; 
    private String vioDesc; 
    private Integer vehicleID;
    private String state; 
    private Integer vioID;
    private Integer zipCode;

    public ParkingViolation(Date timestap, Integer fine, String vioDesc, Integer vehicleID, String state, Integer vioID, Integer zipCode) {
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

    public Integer getFine() {
        return fine;
    }

    public String getViolationDesc() {
        return vioDesc;
    }
    
    public Integer getVehicleID() {
        return vehicleID;
    }

    public String getState() {
        return state; 
    }

    public Integer getViolationID() {
        return vioID;
    }

    public Integer getZip() {
        return zipCode;
    }
}
