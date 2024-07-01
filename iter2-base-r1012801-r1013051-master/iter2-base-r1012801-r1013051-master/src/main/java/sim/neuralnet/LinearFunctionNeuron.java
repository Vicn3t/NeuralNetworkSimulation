package sim.neuralnet;

public class LinearFunctionNeuron extends ActivationFunctionNeuron
{
	/**
	 * Constructor
	 */
	public LinearFunctionNeuron()
	{
		super();
	}
	
    @Override
    /**
     * méthode vide
     * @inspects | input
     * @post | result >= -1000 && result <= 1000
     */
    public int applyActivationFunction(int input)
    {
    	return Math.max(-1000, Math.min(1000, input));
    }
    
    
    /**
     * @pre | 0 <= index
     * @pre | index < getDependencies().size()
     * @post | getBias() == old(getBias())
     * @post | getDependencies().size() == old(getDependencies().size())+1
     * @post | getDependencies().size() <= 7
     * To make a sensor Neuron have more impact on super.computeOutput we can link to it twice (with the same weight)
     * The additional reference is appended at the end of getDependencies().
     * 
     * Fails silently like super.connect
     */
    public void doubleSensor(int index) {
		var deps = getDependencies();
		var p = deps.get(index);
		if (deps.size() == 7) {
		}
		else {
			deps.add(p);
		setDependencies(deps);
		}
    }
    
    
}
