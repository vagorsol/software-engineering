package phillydata.processor;

import java.io.IOError;
import java.util.List;

import phillydata.common.*;
import phillydata.data.*;

public class PhillyDataProcessor {
    protected ParkingReader parkingReader;
    protected PropertyReader propertyReader;
    protected PopulationReader populationReader;

    protected List<ParkingViolation> parkingViolations;
    protected List<PropertyValue> propertyValue;
    protected List<Population> population;

    public PhillyDataProcessor(ParkingReader parkingReader, PropertyReader propertyReader, PopulationReader populationReader) {
        this.parkingReader = parkingReader;
        this.propertyReader = propertyReader;
        this.populationReader = populationReader;

        try {
            parkingViolations = parkingReader.readParkingData();
            propertyValue = propertyReader.readPropertyData();
            population = populationReader.readPopulationData();
        } catch (IllegalStateException e) {
            System.out.println("Error reading the file!");
            return; 
        } catch (IOError | SecurityException e) {
            System.out.println("Cannot open file for reading!");
            return;
        }
        // TODO: how the catch error cases are going to work...
    }
}
