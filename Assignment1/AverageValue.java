import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Calculates & display the average of numerical values in the Nth field of each line of the file (aka column)
 */
public class AverageValue {
    public static void main(String[] args) {
        // checks if user entered both filename AND field number (will not say which they forgot)
        if (args.length < 2) {
            System.out.println("Invalid data");
            return; 
        }

        // makes sure that args[0] is a (positive) number
        int N;
        try {
            N = Integer.parseInt(args[0]);
            if (N < 0) {
                System.out.println("Invalid data");
                return;
            }
        } catch(NumberFormatException e) {
            System.out.println("Invalid data");
            return;
        }
        
        String filename = args[1];
        double sum = 0; 
        int lineNo = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = null;
            
            while (((line = reader.readLine()) != null)) {
                String[] fmtLine = line.split(",");

                // System.out.println(fmtLine.length + " " + N);
                if (fmtLine.length <= N) {
                    // checks if the Nth place on the line even exists - if not, ignores and continues
                    continue; 
                } else if(fmtLine[N].isEmpty() || fmtLine[N] == null) {
                    continue;
                } else {
                    // tries to add the entry, ignores if not a number
                    try {
                        sum += Double.parseDouble(fmtLine[N]);
                        lineNo++;
                    } catch(NumberFormatException e) {
                        continue;
                    }
                }
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("Invalid data");
        }
        catch (IOException e) {
            System.out.println("Invalid data");
        }
        
        if (lineNo == 0) {
            System.out.println("Invalid data");
        } else {
            // output results - I am assuming we do not need to output the given field value for each line
            System.out.format("The average of the values in field #%d is %.3f\n", N, sum / lineNo);
        }
        
    }
}
