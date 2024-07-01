package sim.behaviorTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sim.Chromosome;
import sim.Constants;
import sim.Creature;
import sim.World;
import sim.behaviors.ImmobileBehavior;
import util.Orientation;
import util.Point;

class ImmobileBehaviorTest {
	public ImmobileBehavior immobileBehavior;
	public Chromosome chromosome;
	public Creature creature;
	public Creature creature2;
	public World world1;
	public World world2;
	
	
	
	
	@BeforeEach
	void setUp() {
		 chromosome = new Chromosome(new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
		immobileBehavior = new ImmobileBehavior(chromosome);
		 creature = new Creature(immobileBehavior,new Point(10, 10) , new Orientation(0));
		 creature2 = new Creature(immobileBehavior,new Point(0, 0) , new Orientation(0));
		 world1 = new World(Constants.WSIZE, Constants.WSIZE, new Creature[] {creature});	
		 world2 = new World(Constants.WSIZE, Constants.WSIZE, new Creature[] {new Creature(immobileBehavior,new Point(10, 8) , new Orientation(0))});
	}

	@Test
	void colorTest() {
		assertEquals(immobileBehavior.getColor(), util.Color.WHITE);
		immobileBehavior.applyBehavior(world1, creature);
}
	
	@Test
	void applyBehaviorTest() {
		immobileBehavior.applyBehavior(world1, creature);
		assertEquals(creature.getPosition(), new Point(10, 10));
		
		immobileBehavior.applyBehavior(world1,creature2);
		assertEquals(creature2.getPosition(), new Point(0, 0));
		
		immobileBehavior.applyBehavior(world2,creature);     
		}

	@Test
	void copyWithChromosomeTest() {
		ImmobileBehavior immobileBehavior2 = immobileBehavior.copyWithChromosome(chromosome);
		assertEquals(immobileBehavior.getClass(), immobileBehavior2.getClass());
		assertTrue(immobileBehavior.getChromosome().isEqual(immobileBehavior2.getChromosome()));

	}
}