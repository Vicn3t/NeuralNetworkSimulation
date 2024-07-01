package sim.neuralnet;

import sim.Creature;
import sim.World;

/**
 * @immutable
 */
public class VerticalPositionSensorNeuron extends SensorNeuron
{
    @Override
    /**
     * méthode vide
     * @inspects | world
     * @inspects | creature
     * @creates | result
     * @post | result >= -1000 && result <= 1000
     */
    public int computeOutput(World world, Creature creature)
    {
        
    	// Get the creature's current y-coordinate
        int yPosition = creature.getPosition().getY();
        int worldHeight = world.getHeight() - 1;

        // Calculate a scaled value between -1000 and 1000
        // -1000 corresponds to the top of the world, and 1000 to the bottom of the world
        int output = (yPosition * 2000 / worldHeight) - 1000;

        return output;
    }
}
