package phillydata.processor;

import java.io.IOError;
import java.util.List;
import java.util.Map;

import phillydata.common.*;
import phillydata.data.*;

public class PhillyDataProcessor {
    protected ParkingReader parkingReader;
    protected PropertyReader propertyReader;
    protected PopulationReader populationReader;

    protected Map<Integer, List<ParkingViolation>> parkingViolations;
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
            System.exit(1); 
        } catch (IOError | SecurityException e) {
            System.out.println("Cannot open file for reading!");
            System.exit(1);;
        }
    }

    /**
     * Returns the total population for all of the ZIP Codes in the population input file
     * @return sum
     */
    public int getTotalPop() {
        int sum = 0;

        for(Population p : population) {
            sum += p.getPopulation();
        }

        return sum;
    }

    /**
     * Given a ZIP code, returns corresponding population in the population input file
     * @param zip
     * @return population iff that zip code exists in the population input file, else returns 0
     */
    public int getPopByZip(int zip) {
        for(Population p : population) {
            if (p.getZip() == zip){
                return p.getPopulation();
            }
        }
        return 0;
    }

    
}
