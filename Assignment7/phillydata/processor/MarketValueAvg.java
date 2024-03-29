package phillydata.processor;

import java.util.List;

import phillydata.common.PropertyValue;

public class MarketValueAvg implements ResidentialAverager {
    public int avgResidential(int houses, List<PropertyValue> pv) { 
        double sum = 0.0;
        for (PropertyValue p : pv) {
            if (p.getMarketValue() != null) {
                sum += p.getMarketValue();
            }
        }
        double avg = sum / houses;
        return (int) ((double) Math.round(avg * 10000)) / 10000;
    }
}
