package sim.naturalselection;


import sim.World;
import util.Point;

/**
 * @immutable
 */
public interface NaturalSelection
{
	/**
	 * ajout de la docu
	 * @pre | Point.isWithin(position, world.getWidth(), world.getHeight())
	 * @inspects | world
	 * @pre | position != null
	 */
    public boolean survives(World world, Point position);
}
