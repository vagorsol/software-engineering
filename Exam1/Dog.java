import java.util.*;

public class Dog {
    public String name;
	public boolean isGood;
	
	public Dog(String name, boolean good) {
		this.name = name;
		this.isGood = good;
	}

	public static Set<String> goodDogs(List<Dog> dogs) {
        if (dogs == null) {
            throw new IllegalArgumentException();
        }
        
        TreeSet<String> ret = new TreeSet<>();
        for (Dog dog : dogs) {
            if (dog == null) {
                continue;
            } else {
                if (dog.isGood == true && dog.name != null) {
                    ret.add(dog.name); //  && !dog.name.isEmpty() <- unsure if should ignore empty name - ask
                }
            }
        }

        return ret; 
	}

    public static void main(String[] args) {
        // testing
        LinkedList<Dog> dogs = new LinkedList<>();
        // dogs = null;
        dogs.add(new Dog("Sam", false));
        dogs.add(new Dog(null, true));
        dogs.add(new Dog("Casey", true));
        dogs.add(null);
        dogs.add(new Dog("Timothy", true));
        dogs.add(new Dog("", true));

        for (String name : Dog.goodDogs(dogs)) {
            System.out.println(name);
        }
    
    }
}

