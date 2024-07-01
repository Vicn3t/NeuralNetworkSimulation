package sim.neuralnet;

import sim.Creature;
import sim.World;
import util.Orientation;
import util.Point;


public class FreePassageSensorNeuron extends BinarySensorNeuron
{
	/**
	 * ajout de la docu
	 * @invar | orientation != null
	 */
    private final Orientation orientation;

    /**
     * méthode vide
     * ajout de la docu
     * @throws IllegalArgumentException | orientation == null
     * @inspects | orientation
     */
    public FreePassageSensorNeuron(Orientation orientation)
    {
    	if (orientation == null) {
            throw new IllegalArgumentException("Orientation cannot be null");
        }
        this.orientation = orientation;
    }

    @Override
    /**
     * méthode vide + ajout de la docu
     * @inspects | world
     * @inspects | creature
     */
    public boolean detect(World world, Creature creature)
    {
    	Point currentPos = creature.getPosition();
    	
    	Point targetPos = currentPos.move(creature.getOrientation().compose(orientation).toVector());
    	
    	 return world.isInside(targetPos) && world.isFree(targetPos);
    }
}
