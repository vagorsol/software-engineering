import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
        double sum = 0; 
        int lineNo = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = null;
            
            while (((line = reader.readLine()) != null)) {
                String[] fmtLine = line.split(",");

                if (fmtLine.length < N) {
                    // checks if the Nth place on the line even exists
                    System.out.format("There is no field %d!", N); 
                    return; 
                } else if(fmtLine[N].isEmpty() || fmtLine[N] == null) {
                    // check if there is a (valid, i.e., not null or empty) value
                    System.out.println("All elements in %s should be filled (with numbers)!");
                    return;
                } else {
                    // tries to add the entry, and handles if the value is not a number 
                    try {
                        sum += Double.parseDouble(fmtLine[N]);
                        lineNo++;
                    } catch(NumberFormatException e) {
                        System.out.format("All elements in %s should be (real) numbers!\n", filename);
                        return;
                    }
                }
            }
        } catch (FileNotFoundException fnf){
            System.out.format("Unable to open %s! Please check that the filename is correct!\n", filename);
        }
        catch (IOException e) {
            System.out.format("There was a problem with reading the file! Please check that the file is formatted properly!\n", filename);
        }
        
        // output results - not sure if need to hold onto each field value?
        System.out.format("The average of the values in field #%d is %.3f\n", N, sum / lineNo);
    }
}
