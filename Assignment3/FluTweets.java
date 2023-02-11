public class FluTweets {
    
    public static void main(String[] args) { 
        // get the file names 
        if (args.length < 3) {
            System.out.println("Missing file information!");
            return;
        }

        String datafn = "";
        String statefn = "";
        String logfn = "";

        for (int i = 0; i < args.length; i++){
            if (args[i].contains("-datafile=") && !datafn.isEmpty()) {
                String[] splitStr = args[i].split("=");

                // check if filename was include (ie. if empty or not)
                if (splitStr.length < 2) {
                    break;
                } else {
                    datafn = splitStr[1];
                }
                
            } else if (args[i].contains("-statesfile=") && !statefn.isEmpty()) {
                String[] splitStr = args[i].split("=");

                if (splitStr.length < 2) {
                    break;
                } else {
                    statefn = splitStr[1];
                }
            } else if (args[i].contains("-logfile=") && !logfn.isEmpty()) {
                String[] splitStr = args[i].split("=");

                if (splitStr.length < 2) {
                    break;
                } else {
                    logfn = splitStr[1];
                }
            }
        }
    }
}
