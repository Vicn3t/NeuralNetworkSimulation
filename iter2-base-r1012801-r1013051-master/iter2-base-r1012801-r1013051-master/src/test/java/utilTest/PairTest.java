package utilTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.Pair;

class PairTest {

	private Pair <Integer, Integer> pair;

	@BeforeEach
	public void setup() {
		pair = new Pair<>(1, 2);
	}
	
	@Test
	public void initializationPair() {
		Pair <Integer, Integer> pair = new Pair<>(1, 2);
		assertEquals(1, pair.getFirst());
		assertEquals(2, pair.getSecond());
		
		pair.setFirst(3);
		assertEquals(3, pair.getFirst());
		assertEquals(2, pair.getSecond());

		pair.setSecond(4);
		assertEquals(3, pair.getFirst());
		assertEquals(4, pair.getSecond());
	}
	
	@Test
	public void isEqualOnSamePair() {
		assertTrue(pair.isEqual(pair));
	}
	@Test
	public void isEqualOndifferentPair() {
		Pair <Integer, Integer> pair2 = new Pair<>(1, 2);
		assertTrue(pair.isEqual(pair2));
}
	
	@Test
	public void isNotEqual() {
		Pair<Integer, Integer> pair2 = new Pair<>(3, 4);
		assertFalse(pair.isEqual(pair2));
	}
}
