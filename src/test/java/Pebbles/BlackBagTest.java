package Pebbles;

import org.junit.Test;

import java.util.Objects;
import java.util.Vector;

import static org.junit.Assert.assertEquals;

public class BlackBagTest {
    private final ClassLoader classLoader = this.getClass().getClassLoader();

    @Test
    public void testValidBlackBagCSV() {
        BlackBag bag = BlackBag.fromCSV(new Bag(), 1, Objects.requireNonNull(classLoader.getResource("test.csv")).getFile());
        for (Integer i=1; i <= 100; i++) {
            assertEquals(i, bag.removeStone(0));
        }
    }
    @Test(expected = IllegalArgumentException.class) public void testTooManyPlayers() {
        String fileName = Objects.requireNonNull(classLoader.getResource("test.csv")).getFile();
        try {
            BlackBag.fromCSV(new Bag(), 100, fileName);
        } catch (IllegalArgumentException e) {
            assertEquals(
                    "Bag starting contents must have at least 11 times the number of players (1100 > 100) in stones (file: " + fileName + ")", e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class) public void testNegativeWeights() {
        String fileName = Objects.requireNonNull(classLoader.getResource("negatives.csv")).getFile();
        try {
            BlackBag.fromCSV(new Bag(), 1, fileName);
        } catch (IllegalArgumentException e) {
            assertEquals("Bag must contain strictly positively weighted stones (file: " + fileName + ")", e.getMessage());
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class) public void testZeroWeights() {
        String fileName = Objects.requireNonNull(classLoader.getResource("zeros.csv")).getFile();
        try {
            BlackBag.fromCSV(new Bag(), 1, fileName);
        } catch (IllegalArgumentException e) {
            assertEquals("Bag must contain strictly positively weighted stones (file: " + fileName + ")", e.getMessage());
            throw e;
        }
    }

    @Test public void testRefill() {
        Vector<Integer> vec = new Vector<>();
        for (int i=0; i < 10; i++) {
            vec.add(i);
        }
        Bag whiteBag = new Bag();
        BlackBag blackBag = new BlackBag(whiteBag, vec);
        assertEquals(10, blackBag.size());
        assertEquals(0, whiteBag.size());
        for (int i=1; i <= 10; i++) {
            whiteBag.addStone(blackBag.removeStone(0));
            assertEquals(10-i, blackBag.size());
            assertEquals(i, whiteBag.size());
        }
        blackBag.refill();
        assertEquals(10, blackBag.size());
        assertEquals(0, whiteBag.size());
    }
}
