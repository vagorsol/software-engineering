package phillydata.data;

import java.util.List;
import java.util.Map;

import phillydata.common.ParkingViolation;

public interface ParkingReader {
    public Map<Integer, List<ParkingViolation>> readParkingData(); 
}
