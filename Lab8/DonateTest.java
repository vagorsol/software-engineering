import static org.junit.Assert.*;

import org.junit.Test;

public class DonateTest {
	
	Fund f;

	@Test(expected=IllegalArgumentException.class)
	public void testNullUser() {
		f = new Fund(100);
		f.donate(null, 10);
	}
	
}
