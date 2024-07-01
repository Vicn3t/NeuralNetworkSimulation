package sim.behaviors;

import sim.Chromosome;
import sim.Creature;
import sim.World;
import sim.neuralnet.NeuralNetwork;
import util.Color;
import util.Vector;


/**
 * @invar | getColor().equals( Color.GREEN )
 */
public class NeuralNetworkBehavior extends Behavior	{
	/**
	 * @representationObject
	 * neuralNetwork != null
	 */
    private final NeuralNetwork neuralNetwork;

    public NeuralNetworkBehavior(Chromosome chromosome)
    {
    	super(chromosome);
    	this.neuralNetwork = NeuralNetwork.fromChromosome(chromosome);
    }

	/**
	 * ajout de la docu + methods
	 * @post | result.equals(Color.GREEN)
	 */
	@Override
	public Color getColor() {
		return Color.GREEN;
	}
	
    /**
     * ajout de la docu
     * @inspects | world
     * @mutates | creature
     */
    @Override
    public void applyBehavior(World world, Creature creature)
    {
        processForwardMovement(world, creature);
        processTurning(world, creature);
    }


    /**
     * méthode vide
     * 
     */
    private void processForwardMovement(World world, Creature creature)
    {
    	// Retrieve the output from the forward movement neuron
        int moveForwardOutput = neuralNetwork.getMoveForwardNeuron().computeOutput(world, creature);

        // If the output value is greater than zero, the creature moves forward
        if (moveForwardOutput > 0) {
            creature.moveForward(world, new Vector(0, 0)); // Add a vector with 0,0 because the creature has no drift
        }
    }

    /**
     * LEGIT
     */
    private void processTurning(World world, Creature creature)
    {

    	int clockVal = neuralNetwork.getTurnClockwiseNeuron().computeOutput(world, creature);
    	int counterVal = neuralNetwork.getTurnCounterclockwiseNeuron().computeOutput(world, creature);
    	
    	if ((Math.abs(clockVal - counterVal) > 150)) { //if the 2 values are substantially different
    		if (clockVal > counterVal) {creature.turnClockwise();}
    		else {creature.turnCounterclockwise();}
    	}

    }
	



    /**
     * ajout de la docu
     * @inspects | chromosome
     * @creates | result
     */
	@Override
	public NeuralNetworkBehavior copyWithChromosome(Chromosome chromosome)
	{
		return new NeuralNetworkBehavior(chromosome);
	}
}
