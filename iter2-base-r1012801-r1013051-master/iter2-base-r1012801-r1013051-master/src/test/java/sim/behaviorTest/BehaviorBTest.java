package sim.behaviorTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sim.Chromosome;
import sim.Constants;
import sim.Creature;
import sim.World;
import sim.behaviors.BehaviorB;
import util.Orientation;
import util.Point;

class BehaviorBTest {
	public BehaviorB behaviorB;
	public Chromosome chromosome;
	public Creature creature;
	public Creature creature2;
	public World world1;
	public World world2;
	
	
	
	
	@BeforeEach
	void setUp() {
		 chromosome = new Chromosome(new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
		behaviorB = new BehaviorB(chromosome);
		 creature = new Creature(behaviorB,new Point(10, 10) , new Orientation(0));
		 creature2 = new Creature(behaviorB,new Point(0, 0) , new Orientation(0));
		 world1 = new World(Constants.WSIZE, Constants.WSIZE, new Creature[] {creature});	
		 world2 = new World(Constants.WSIZE, Constants.WSIZE, new Creature[] {new Creature(behaviorB,new Point(10, 8) , new Orientation(0))});
	}

	@Test
	void colorTest() {
		assertEquals(behaviorB.getColor(), util.Color.BLUE);
		behaviorB.applyBehavior(world1, creature);
}
	
	@Test
	void applyBehaviorTest() {
		behaviorB.applyBehavior(world1, creature);
		assertEquals(creature.getPosition(), new Point(10, 9));
		
		behaviorB.applyBehavior(world1,creature2);
		assertEquals(creature2.getPosition(), new Point(0, 0));
		
		behaviorB.applyBehavior(world2,creature);
		assertEquals(creature.getPosition(),new Point(10, 9));
		Orientation or = creature.getOrientation();
		  assertTrue(
		            !or.equals(Orientation.east()) &&
		            !or.equals(Orientation.southEast()) &&
		            !or.equals(Orientation.south()) &&
		            !or.equals(Orientation.southWest()) &&
		            !or.equals(Orientation.west()) &&
		            !or.equals(Orientation.north())
		        );        
		}

	@Test
	void copyWithChromosomeTest() {
		BehaviorB behaviorB2 = behaviorB.copyWithChromosome(chromosome);
		assertEquals(behaviorB.getClass(), behaviorB2.getClass());
		assertTrue(behaviorB.getChromosome().isEqual(behaviorB2.getChromosome()));
	}
}