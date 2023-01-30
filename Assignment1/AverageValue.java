import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * takes two runtime arguments: int N, and filename 
 * file = CSV file
 * calculate & display average of numerical values in Nth field of each line of file (aka column)
 */

public class AverageValue {
    public static void main(String[] args) {
        // checks if user entered both filename AND field number (will not say which they forgot)
        if (args.length < 2) {
            System.out.println("Missing field and/or filename!");
            return; 
        }

        // makes sure that args[0] is an integer
        int N;
        try {
            N = Integer.parseInt(args[0]);
        } catch(NumberFormatException e) {
            System.out.println("Field number should be an integer!");
            return;
        }
        
        String filename = args[1];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            
        } catch (IOException e) {
            System.out.format("Unable to open %s! Please check that the filename is correct!\n", filename);
        }
        
    }
}
