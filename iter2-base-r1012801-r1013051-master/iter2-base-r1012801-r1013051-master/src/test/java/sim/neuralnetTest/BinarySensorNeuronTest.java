package sim.neuralnetTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sim.Chromosome;
import sim.Constants;
import sim.Creature;
import sim.World;
import sim.behaviors.BehaviorA;
import sim.neuralnet.BinarySensorNeuron;
import sim.neuralnet.FreePassageSensorNeuron;
import util.Orientation;
import util.Point;

class BinarySensorNeuronTest {

	public BinarySensorNeuron neuron;
	public Orientation orientation;
	private Creature creature1;
	private World world;

	@BeforeEach
	public void setup() {
		orientation = new Orientation(0);
		neuron = new FreePassageSensorNeuron(orientation);
		creature1 = new Creature(new BehaviorA(Chromosome.createRandom()),new Point(0, 0) , new Orientation(4));
		Creature[] creatures = new Creature[] {creature1}; 
        world = new World(Constants.WSIZE, Constants.WSIZE, creatures);
	}
	
	@Test
	public void computeOutputFree()
	{
		assertEquals(750, neuron.computeOutput(world, creature1));
	}
	@Test
	public void computeOutputBlock()
	{
		Creature creature2 = new Creature(new BehaviorA(Chromosome.createRandom()),new Point(0, 1) , new Orientation(0));
		assertEquals(-750, neuron.computeOutput(world, creature2));
	}
	
	@Test
	public void computeOutputWall()
	{
		Creature creature3 = new Creature(new BehaviorA(Chromosome.createRandom()),new Point(Constants.WSIZE-1, Constants.WSIZE-1) , new Orientation(4));
		assertEquals(-750, neuron.computeOutput(world, creature3));
		
    
	}

}
