package sim.neuralnet;

import java.util.ArrayList;
import java.util.Arrays;

// import java.util.Arrays;

import sim.Chromosome;
import util.Orientation;
import util.Pair;

/**
 * Output neurons depend at least on all input neurons, if they depend on something.
 * All Neuron fields are freely accessible by the client.
 */
public class NeuralNetwork
{


	/**
	 * @representationObject 
	 */
    private final SensorNeuron[] inputLayerNeurons;



    private final ActivationFunctionNeuron moveForwardNeuron;

    private final ActivationFunctionNeuron turnClockwiseNeuron;
    
    private final ActivationFunctionNeuron turnCounterclockwiseNeuron;

    /**
     * Returns a NeuralNetwork with 7 input neurons and 3 output neurons, as described in assignment,
     * but with no connections.
     * @post | getInputNeurons().length == 7
     */
    public NeuralNetwork()
    {
    	
    	//this block of code is LEGIT
        this.inputLayerNeurons = new SensorNeuron[] {
    		new FreePassageSensorNeuron(Orientation.north()),
    		new FreePassageSensorNeuron(Orientation.northWest()),
    		new FreePassageSensorNeuron(Orientation.northEast()),
    		new HorizontalOrientationSensorNeuron(),
            new VerticalOrientationSensorNeuron(),
            new HorizontalPositionSensorNeuron(),
            new VerticalPositionSensorNeuron()
        };
        this.moveForwardNeuron = new RectifiedLinearUnitFunctionNeuron();
        this.turnClockwiseNeuron = new LinearFunctionNeuron();
        this.turnCounterclockwiseNeuron = new LinearFunctionNeuron();
        

    }



	/**
	 * @post | result.length == 7
	 */
	public SensorNeuron[] getInputNeurons()
    {
        return inputLayerNeurons;
    }
	
	/**
	 * méthode vide
	 * @post | result.length == 3
	 */
    public ActivationFunctionNeuron[] getOutputNeurons()
    {
        return new ActivationFunctionNeuron[] {moveForwardNeuron, turnCounterclockwiseNeuron, turnClockwiseNeuron};
        //please use this specific order (different than the order of the fields above):
        // - moveForwardNeuron
        // - turnCounterclockwiseNeuron
        // - turnClockwiseNeuron  
    }

    public ActivationFunctionNeuron getMoveForwardNeuron() { return this.moveForwardNeuron; }

    public ActivationFunctionNeuron getTurnClockwiseNeuron() { return this.turnClockwiseNeuron; }

    public ActivationFunctionNeuron getTurnCounterclockwiseNeuron() { return this.turnCounterclockwiseNeuron; }

    /**
     * méthode vide
     * @pre | chromosome != null
     * post:
     * - getInputNeurons() and the first 7 genes of chromosome are used as dependencies/weights of getMoveForwardNeuron()
     * - getInputNeurons() and the next 7 genes of chromosome are used as dependencies/weights of getTurnCounterclockwiseNeuron()
     * - getInputNeurons() and the next 7 genes of chromosome are used as dependencies/weights of getTurnClockwiseNeuron()
     * - The 3 remaining genes are used as biases of
     *   getMoveForwardNeuron(), getTurnCounterclockwiseNeuron(), getTurnClockwiseNeuron()
     */
	public static NeuralNetwork fromChromosome(Chromosome chromosome) 
	{
	    NeuralNetwork network = new NeuralNetwork();
	    SensorNeuron[] inputNeurons = network.getInputNeurons();
	    int numInputNeurons = inputNeurons.length;
	    int geneIndex = 0;

	    // Connect weights to moveForwardNeuron
	    for (int i = 0; i < numInputNeurons; i++) {
	        int weight = chromosome.getGene(geneIndex++);
	        network.getMoveForwardNeuron().connect(inputNeurons[i], weight);
	    }

	    // Connect weights to turnCounterclockwiseNeuron
	    for (int i = 0; i < numInputNeurons; i++) {
	        int weight = chromosome.getGene(geneIndex++);
	        network.getTurnCounterclockwiseNeuron().connect(inputNeurons[i], weight);
	    }

	    // Connect weights to turnClockwiseNeuron
	    for (int i = 0; i < numInputNeurons; i++) {
	        int weight = chromosome.getGene(geneIndex++);
	        network.getTurnClockwiseNeuron().connect(inputNeurons[i], weight);
	    }

	    // Set biases for each output neuron
	    network.getMoveForwardNeuron().setBias(chromosome.getGene(geneIndex++));
	    network.getTurnCounterclockwiseNeuron().setBias(chromosome.getGene(geneIndex++));
	    network.getTurnClockwiseNeuron().setBias(chromosome.getGene(geneIndex));

	    return network;
	}
	
	/**
	 * méthode ajoutée + docu
	 * @pre | l1 != null
	 * @pre | l2 != null
	 */
	public static boolean isEqualList(ArrayList<Pair<Neuron, Integer>> l1, ArrayList<Pair<Neuron, Integer>> l2) {
		if (l1.size() != l2.size()) {
            return false;
        }
        for (int i = 0; i < l1.size(); i++) {
            if (!l1.get(i).isEqual(l2.get(i))) {
                return false;
            }
        }
        return true;
	}
}