package phillydata.processor;

import java.io.IOError;
import java.util.*;

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
     * For each zip code, get the average fines divided by the population of that zipcode
     * @return ret, zipcode with associated average amount of fines
     */
    public Map<Integer, Double> getFinesPerCapita() {
        // return set
        Map<Integer, Double> ret = new TreeMap<>();

        // get all ZipCodes
        Map<Integer, Integer> zips = new HashMap<>();
        
        for(Population p : population){
            ret.put(p.getZip(), 0.0);
            zips.put(p.getZip(), 0);
        }

        // add all fines to the map
        for(Map.Entry<Integer, List<ParkingViolation>> parkingVio : parkingViolations.entrySet()) {
            List<ParkingViolation> pvlst = parkingVio.getValue(); 
            for (ParkingViolation pv : pvlst) {
                Integer zipCode = pv.getZip();
                if(zipCode != null && zips.containsKey(zipCode)) {
                    Integer val = zips.get(zipCode);
                    val = val +  pv.getFine();
                    // System.out.println(zipCode + " " + pv.getFine() + " " + val);
                    zips.put(zipCode, val);
                }
            }
        }

        // calculate the average of all the fines
        // Fun Fact! technically Math.round sometimes truncates! however, writing my own rounder hurt my head so Sure
        for(Map.Entry<Integer, Integer> z : zips.entrySet()) {
            Integer val = z.getValue(); // total fines
            Integer pop = getPopByZip(z.getKey()); // population at zipcode
            
            Double avg = ((double) val / (double) pop); 
            avg = ((double) Math.round(avg * 10000)) / 10000; 
            ret.put(z.getKey(), avg);
        }

        return ret; 
    }

    public int getResidentialAverage(int zip, ResidentialAverager averager) {
        int houses = 0;

        List<PropertyValue> pv = new ArrayList<>();
        for (PropertyValue p : propertyValue) {
            if (p.getZip() == zip) {
                pv.add(p);
                houses++;
            }
        }
        
        // if no houses then no average (and no division error)
        if (houses == 0) {
            return 0;
        }

        // call averager here w specification as to which value to divide by
        int avg = averager.avgResidential(houses, pv);
        return avg;
    }

    /**
     * Displays the residential market value per capita for a user-inputted ZIP code
     * (i.e., total market value for all residences in the ZIP code divided by the population of that ZIP Code)
     * @param zip, the ZIP to get the market per capita at
     * @return ret, the rounded integer market value per capita value
     */
    public int getMarketValPerCapita(int zip) {
        int popAtZIP = getPopByZip(zip);
        if (popAtZIP == 0) {
            return 0;
        }        

        double sum = 0.0; 

        for (PropertyValue p : propertyValue) {
            if (p.getZip() == zip) {
                sum += p.getMarketValue();
            }
        }

        // round the market value per capita value
        double avg = sum / popAtZIP;
        int ret = (int) ((double) Math.round(avg * 10000)) / 10000; 
        
        return ret;
    }

    /**
     * Given a ZIP code, returns corresponding population in the population input file
     * @param zip
     * @return population iff that zip code exists in the population input file, else returns 0
     */
    protected int getPopByZip(int zip) {
        for(Population p : population) {
            if (p.getZip() == zip){
                return p.getPopulation();
            }
        }
        return 0;
    }
}
