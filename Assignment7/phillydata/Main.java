package phillydata;

import phillydata.common.*;
import phillydata.data.*;
import phillydata.ui.PhillyDataUI;

import java.io.IOError;
import java.util.List;
import java.util.Scanner;

public class Main {

    // function to make main look *SLIGHTLY* nicer. literally just prints out a menu
    private static void menu() {
        System.out.println("Menu");
        System.out.println("1: Total Population for All ZIP Codes");
        System.out.println("2: Fines Per Capita");
        System.out.println("3: Average Residential Market Value");
        System.out.println("4: Average Residential Total Liveable Area");
        System.out.println("5: Residential Market Value Per Capita");
        System.out.println("0: Exit");
        System.out.print("Select an Option: ");
    }
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

        CSVPropertyReader propertyReader = new CSVPropertyReader(propertyFile);
        PopulationReader populationReader = new PopulationReader(populationFile);

        // open and read files
        List<ParkingViolation> parkingViolations;
        List<PropertyValue> propertyValue; 
        List<Population> populationValue;

        try {
            parkingViolations = parkingReader.readParkingData();
            propertyValue = propertyReader.readPropertyData();
            populationValue = populationReader.readPopulationData();
        } catch (IllegalStateException e) {
            System.out.println("Error reading the file!");
            return; 
        } catch (IOError | SecurityException e) {
            System.out.println("Cannot open file for reading!");
            return;
        }

        // probably need proccessors but that's me in 30 minute's issue
        PhillyDataUI ui = new PhillyDataUI();

        ui.start();
    }
}
