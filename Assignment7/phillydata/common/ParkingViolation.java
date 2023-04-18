package phillydata.common;

import java.util.Date;

public class ParkingViolation {
    private Date timestap;
    private String fine; 
    private String vioDesc; 
    private String vehicleID;
    private String state; 
    private String vioID;
    private Integer zipCode;

    public ParkingViolation(Date timestap, String fine, String vioDesc, String vehicleID, String state, String vioID, Integer zipCode) {
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
        try {
            return Integer.parseInt(fine);
        } catch (NumberFormatException | NullPointerException e) {
            return null; 
        }
    }

    public String getViolationDesc() {
        return vioDesc;
    }
    
    public Integer getVehicleID() {
        try {
            return Integer.parseInt(vehicleID);
        } catch (NumberFormatException | NullPointerException e) {
            return null; 
        }
    }

    public String getState() {
        return state; 
    }

    public Integer getViolationID() {
        try {
            return Integer.parseInt(vioID);
        } catch (NumberFormatException | NullPointerException e) {
            return null; 
        }
    }

    public Integer getZip() {
        return zipCode;
    }
}
