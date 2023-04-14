package phillydata.data;

import java.util.List;
import phillydata.common.ParkingViolation;

public interface ParkingReader {
    public List<ParkingViolation> readParkingData(); 
}
