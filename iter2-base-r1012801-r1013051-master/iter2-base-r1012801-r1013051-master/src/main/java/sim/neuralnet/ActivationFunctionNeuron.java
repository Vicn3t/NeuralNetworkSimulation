package sim.neuralnet;

import util.Pair;

import java.util.ArrayList;

import sim.Creature;
import sim.World;

/**
 * The Neuron references should be freely accessible by the client
 */
public abstract class ActivationFunctionNeuron implements Neuron
{
	/**
	 * @representationObject
	 * @representationObjects
	 * @invar | dependencies != null
	 * @invar | dependencies.stream().allMatch(p -> p != null)
	 * @invar | dependencies.stream().allMatch(p -> p.getFirst() != null)
	 * @invar | dependencies.stream().allMatch(p -> p.getSecond() != null)
	 * @invar | dependencies.size() <= 7
	 */
    private ArrayList<Pair<Neuron, Integer>> dependencies;

    private int bias;

    /**
     * @return
     * @post | result != null
	 * @post | result.stream().allMatch(p -> p != null)
	 * @post | result.stream().allMatch(p -> p.getFirst() != null)
	 * @post | result.stream().allMatch(p -> p.getSecond() != null)
	 * @post | result.size() <= 7
     */
    public ArrayList<Pair<Neuron, Integer>> getDependencies() {
    	return dependencies;
    }
    
    /**
     * ajout de la docu
     * pre: deps has correct length
     * @pre | deps != null
     * @pre | deps.stream().allMatch(p -> p != null)
     * @pre | deps.stream().allMatch(p -> p.getFirst() != null)
     * @pre | deps.stream().allMatch(p -> p.getSecond() != null)
     * @mutates | this
     * @post | getDependencies() == deps
     * @post | getBias() == old(getBias())
     */
    public void setDependencies(ArrayList<Pair<Neuron, Integer>> deps) {
    	dependencies = deps;
    }

    /**
     * ajout de la docu
     * Initializes with getBias = 0 and getDependencies is empty
     * @post | this.getDependencies().isEmpty()
     * @post | this.getDependencies().size() == 0
     * @post | this.getBias() == 0
     * */
    public ActivationFunctionNeuron()
    {
    	this.dependencies = new ArrayList<>();
        this.bias = 0;
    }

    /** méthode modifiée
     * If the connection should fail, do nothing and return false
     */
    public boolean connect(Neuron dependency, int weight){
    	if (dependencies.size() >= 7) {
            return false;
    	}
    	//if (dependencies.size() == 7) {
    	//	dependencies.remove(6);
    	//	dependencies.add(new Pair<Neuron, Integer>(dependency , weight));
    	//	return true;
    	//}
        var p = new Pair<Neuron, Integer>(dependency, weight);
    	dependencies.add(p);
    	return true;

    }

    /*
     * @mutates | this
     * @post | this.getBias() == bias
     * @post | this.getDependencies().equals(old(this.getDependencies()))
     */
    public void setBias(int bias)
    {
        this.bias = bias;
    }
    

	/**
	 * ajout de la docu
	 */
    public int getBias() {
    	return bias;
    }



    @Override
    /**
     * méthode vide et ajout de la docu
     * @pre | world != null
     * @pre | creature != null
     * @post | result >= -1000 && result <= 1000
     */
    public int computeOutput(World world, Creature creature)
    {
    	int weightedSum = 0;

        for (Pair<Neuron, Integer> dependency : dependencies) {
            Neuron neuron = dependency.getFirst();
            int weight = dependency.getSecond();
            int input = neuron.computeOutput(world, creature);
            weightedSum += (input * weight) / 1000;
        }

        weightedSum += bias;

        return applyActivationFunction(weightedSum);
    }
	
    public abstract int applyActivationFunction(int input);
}

