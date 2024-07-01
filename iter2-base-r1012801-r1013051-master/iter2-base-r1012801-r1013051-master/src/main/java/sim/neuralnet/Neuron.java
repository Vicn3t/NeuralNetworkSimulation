package sim.neuralnet;

import sim.Creature;
import sim.World;

public interface Neuron
{
	/**
	 * ajout de la docu
	 * @inspects | world
	 * @inspects | creature
	 */
    int computeOutput(World world, Creature creature);
}
