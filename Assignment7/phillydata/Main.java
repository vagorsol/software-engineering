package phillydata;

import phillydata.data.*;

public class Main {
    public static void main(String[] args) {
        if (args.length < 4){
            System.out.println("Missing inputs!");
            return;
        }
        if (args.length > 4) {
            System.out.println("Too many inputs!");
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
        PopulationReader popReader = new PopulationReader(populationFile);

    
    }
}
