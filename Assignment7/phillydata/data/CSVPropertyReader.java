package phillydata.data;

import java.io.File;
import java.util.*;

import phillydata.common.PropertyValue;

public class CSVPropertyReader {
    private String filename;

    public CSVPropertyReader(String filename) {
        this.filename = filename;
    }

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
                    } else if (linesplit[i].equals("total_liveable_area")) {
                        liveAreaField = i;
                    } else if (linesplit[i].equals("zip_code")) {
                        zipField = i;
                    }
                }
            }

            while (in.hasNext()) {
                String line = in.nextLine();
                String[] linesplit = line.split(",");

                double marketVal = Double.parseDouble(linesplit[marketValField]);
                double livableArea = Double.parseDouble(linesplit[liveAreaField]);
                int zipCode = Integer.parseInt(linesplit[zipField]);

                PropertyValue vioToAdd = new PropertyValue(marketVal, livableArea, zipCode);

                ret.add(vioToAdd);

            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        
        return ret; 
    }
}
