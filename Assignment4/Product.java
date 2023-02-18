/*
 * This class represents a Product whose status is tracked by the system.
 * 
 * DO NOT CHANGE THIS CODE!
 */

public class Product implements Comparable<Product> {
	
	public String id;
	public String status;
	
	public Product(String id, String status) {
		this.id = id;
		this.status = status;
	}
	
	public String toString() {
		return "Product ID " + id + " reported as " + status;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || (o instanceof Product) == false) return false;
		Product p = (Product)o;
		return p.id.equals(this.id) && p.status.equals(this.status);
	}
	
	@Override
	public int compareTo(Product p) {
		if (this.equals(p)) return 0;
		else {
			return this.id.compareTo(p.id);
		}
	}
	
	@Override
	public int hashCode() {
		return id.hashCode() + status.hashCode();
	}

}
