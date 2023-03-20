import java.util.LinkedList;
import java.util.List;

public class NamesManager {
	
	public static int countMatches (Character letter, List<String> names) {
		if (letter == null) {
			throw new IllegalArgumentException();
		}

		if (names == null) {
			throw new IllegalArgumentException();
		}

		if (Character.isLetter(letter) == false) {
			return 0;
		}
		
		if(names.isEmpty()){
			return 0;
		}

		int count = 0;
		for (String name : names) {
			if(name != null && !name.isEmpty()){
				String[] fullName = name.split(",");
				if (fullName.length == 2 && !fullName[1].isEmpty()){
					if (fullName[1].charAt(0) == letter)
						count++;
				}
			}	
		}
		return count;
	}
	
	public static void main(String[] args) {
		
		List<String> list = new LinkedList<>();
		// list.add("Hopper,Grace");
		// list.add("Lovelace,Ada");
		list.add("");
		// list.add(null);
		// list.add("Borg,Anita");
		// list.add("Todd,");
		list.add(",Aoward");
		// list.add(" , a");

		Character c = 'A';
		
		int matches = countMatches(c, list);
		System.out.println("Number of matches: " + matches);
	}
}
