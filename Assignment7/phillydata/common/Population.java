package phillydata.common;

public class Population {
    private int zipCode;
    private int population;

    public Population(int zipCode, int population) {
        this.zipCode = zipCode;
        this.population = population;
    }

    public int getZip() {
        return zipCode;
    }

    public int getPopulation() {
        return population;
    }
}
