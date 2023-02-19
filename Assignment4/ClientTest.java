import java.util.HashSet;
import java.util.Set;

/**
 * Testing the get() function in the Client class
 * 
 * @author aqyang
 */
public class ClientTest {
    public static void main(String[] args){
        String[] ids = {"1234"}; 
        Client client = new Client(); // tbh i want to see what server unavalible port is 
        Client clientBad = new Client("host", 4);
        Set<Product> products = new HashSet<>(); 
        try {
            products = client.get(ids);
            for (Product p : products){
                System.out.println(p);
            }
            products = clientBad.get(ids);
            for (Product p : products){
                System.out.println(p);
            }
        } catch(IllegalArgumentException e) {
            System.out.println("Uh oh (Illegal Argument)");
        } catch(IllegalStateException e){
            System.out.println("Uh Oh (Illegal State)");
        }
        

        // for (Product p : products){
        //     System.out.println(p);
        // }
        System.out.println("Finished!");
        /*
         * others:
         * {"1234"}
         * {"0", "1111"}
         * {}
         */
    }
}
