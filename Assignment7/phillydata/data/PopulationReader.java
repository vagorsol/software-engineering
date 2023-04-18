package phillydata.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import phillydata.common.Population;

public class PopulationReader {
    private String filename;

    public PopulationReader(String filename) {
        this.filename = filename;
    }

    public List<Population> readPopulationData() {
        if (filename == null) {
            throw new IllegalStateException("Filename not specified!");
        } 

        List<Population> ret = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = null;

            while ((line = reader.readLine()) != null) {
                String[] fmtLine = line.split(" ");
                try {
                    Population popToAdd = new Population(Integer.parseInt(fmtLine[0]), Integer.parseInt(fmtLine[1]));
                    ret.add(popToAdd);
                } catch(NumberFormatException | NullPointerException e) {
                    continue; 
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return ret; 
    }
    

    public static void main(String[] args) {
        PopulationReader pr = new PopulationReader("population.txt");
        List<Population> lst = pr.readPopulationData();
        Population p = lst.get(0);

        //verifying data
        System.out.println("Zip Code: " + p.getZip()); // 19102
        System.out.println("Population: " + p.getPopulation()); // 4705
    }
}
