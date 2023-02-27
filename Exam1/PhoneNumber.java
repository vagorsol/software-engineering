import java.util.*;

public class PhoneNumber {
    public int areaCode;
	public int number;
	
	public PhoneNumber(int areaCode, int number) {
		this.areaCode = areaCode;
		this.number = number;
	}
	
	@Override
	public boolean equals(Object other) {
		return other != null && other instanceof PhoneNumber &&	((PhoneNumber)other).hashCode() == this.hashCode();
	}
	
	@Override
	public int hashCode() {
        // added mod so if same sum + different numbers or vice versa wouldn't equals same (hopefully)
		return areaCode % 10 + number % 10 + areaCode + number;
	}

    public static void main(String[] args){
        PhoneNumber pn1 = new PhoneNumber(555, 5266556);
        PhoneNumber pn2 = new PhoneNumber(610, 5266501);

        List<PhoneNumber> list = new ArrayList<>();
        list.add(pn1);
        // should be false, but prints true!
        System.out.println(list.contains(pn2)); 

        Set<PhoneNumber> set = new HashSet<>();
        set.add(pn1);
        // should be false, but prints true!
        System.out.println(set.contains(pn2));
    }
}
