package sim.neuralnetTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sim.Chromosome;
import sim.Constants;
import sim.Creature;
import sim.World;
import sim.behaviors.BehaviorA;
import sim.neuralnet.FreePassageSensorNeuron;
import util.Orientation;
import util.Point;

class FreePassageSensorNeuronTest {

	private FreePassageSensorNeuron weight;
	public Orientation orientation;
	private Creature creature1;
	private World world;

	@BeforeEach
	public void setup() {
		orientation = new Orientation(0);
		weight = new FreePassageSensorNeuron(orientation);
		creature1 = new Creature(new BehaviorA(Chromosome.createRandom()),new Point(0, 0) , new Orientation(4));
		Creature[] creatures = new Creature[] {creature1}; 
        world = new World(Constants.WSIZE, Constants.WSIZE, creatures);
	}
	
	@Test
	public void IllegalOrientation() {
		assertThrows(IllegalArgumentException.class, () -> new FreePassageSensorNeuron(null));
	}
	
	@Test
	public void detect() {
		assertTrue(weight.detect(world, creature1));
	}
	@Test
	public void detectOutOfTheWorld() {
		Creature creature2 = new Creature(new BehaviorA(Chromosome.createRandom()),new Point(0, 0) , new Orientation(0));
		assertFalse(weight.detect(world, creature2));
	}
	@Test
	public void detectNotFree() {
		Creature creature3 = new Creature(new BehaviorA(Chromosome.createRandom()),new Point(0, 1) , new Orientation(0));
		assertFalse(weight.detect(world, creature3));
		
	
    }
}
