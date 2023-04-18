package phillydata.ui;

import java.util.Scanner;

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
            userInput = Integer.parseInt(in.nextLine());

            // todo:set them to prrocessors (or something)
            switch (userInput) {
                case 0:
                    System.out.println("Program Closing!");
                    looping = false; 
                    break; 
                case 1:
                    System.out.println("Total Population for All ZIP Codes");
                    break;
                case 2:
                    System.out.println("Fines Per Capita");
                    break;
                case 3:
                    System.out.println("Average Residential Market Value");
                    break;
                case 4: 
                    System.out.println("Average Residential Total Liveable Area");
                    break;
                case 5:
                    System.out.println("Residential Market Value Per Capita");
                    break;
                default:
                    System.out.println("Invalid Option! Please select one of the listed options!");
                    break;
            }
        }
    }
}


