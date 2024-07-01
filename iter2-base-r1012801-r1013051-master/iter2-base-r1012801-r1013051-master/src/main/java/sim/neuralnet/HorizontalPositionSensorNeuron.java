package sim.neuralnet;

import sim.Creature;
import sim.World;


public class HorizontalPositionSensorNeuron extends SensorNeuron
{
    @Override
    /**
     * méthode vide + docu
     * @inspects | world
     * @inspects | creature
     * @creates | result
     * @post | result >= -1000 && result <= 1000
     */
    public int computeOutput(World world, Creature creature)
    {
    	// Get the creature's current x-coordinate
        int xPosition = creature.getPosition().getX();
        int worldWidth = world.getWidth() -1;

        // Calculate a scaled value between -1000 and 1000
        // -1000 corresponds to the leftmost position, and 1000 to the rightmost
        int output = (xPosition * 2000 / worldWidth) - 1000;
        return output;
    }
}
