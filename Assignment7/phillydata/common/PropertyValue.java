package phillydata.common;

public class PropertyValue {
    private String marketValue;
    private String totalLivableArea; 
    private Integer zipCode; 

    public PropertyValue(String marketValue, String totalLivableArea, Integer zipCode) {
        this.marketValue = marketValue;
        this.totalLivableArea = totalLivableArea;
        this.zipCode = zipCode; 
    }

    public Double getMarketValue() {
        try {
            return Double.parseDouble(marketValue);
        } catch (NumberFormatException | NullPointerException e) {
            return null; 
        }
    } 

    public Double getTotalLivableArea() {
        try {
            return Double.parseDouble(totalLivableArea);
        } catch (NumberFormatException | NullPointerException e) {
            return null; 
        }
    }

    public Integer getZip() {
        return zipCode;
    }
}
