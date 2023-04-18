package phillydata.ui;

import java.util.*;

import phillydata.processor.PhillyDataProcessor;

public class PhillyDataUI {
    protected PhillyDataProcessor processor;
    protected Scanner in; 
    
    public PhillyDataUI(PhillyDataProcessor processor) {
        this.processor = processor;
        in = new Scanner(System.in);
    }

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

    public void start() {
        boolean looping = true;
        int userInput;

        while (looping) {
            menu();

            // checks if is a number
            try {
                userInput = Integer.parseInt(in.nextLine());
            } catch(NumberFormatException | NullPointerException e) {
                System.out.println("Invalid Option! Please select one of the listed options! \n");
                continue;
            }
            
            System.out.println(); // output readability

            // todo:set them to prrocessors (or something)
            switch (userInput) {
                case 0:
                    System.out.println("Program Closing!");
                    looping = false; 
                    break; 
                case 1:
                    doTotalPopZIP();
                    break;
                case 2:
                    doFinesPerCapita();;
                    break;
                case 3:
                    System.out.println("Average Residential Market Value");
                    break;
                case 4: 
                    System.out.println("Average Residential Total Liveable Area");
                    break;
                case 5:
                    doMarketValPerCapitca();
                    break;
                default:
                    System.out.println("Invalid Option! Please select one of the listed options!");
                    break;
            }
        }
    }

    /**
     * Menu Option #1: displays the total population of all the ZIP codes in population input file
     */
    public void doTotalPopZIP() {
        int sum = processor.getTotalPop();
        System.out.println("The Total Population for all ZIP codes is: " + sum + "\n");
    }

    /**
     * For each ZIP code, give the aggregate fines
     */
    public void doFinesPerCapita() {
        Map<Integer, Double> ret = processor.getFinesPerCapita();
        
        for(Map.Entry<Integer, Double> p : ret.entrySet()) {
            System.out.printf("%s \t %.4f\n", p.getKey(), p.getValue());
        }
    }

    /**
     * Displays the residential market value per capita for a user-inputted ZIP code
     * (i.e., total market value for all residences in the ZIP code divided by the population of that ZIP Code)
     */
    public void doMarketValPerCapitca() {
        System.out.print("Enter a ZIP Code: ");
        
        int zipcode; 

        try {
            zipcode = Integer.parseInt(in.nextLine());
        } catch(NumberFormatException | NullPointerException e) {
            System.out.println("Invalid zipcode! Please enter a zipcode! \n");
            return;
        }
        
        int marketVal = processor.getMarketValPerCapita(zipcode);

        System.out.printf("Residential Market Value per Capita of %d: %d \n", zipcode, marketVal);

    }
}


