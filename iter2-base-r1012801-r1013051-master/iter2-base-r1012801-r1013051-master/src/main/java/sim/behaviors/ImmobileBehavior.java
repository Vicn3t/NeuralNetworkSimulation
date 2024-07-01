package sim.behaviors;

import sim.Chromosome;
import sim.Creature;
import sim.World;
import util.Color;


/**
 * @invar | getColor().equals( Color.WHITE )
 * 
 */
public class ImmobileBehavior extends Behavior
{
	public ImmobileBehavior(Chromosome chromosome)
	{
		super(chromosome);
	}
	

	/**
	 * ajout de la docu + methods
	 * @post | result.equals(Color.WHITE)
	 */
	@Override
	public Color getColor() {
		return Color.WHITE;
	}
	
	/**
	 * méthode vide à ne pas modifier
	 * ajout de la docu
	 * @pre | world != null
	 * @pre | creature != null
	 * No operation as the creature remains immobile
	 */
    @Override
    public void applyBehavior(World world, Creature creature)
    {
    	// No operation as the creature remains immobile
    }
    

    /**
     * ajout de la docu
     * @inspects | chromosome
     * @creates | result
     */
    @Override
    public ImmobileBehavior copyWithChromosome(Chromosome chromosome)
    {
    	return new ImmobileBehavior(chromosome);
    }
    
}

