package phillydata.common;

public class PropertyValue {
    private Double marketValue;
    private Double totalLivableArea; 
    private Integer zipCode; 

    public PropertyValue(Double marketValue, Double totalLivableArea, Integer zipCode) {
        this.marketValue = marketValue;
        this.totalLivableArea = totalLivableArea;
        this.zipCode = zipCode; 
    }

    public Double getMarketValue() {
        return marketValue;
    } 

    public Double getTotalLivableArea() {
        return totalLivableArea;
    }

    public Integer getZip() {
        return zipCode;
    }
}
