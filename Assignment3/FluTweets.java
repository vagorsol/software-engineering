public class FluTweets {
    
    public static void main(String[] args) {         
        // get runtime (CL?) arguments 
        if (args.length < 3) {
            System.out.println("Missing file information!");
            return;
        }

        String datafn = "";
        String statefn = "";
        String logfn = "";

        // get the filenames
        for (int i = 0; i < args.length; i++){
            if (args[i].contains("-datafile=") && datafn.isEmpty()) {
                String[] splitStr = args[i].split("=");
                // check if filename was include (ie. if empty or not)
                if (splitStr.length < 2) {
                    break;
                } else {
                    datafn = splitStr[1];
                }
            } else if (args[i].contains("-statesfile=") && statefn.isEmpty()) {
                String[] splitStr = args[i].split("=");

                if (splitStr.length < 2) {
                    break;
                } else {
                    statefn = splitStr[1];
                }
            } else if (args[i].contains("-logfile=") && logfn.isEmpty()) {
                String[] splitStr = args[i].split("=");

                if (splitStr.length < 2) {
                    break;
                } else {
                    logfn = splitStr[1];
                }
            }
        }

        // check if all the filenames have been given. if not, error message and exit
        if (datafn.isEmpty() || statefn.isEmpty() || logfn.isEmpty()) {
            System.out.println("One or more file names is missing! Please check that you included them all!");
            return;
        }
    
        System.out.println("Data file name: " + datafn);
        System.out.println("State file name: " + statefn);
        System.out.println("Log file name: " + logfn); 

        // TODO: open and read files
    }
}
