import java.util.HashSet;
import java.util.Set;

public class ClientTest {
    public static void main(String[] args){
        String[] ids = {"1234", "5678"}; 
        Client client = new Client(); 
        Set<Product> products = new HashSet<>(); 
        products = client.get(ids);

        for (Product p : products){
            System.out.println(p);
        }
        System.out.println("Finished!");
        /*
         * others:
         * {"1234"}
         * {"0", "1111"}
         * {}
         */
    }
}
