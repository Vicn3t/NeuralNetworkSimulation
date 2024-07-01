package sim.neuralnetTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sim.Chromosome;
import sim.Constants;
import sim.Creature;
import sim.World;
import sim.behaviors.BehaviorA;
import sim.neuralnet.ActivationFunctionNeuron;
import sim.neuralnet.LinearFunctionNeuron;
import sim.neuralnet.Neuron;
import sim.neuralnet.VerticalPositionSensorNeuron;
import util.Orientation;
import util.Pair;
import util.Point;

class ActivationFunctionNeuronTest {

	private ArrayList<Pair<Neuron, Integer>> dependencies;
	private ActivationFunctionNeuron neuron;
	private World world;
	private Creature creature1;
	private Creature creature2;
	
	@BeforeEach
	public void setup() {
		dependencies = new ArrayList<Pair<Neuron, Integer>>();
		neuron = new LinearFunctionNeuron();
		creature1 = new Creature(new BehaviorA(Chromosome.createRandom()),new Point(0, 0) , new Orientation(0));
		creature2 = new Creature(new BehaviorA(Chromosome.createRandom()),new Point(0, Constants.WSIZE -1) , new Orientation(0));
		Creature[] creatures = new Creature[] {creature1, creature2}; 
        world = new World(Constants.WSIZE, Constants.WSIZE, creatures);

	}

	@Test
	public void getters() {
		assertTrue(neuron.getDependencies().isEmpty());
		assertEquals(0, neuron.getBias());
		
        neuron.setDependencies(dependencies);
        neuron.setBias(1);
        assertEquals(1, neuron.getBias());
        assertTrue(dependencies.equals(neuron.getDependencies()));
    }
	
	@Test
	public void connect()
	{
		neuron.connect(new LinearFunctionNeuron(), 1);
		assertEquals(1, neuron.getDependencies().size());
		assertTrue(neuron.getDependencies().get(0).getFirst() instanceof LinearFunctionNeuron);
		assertEquals(1, neuron.getDependencies().get(0).getSecond());
	}
	
	@Test
	public void computeOutput() {
		neuron.connect(new VerticalPositionSensorNeuron(), 1);
        neuron.setBias(1);
		var result = neuron.computeOutput(world, creature1);
		var result2 = neuron.computeOutput(world, creature2);
		assertEquals(0, result);
		assertEquals(2, result2);
	}
	}
	
	
	
		

