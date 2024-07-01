package sim.neuralnet;

import sim.Creature;
import sim.World;

/**
 * @immutable
 */
public abstract class SensorNeuron implements Neuron {

	@Override
	public abstract int computeOutput(World world, Creature creature);

}
