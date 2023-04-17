package phillydata.data;

import java.util.List;
import phillydata.common.ParkingViolation;

public interface ParkingReader {
    // TODO: might what to change List to Set so same vehicle ID w/multiple violations are in one object (i.e., in CSV/JSONParkingReader)
    public List<ParkingViolation> readParkingData(); 
}
