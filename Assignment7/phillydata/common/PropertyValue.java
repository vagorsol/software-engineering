package phillydata.common;

public class PropertyValue {
    // i don't know what marketvalue + totallivablearea types are. maybe i should run a test to check
    private double marketValue;
    private double totalLivableArea; 
    private int zipCode; 

    public PropertyValue(double marketValue, double totalLivableArea, int zipCode) {
        this.marketValue = marketValue;
        this.totalLivableArea = totalLivableArea;
        this.zipCode = zipCode; 
    }

    public double getMarketValue() {
        return marketValue;
    } 

    public double getTotalLivableArea() {
        return totalLivableArea;
    }

    public int getZip() {
        return zipCode;
    }
}
