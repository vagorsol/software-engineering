package phillydata.data;

import java.io.File;
import java.util.*;

import phillydata.common.PropertyValue;

public class CSVPropertyReader {
    private String filename;

    public CSVPropertyReader(String filename) {
        this.filename = filename;
    }

    // TODO: if have time, try to make this not take 500 years
    public List<PropertyValue> readPropertyData() {
        if (filename == null) {
            throw new IllegalStateException("Filename not specified!");
        } 
        
        List<PropertyValue> ret = new LinkedList<>();

        int marketValField = 0, liveAreaField = 0, zipField = 0;


        try (Scanner in = new Scanner(new File(filename))) {
            // get the first line
            if (in.hasNext()) {
                String line = in.nextLine(); 
                String[] linesplit = line.split(",");
                
                for(int i = 0; i < linesplit.length; i++) {
                    if (linesplit[i].equals("market_value")) {
                        marketValField = i;
                    } else if (linesplit[i].equals("total_livable_area")) {
                        liveAreaField = i;
                    } else if (linesplit[i].equals("zip_code")) {
                        zipField = i;
                    }
                }
            }

            while (in.hasNext()) {
                String line = in.nextLine();
                String[] linesplit = line.split(",");

                // initialize values in case empty: negative means no value (probably no negative values in csv but too difficult to check)
                Double marketVal = null;
                Double livableArea = null;
                Integer zipCode = null;

                if (!linesplit[marketValField].isEmpty()) {
                    marketVal =  Double.parseDouble(linesplit[marketValField]);
                } else {
                    marketVal = null;
                }

                if (!linesplit[liveAreaField].isEmpty()){
                    livableArea = Double.parseDouble(linesplit[liveAreaField]);
                } else {
                    livableArea = null;
                }

                if (!linesplit[zipField].isEmpty()){
                    String[] zipString = linesplit[zipField].split("-");
                    String[] zipStringSpace = zipString[0].split(" ");
                    for (String zss:zipStringSpace) {
                        if (!zss.isEmpty()) {
                            String[] zipStringRegEx = zss.split("[a-zA-Z]+");
                            zipCode = Integer.parseInt(zipStringRegEx[0]);
                        }
                    }
                } else {
                    zipCode = null;
                }

                PropertyValue vioToAdd = new PropertyValue(marketVal, livableArea, zipCode);
                ret.add(vioToAdd);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        
        return ret; 
    }

    public static void main(String[] args) {
        CSVPropertyReader pr = new CSVPropertyReader("properties.csv");
        List<PropertyValue> lst = pr.readPropertyData();
        PropertyValue pv = lst.get(0);

        // verifying data
        System.out.println("Market Value: " + pv.getMarketValue()); 
        System.out.println("Total Livable Area: " + pv.getTotalLivableArea());
        System.out.println("Zip Code: " + pv.getZip());
    }
}
