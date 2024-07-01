package sim.naturalselection;

import sim.World;
import util.Point;

public class CircularHabitableZone implements NaturalSelection
{

	/**
	 * ajout de la docu
	 * @invar | center != null
	 */
    private final Point center;


	/**
	 * ajout de la docu
	 * @invar | radiusSquared > 0
	 */
    private final int radiusSquared;

	/**
	 * ajout de la docu
	 * @inspects | center
	 * @throws IllegalArgumentException | radius <= 0
	 * @throws IllegalArgumentException | center == null
	 */
    public CircularHabitableZone(Point center, int radius)
    {
    	if (radius <= 0 || center == null) {
    		throw new IllegalArgumentException();
    	}
        this.center = center;
        this.radiusSquared = radius * radius;
    }

	/**
	 * ajout de la docu
	 * @inspects | world
	 * @pre | position != null
     * méthode vide
     */
    @Override
    public boolean survives(World world, Point position)
    {
    	 // Calculate the squared difference in x and y coordinates between the position and the center
        int dx = position.getX() - center.getX();
        int dy = position.getY() - center.getY();

        // Calculate the distance squared between the position and the center
        int distanceSquared = dx * dx + dy * dy;

        // Check if the position lies within the circular zone by comparing distanceSquared to radiusSquared
        return distanceSquared <= radiusSquared;
    }
}