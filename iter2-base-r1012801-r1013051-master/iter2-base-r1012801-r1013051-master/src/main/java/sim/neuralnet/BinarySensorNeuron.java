package sim.neuralnet;

import sim.Creature;
import sim.World;


public abstract class BinarySensorNeuron extends SensorNeuron
{
	/**
	 * ajout de la docu
	 * @inspects | world
	 */
    @Override
    /**
     * méthode vide
     * @inspects | creature
     * @inspects | world
     * @post | result == -750 || result == 750
     */
    public int computeOutput(World world, Creature creature)
    {
    	return detect(world, creature) ? 750 : -750;
    }

    public abstract boolean detect(World world, Creature creature);
}
