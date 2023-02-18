/*
 * This class represents the Client that communicates with the RESTful API.
 * 
 * Implement the get() method according to the specification. You may add other methods
 * and instance variables as needed, though it should be possible to implement get()
 * without adding anything else.
 */

import java.util.Set;

public class Client {
	
	private String host;
	private int port;
	
	public Client() {
		// use Node Express defaults
		host = "localhost";
		port = 3000;
	}
	
	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}


	/* IMPLEMENT THIS METHOD! */
	public Set<Product> get(String[] ids) {

		// change accordingly!
		return null;
	}

}
