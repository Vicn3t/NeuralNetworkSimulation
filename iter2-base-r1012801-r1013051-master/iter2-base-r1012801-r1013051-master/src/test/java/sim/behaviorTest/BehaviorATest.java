package sim.behaviorTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sim.Chromosome;
import sim.Constants;
import sim.Creature;
import sim.World;
import sim.behaviors.BehaviorA;
import util.Color;
import util.Orientation;
import util.Point;

class BehaviorATest {

	public BehaviorA behaviorA;
	public Chromosome chromosome;
	public Creature creature;
	public Creature creature2;
	public World world1;
	public World world2;
	
	
	@BeforeEach
	void setUp() {
		 chromosome = new Chromosome(new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
		behaviorA = new BehaviorA(chromosome);
		 creature = new Creature(behaviorA,new Point(10, 10) , new Orientation(0));
		 creature2 = new Creature(behaviorA,new Point(0, 0) , new Orientation(0));
		 world1 = new World(Constants.WSIZE, Constants.WSIZE, new Creature[] {creature});	
		 world2 = new World(Constants.WSIZE, Constants.WSIZE, new Creature[] {new Creature(behaviorA,new Point(8, 6) , new Orientation(0))});
	}
	@Test
	void colorTest() {
		assertEquals(behaviorA.getColor(), util.Color.RED);
	}
	
	@Test
	void applyBehaviorTest() {
		behaviorA.applyBehavior(world1, creature);
		assertEquals(creature.getPosition(), new Point(9, 8));
		
		behaviorA.applyBehavior(world1,creature2);
		assertEquals(creature2.getPosition(), new Point(0, 0));
		
		behaviorA.applyBehavior(world2,creature);
		assertTrue(creature.getPosition().equals(new Point(9, 8)));
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
	BehaviorA behaviorA2 = behaviorA.copyWithChromosome(chromosome);
	assertEquals(behaviorA.getClass(), behaviorA2.getClass());
	assertTrue(behaviorA.getChromosome().isEqual(behaviorA2.getChromosome()));

}
}
