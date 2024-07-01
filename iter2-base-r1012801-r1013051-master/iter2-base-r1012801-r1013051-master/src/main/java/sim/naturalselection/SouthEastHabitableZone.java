package sim.naturalselection;

import sim.World;
import util.Point;

public class SouthEastHabitableZone implements NaturalSelection {

	/**
	 * ajout de la docu
	 * @inspects | world
	 * @pre | pos != null
	 */
	@Override
	public boolean survives(World world, Point pos) {
    	return (pos.getX() >= 2 * world.getWidth() / 3) &&
    			(pos.getY() >= 2* world.getHeight() / 3);
	}

}
