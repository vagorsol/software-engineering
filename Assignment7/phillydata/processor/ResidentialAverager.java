package phillydata.processor;

import java.util.List;

import phillydata.common.PropertyValue;

public interface ResidentialAverager {
    public int avgResidential(int houses, List<PropertyValue> pv);
}
