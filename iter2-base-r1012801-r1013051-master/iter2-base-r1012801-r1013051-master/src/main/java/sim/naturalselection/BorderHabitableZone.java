package sim.naturalselection;

import sim.World;
import util.Point;
import util.Logic;

/*
 * @invar | GetBorderSize() > 0
 */
public class BorderHabitableZone implements NaturalSelection
{
	/**
	 * ajout de la docu
	 * @invar | borderSize > 0
	 */
    private final int borderSize;
    
    /**
     * ajout de la docu
     * @throws IllegalArgumentException | borderSize <= 0
     * @post | getBorderSize() == borderSize
     */
    public BorderHabitableZone(int borderSize)
    {
    	if (borderSize <= 0) {
    		throw new IllegalArgumentException();
    	}
        this.borderSize = borderSize;
    }

    /**
     * méthode vide + ajout de la docu
     * @inspects | position
     * @inspects | world
     * @pre | world != null
     * @post If the position is within the border zone, the creature survives.
     *      | Logic.implies(position.getX() < getBorderSize() || position.getX() > world.getWidth() - getBorderSize() || position.getY() < getBorderSize() || position.getY() > world.getHeight() - getBorderSize(), result == true)
     * @post If the position is not within the border zone, the creature does not survive.
     *  	| Logic.implies(!(position.getX() < getBorderSize() || position.getX() > world.getWidth() - getBorderSize() || position.getY() < getBorderSize() || position.getY() > world.getHeight() - getBorderSize()), result == false)
     */
    @Override
    public boolean survives(World world, Point position){
        // Retrieve the dimensions of the world
        int worldWidth = world.getWidth();
        int worldHeight = world.getHeight();

        // Check if the position falls within the border zone by evaluating against borderSize
        boolean withinLeftBorder = position.getX() < borderSize;
        boolean withinRightBorder = position.getX() > (worldWidth - borderSize);
        boolean withinTopBorder = position.getY() < borderSize;
        boolean withinBottomBorder = position.getY() > (worldHeight - borderSize);

        // The creature survives if it is within any of the borders
        return withinLeftBorder || withinRightBorder || withinTopBorder || withinBottomBorder;
    }

	/**
	 * ajout de la docu
	 * @post | result > 0
	 */
	public int getBorderSize() {
		return borderSize;
	}
}
