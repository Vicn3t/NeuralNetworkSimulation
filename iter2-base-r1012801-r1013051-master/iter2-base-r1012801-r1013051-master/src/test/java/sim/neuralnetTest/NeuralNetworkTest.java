package sim.neuralnetTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sim.Chromosome;
import sim.neuralnet.ActivationFunctionNeuron;
import sim.neuralnet.LinearFunctionNeuron;
import sim.neuralnet.NeuralNetwork;
import sim.neuralnet.Neuron;
import sim.neuralnet.RectifiedLinearUnitFunctionNeuron;
import sim.neuralnet.SensorNeuron;
import util.Pair;

class NeuralNetworkTest {

	private Chromosome chromosome;
	private NeuralNetwork imputNeurons;
	private NeuralNetwork outputNeurons;

	
	@BeforeEach
	public void setup() {
		chromosome = new Chromosome(new int[] { 1, 2, 3, 4, 5, 6, 7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24});
        imputNeurons = new NeuralNetwork();
        outputNeurons = new NeuralNetwork();
	}
	@Test
	public void gettersTest() {

        SensorNeuron [] inputLayerNeurons = new SensorNeuron[7];
        ActivationFunctionNeuron[] outputLayerNeurons = new  ActivationFunctionNeuron[3];
        
        assertEquals(7, imputNeurons.getInputNeurons().length);
        assertEquals(3, outputNeurons.getOutputNeurons().length);
        assertEquals(new RectifiedLinearUnitFunctionNeuron().getClass(), outputNeurons.getMoveForwardNeuron().getClass());
        assertEquals(new LinearFunctionNeuron().getClass(), outputNeurons.getTurnClockwiseNeuron().getClass());
        assertEquals(new LinearFunctionNeuron().getClass(), outputNeurons.getTurnCounterclockwiseNeuron().getClass());
        assertEquals(inputLayerNeurons.getClass(), imputNeurons.getInputNeurons().getClass());
        assertEquals(outputLayerNeurons.getClass(), outputNeurons.getOutputNeurons().getClass());
	
}
	@Test
	public void fromChromosome() {
		var network = NeuralNetwork.fromChromosome(chromosome);
        ArrayList<Pair<Neuron, Integer>> MoveForwardNeuron = new ArrayList<>();
        MoveForwardNeuron.add(new Pair<Neuron, Integer>(network.getInputNeurons()[0], 1));
        MoveForwardNeuron.add(new Pair<Neuron, Integer>(network.getInputNeurons()[1], 2));
        MoveForwardNeuron.add(new Pair<Neuron, Integer>(network.getInputNeurons()[2], 3));
        MoveForwardNeuron.add(new Pair<Neuron, Integer>(network.getInputNeurons()[3], 4));
        MoveForwardNeuron.add(new Pair<Neuron, Integer>(network.getInputNeurons()[4], 5));
        MoveForwardNeuron.add(new Pair<Neuron, Integer>(network.getInputNeurons()[5], 6));
        MoveForwardNeuron.add(new Pair<Neuron, Integer>(network.getInputNeurons()[6], 7));
        
        ArrayList<Pair<Neuron, Integer>> TurnClockwiseNeuron = new ArrayList<>();
        TurnClockwiseNeuron.add(new Pair<Neuron, Integer>(network.getInputNeurons()[0], 8));
        TurnClockwiseNeuron.add(new Pair<Neuron, Integer>(network.getInputNeurons()[1], 9));
        TurnClockwiseNeuron.add(new Pair<Neuron, Integer>(network.getInputNeurons()[2], 10));
        TurnClockwiseNeuron.add(new Pair<Neuron, Integer>(network.getInputNeurons()[3], 11));
        TurnClockwiseNeuron.add(new Pair<Neuron, Integer>(network.getInputNeurons()[4], 12));
        TurnClockwiseNeuron.add(new Pair<Neuron, Integer>(network.getInputNeurons()[5], 13));
        TurnClockwiseNeuron.add(new Pair<Neuron, Integer>(network.getInputNeurons()[6], 14));
        
        ArrayList<Pair<Neuron, Integer>> TurnCounterclockwiseNeuron = new ArrayList<>();
        
		
		assertEquals(MoveForwardNeuron.size(), network.getMoveForwardNeuron().getDependencies().size());	
		assertEquals(7, network.getTurnClockwiseNeuron().getDependencies().size());
		assertEquals(7, network.getTurnCounterclockwiseNeuron().getDependencies().size());

		assertEquals(MoveForwardNeuron.getClass(), network.getMoveForwardNeuron().getDependencies().getClass());
		assertTrue(MoveForwardNeuron.get(0).isEqual(network.getMoveForwardNeuron().getDependencies().get(0)));
		
		assertTrue(NeuralNetwork.isEqualList(MoveForwardNeuron, network.getMoveForwardNeuron().getDependencies()));
		assertFalse(NeuralNetwork.isEqualList(TurnClockwiseNeuron, network.getMoveForwardNeuron().getDependencies()));
		assertFalse(NeuralNetwork.isEqualList(TurnClockwiseNeuron, MoveForwardNeuron));
		
		assertEquals(22, network.getMoveForwardNeuron().getBias());
		assertEquals(23, network.getTurnCounterclockwiseNeuron().getBias());
		assertEquals(24, network.getTurnClockwiseNeuron().getBias());
	}
}
