/*
 * This class represents the Client that communicates with the RESTful API.
 * 
 * Implement the get() method according to the specification. You may add other methods
 * and instance variables as needed, though it should be possible to implement get()
 * without adding anything else.
 */

import java.util.*;
import java.io.IOException;
import java.net.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Client {
	
	private String host;
	private int port;
	private static Map<String, Product> productCache = new HashMap<>();
	
	public Client() {
		// use Node Express defaults
		host = "localhost";
		port = 3000;
	}
	
	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	// javac --release 8 -cp "json-simple-1.1.1.jar;." ClientTest.java
	// java -cp "json-simple-1.1.1.jar;." ClientTest
	public Set<Product> get(String[] ids) {
		String request = "http://" + host + ":" + port + "/status?"; 
		Set<Product> products = new HashSet<>(); 

		// check if ids is null: if so, throw an exception
		if (ids == null) {
			throw new IllegalArgumentException();
		}

		// check if ids is empty: if so, return an empty set
		if (ids.length == 0) {
			return products;
		}

		// generate URL
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			// System.out.println(id);

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
			// System.out.println(idStat);
			request = request + idStat;
			// System.out.println(request);
		}

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
				    
				    // parse the data and create a JSON array for it
				    JSONArray data = (JSONArray) parser.parse(line);
				    
					Iterator itt = data.iterator();
				    
					// parse through each object and adds it to the set
					while (itt.hasNext()){
						JSONObject val = (JSONObject) itt.next(); 

						// make and add a Product object to the Set
						String id = (String) val.get("id");
						String status = (String) val.get("status");
						products.add(new Product(id, status));

						// if in cache, update. otherwise, add to cache
						productCache.put(id, new Product(id, status));
					}
				}
		    }
	
		}
		catch (IOException e) {
			// if cannot connect to server, check the Cache

			for (String id : ids) {
				// if the id is in cache, get the information from the cache. otherwise, unknown
				if (!productCache.isEmpty()) {
					if(productCache.containsKey(id)){
						products.add(productCache.get(id));
					} else {
						products.add(new Product(id, "unknown"));
					}
				} else {
					products.add(new Product(id, "unknown"));
				}
			}
		}
		catch (Exception e) {
		    throw new IllegalStateException();
		}
		finally {
			try { in.close(); }
			catch (Exception e) { }
		}

		return products;
	}

}
