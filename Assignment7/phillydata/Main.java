package phillydata;

import phillydata.common.*;
import phillydata.data.*;
import phillydata.processor.PhillyDataProcessor;
import phillydata.ui.PhillyDataUI;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        if (args.length < 4){
            System.out.println("Missing inputs!");
            System.out.println("Usage: java Main" +
                "[parking violation file format] [parking violations file name]"
                + "[property values input file name] [population input file name]");
            return; 
        }
        if (args.length > 4) {
            System.out.println("Too many inputs!");
            System.out.println("Usage: java Main" +
                "[parking violation file format] [parking violations file name]"
                + "[property values input file name] [population input file name]");
            return;
        }

        ParkingReader parkingReader; 

        // get parking file & format
        String filefmt = args[0];
        String parkingFile = args[1];

        if(filefmt.equals("csv")) {
            parkingReader = new CSVParkingReader(parkingFile);
        } else if (filefmt.equals("json")) {
            parkingReader = new JSONParkingReader(parkingFile);
        } else {
            System.out.println("Parking violation file format not specified! Make sure it is capitalized properly!");
            return; 
        }

        // get the other files
        String propertyFile = args[2];
        String populationFile = args[3];

        PropertyReader propertyReader = new PropertyReader(propertyFile);
        PopulationReader populationReader = new PopulationReader(populationFile);


        // start the program
        PhillyDataProcessor processor = new PhillyDataProcessor(parkingReader, propertyReader, populationReader);
        PhillyDataUI ui = new PhillyDataUI(processor);

        ui.start();
    }
}
