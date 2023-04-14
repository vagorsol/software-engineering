package phillydata.data;

import java.util.List;
import phillydata.common.ParkingViolation;

public interface Reader {
    public List<ParkingViolation> readPhillyData(); 
}
