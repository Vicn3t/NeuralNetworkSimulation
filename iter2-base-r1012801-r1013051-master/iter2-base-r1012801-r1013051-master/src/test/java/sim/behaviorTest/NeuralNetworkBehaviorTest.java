package sim.behaviorTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sim.Chromosome;
import sim.Constants;
import sim.Creature;
import sim.World;
import sim.behaviors.NeuralNetworkBehavior;
import util.Orientation;
import util.Point;

class NeuralNetworkBehaviorTest {
	public NeuralNetworkBehavior neuralNetworkBehavior;
	public Chromosome chromosome;
	public Creature creature;
	public Creature creature2;
	public World world1;
	public World world2;
	
	
	
	
	@BeforeEach
	void setUp() {
		 chromosome = new Chromosome(new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
		 neuralNetworkBehavior = new NeuralNetworkBehavior(chromosome);
		 creature = new Creature(neuralNetworkBehavior,new Point(10, 10) , new Orientation(0));
		 creature2 = new Creature(neuralNetworkBehavior,new Point(0, 0) , new Orientation(0));
		 world1 = new World(Constants.WSIZE, Constants.WSIZE, new Creature[] {creature});	
		 world2 = new World(Constants.WSIZE, Constants.WSIZE, new Creature[] {new Creature(neuralNetworkBehavior,new Point(10, 8) , new Orientation(0))});
	}

	@Test
	void colorTest() {
		assertEquals(neuralNetworkBehavior.getColor(), util.Color.WHITE);
		neuralNetworkBehavior.applyBehavior(world1, creature);
}
	
	@Test
	void applyBehaviorTest() {
		neuralNetworkBehavior.applyBehavior(world1, creature);
		assertEquals(creature.getPosition(), new Point(10, 9));
		
		neuralNetworkBehavior.applyBehavior(world1,creature2);
		assertEquals(creature2.getPosition(), new Point(0, 0));       
		}

	@Test
	void copyWithChromosomeTest() {
		NeuralNetworkBehavior neuralNetworkBehavior2 = neuralNetworkBehavior.copyWithChromosome(chromosome);
		assertTrue(neuralNetworkBehavior.equals(neuralNetworkBehavior2));
		assertTrue(neuralNetworkBehavior.getChromosome().isEqual(neuralNetworkBehavior2.getChromosome()));

	}
}

