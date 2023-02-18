/*
 * This class represents the Client that communicates with the RESTful API.
 * 
 * Implement the get() method according to the specification. You may add other methods
 * and instance variables as needed, though it should be possible to implement get()
 * without adding anything else.
 */

import java.util.HashSet;
import java.util.Set;
import java.util.*;
import java.net.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
		String request = "http://localhost:3000/status?"; 
		Set<Product> products = new HashSet<>(); 

		// check if ids is empty
		if (ids == null || ids.length == 0) {
			return products;
		}

		// generate URL
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];

			// check if id is illegal (i.e., null/empty)
			if (id == null || id.isEmpty()){
				throw new IllegalArgumentException();
			}

			// concat id to string
			String idStat = "id=" + id;

			// if multiple ids and not at the end of the string, add the "&"
			if(ids.length > 1 && i < ids.length - 1) {
				idStat = idStat + "&";
			}

			request.concat(idStat);
		}

		// TODO url stuff
		Scanner in = null;
    	
		try {
		    // create an object to represent the connection to a
		    // web server on port 3000 on this computer
		    URL url = new URL(request);
		    HttpURLConnection conn = (HttpURLConnection)url.openConnection(); 
		    conn.setRequestMethod("GET"); 
	
		    // open connection and send HTTP request
		    conn.connect(); 
	
		    // now the response comes back
		    int responsecode = conn.getResponseCode();
		    
		    // make sure the response has "200 OK" as the status
		    if (responsecode != 200) {
		    	System.out.println("Unexpected status code: " + responsecode);
		    }
		    else {
				// made it here, we got a good response, let's read it
				in = new Scanner(url.openStream());
				
				while (in.hasNext()) {
					
				    // read the next line of the body of the response
				    String line = in.nextLine();
				    
				    // the rest of this code assumes that the body contains JSON
				    // first, create the parser
				    JSONParser parser = new JSONParser();
				    
				    // then, parse the data and create a JSON object for it
				    JSONObject data = (JSONObject) parser.parse(line);
				    
				    // read the "message" field from the JSON object
				    String message = (String)data.get("message");
				    
				    // if using the provided Node Express starter code,
				    // this should print "It works!"
				    System.out.println(message);
				}
		    }
	
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
		finally {
			try { in.close(); }
			catch (Exception e) { }
		}

		return products;
	}

}
