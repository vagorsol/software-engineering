import static org.junit.Assert.*;

import org.junit.Test;

public class DonateTest {
	
	Fund f;

	@Test(expected=IllegalArgumentException.class)
	public void testNullUser() {
		f = new Fund(100);
		f.donate(null, 10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBlankUser() {
		f = new Fund(100);
		f.donate("", 10);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNegDonation(){
		f = new Fund(100);
		f.donate("user", -10);
	}

	@Test(expected=IllegalStateException.class)
	public void testNegBalance(){
		// ok how do i get negative balance? 
		f = new Fund(100);
		f.balance = -10;
		f.donate("user", 10);
	}

	@Test
	public void testFullFund() {
		f = new Fund(100);
		double bal = f.donate("user1", 100);
		double bal2 = f.donate("user2", 10);

		// expected actual delta, because double are funny sometimes
		assertEquals(100, bal, 0.1); 
		assertEquals(0, bal2, 0.1);
		assertEquals(1, f.donors.size());
		assertEquals(100, f.balance, 0.1);
		assertTrue(f.donors.contains("user1"));

	}

	@Test
	public void testDonationOverflow() {
		f = new Fund(100);
		double bal = f.donate("user1", 1100);

		// expected actual delta, because double are funny sometimes
		assertEquals(100, bal, 0.1); // returned should be the difference b/t target (100) and balance (was 0)? 
		assertEquals(100, f.balance, 0.1); // balance should be equal to amt donated
		assertEquals(1, f.donors.size());
		assertTrue(f.donors.contains("user1"));	
	}

	@Test
	public void testDonationUnder() {
		f = new Fund(100);
		double bal = f.donate("user1", 20);

		// expected actual delta, because double are funny sometimes
		assertEquals(20, bal, 0.1); 
		assertEquals(20, f.balance, 0.1); // balance should be equal to amt donated
		assertEquals(1, f.donors.size());
		assertTrue(f.donors.contains("user1"));	
	}
}
