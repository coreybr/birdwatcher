import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {
	
	/*
	 * Setup
	 */
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    // Delete existing file for test
	    Database db = new Database();
	    db.deleteBirds();
	    db = null;
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	
	/*
	 * Tests
	 */
	@Test
	public void testDatabase() {
		Database db = new Database();
		assertNotNull(db.birds);
	}

	@Test
	public void testDeleteBirds() {
		Database db = new Database();
		assertTrue(db.birds.isEmpty());
		db.addBird(new Bird("Blue jay", "Cyanocitta cristata"));
		assertSame(1, db.birds.size());
		db.deleteBirds();
		assertTrue(db.birds.isEmpty());
	}

	@Test
	public void testAddBird() {
		Database db = new Database();
		assertTrue(db.birds.isEmpty());
		db.addBird(new Bird("Blue jay", "Cyanocitta cristata"));
		assertSame(1, db.birds.size());
	}

	@Test
	public void testRemoveBirdByName() {
		Database db = new Database();
		assertTrue(db.birds.isEmpty());
		db.addBird(new Bird("Blue jay", "Cyanocitta cristata"));
		db.addBird(new Bird("Red-tailed hawk", "Buteo jamaicensis"));
		assertSame(2, db.birds.size());
		db.removeBirdByName("Blue jay");
		assertSame(1, db.birds.size());
		assertSame("Red-tailed hawk", db.birds.get(0).getName());
	}

	@Test
	public void testPrintBirds() {
		Database db = new Database();
		assertTrue(db.birds.isEmpty());
		db.addBird(new Bird("Blue jay", "Cyanocitta cristata"));
		db.addBird(new Bird("Red-tailed hawk", "Buteo jamaicensis"));
		db.printBirds();
		assertEquals("Blue jay (Cyanocitta cristata): 0 observations\r\nRed-tailed hawk (Buteo jamaicensis): 0 observations\r\n", outContent.toString());
	}

	@Test
	public void testFindByName() {
		Database db = new Database();
		assertTrue(db.birds.isEmpty());
		Bird blueJay = new Bird("Blue jay", "Cyanocitta cristata");
		db.addBird(blueJay);
		db.addBird(new Bird("Red-tailed hawk", "Buteo jamaicensis"));
		assertSame(blueJay, db.findByName("Blue jay"));
	}

	@Test
	public void testHasByName() {
		Database db = new Database();
		assertTrue(db.birds.isEmpty());
		db.addBird(new Bird("Blue jay", "Cyanocitta cristata"));
		assertFalse(db.hasByName("Red-tailed hawk"));
		db.addBird(new Bird("Red-tailed hawk", "Buteo jamaicensis"));
		assertTrue(db.hasByName("Red-tailed hawk"));
	}

	@Test
	public void testObserveByName() {
		Database db = new Database();
		assertTrue(db.birds.isEmpty());
		db.addBird(new Bird("Blue jay", "Cyanocitta cristata"));
		db.addBird(new Bird("Red-tailed hawk", "Buteo jamaicensis"));
		db.printBirds();
		db.observeByName("Blue jay");
		db.observeByName("Blue jay");
		assertEquals("Blue jay (Cyanocitta cristata): 0 observations\r\nRed-tailed hawk (Buteo jamaicensis): 0 observations\r\nObserved Blue jay 1 time.\r\nObserved Blue jay 2 times.\r\n", outContent.toString());
	}

	@Test
	public void testShowByName() {
		Database db = new Database();
		assertTrue(db.birds.isEmpty());
		db.addBird(new Bird("Blue jay", "Cyanocitta cristata"));
		db.addBird(new Bird("Red-tailed hawk", "Buteo jamaicensis"));
		assertEquals("Red-tailed hawk (Buteo jamaicensis): 0 observations", db.showByName("Red-tailed hawk"));
	}

}
