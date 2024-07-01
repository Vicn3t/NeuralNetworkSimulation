package sim.behaviors;

import sim.Chromosome;
import sim.Creature;
import sim.World;
import util.Color;
import util.RandomUtil;
import util.Vector;
import static util.Logic.*;

/**
 * @immutable
 * @invar | getColor() .equals( Color.BLUE )
 *
 */
public class BehaviorB extends Behavior
{
	/**
	 * ajout de la docu
	 * @inspects | chromosome
	 */
	public BehaviorB(Chromosome chromosome)
	{
		super(chromosome);
	}

	/**
	 * ajout de la docu
	 * @post | result.equals(Color.BLUE)
	 */
	@Override
	public Color getColor() {
		return Color.BLUE;
	}
	
	/**
	 * @inspects | world
	 * @mutates | creature
	 * @pre | world != null
	 * @pre | creature != null
	 * @post If the creature is located at the edge of the world, it does not change its position.
	 * 	| implies(world.isLimPos(old(creature.getPosition())), old(creature.getPosition()).equals(creature.getPosition()))
	 * @post If the creature is located at the edge of the world, it does not change its orientation. 
	 * | implies(world.isLimPos(old(creature.getPosition())), old(creature.getOrientation()).equals(creature.getOrientation()))
	 * @post If the creature is not located at the edge of the world, it either moves or it turns.
	 * | implies(!world.isLimPos(old(creature.getPosition())), iff(old(creature.getPosition()).equals(creature.getPosition()), !old(creature.getOrientation()).equals(creature.getOrientation())))
	 */
	@Override
    public void applyBehavior(World world, Creature creature)
    {
        var position = creature.getPosition();
        
        //creatures that are not touching a wall are not done
        if (!world.isLimPos(position)) {
        	creature.moveForward(world, new Vector(0,0));
        	//if it has not moved, it turns randomly
        	if (creature.getPosition().equals(position)) {
            	boolean turnleft = RandomUtil.bool();
            	if (turnleft) { creature.turnCounterclockwise(); }
            	else {creature.turnClockwise(); }
        	}
        }
    }
	
	/**
	 * ajout de la docu
	 * @inspects | chromosome
	 * @creates | result
	 */
	@Override
	public BehaviorB copyWithChromosome(Chromosome chromosome)
    {
    	return new BehaviorB(chromosome);
    }
    

}