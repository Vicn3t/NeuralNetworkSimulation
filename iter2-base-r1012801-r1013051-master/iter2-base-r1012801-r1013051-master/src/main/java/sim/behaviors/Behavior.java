package sim.behaviors;


// import java.util.Arrays;

import sim.Chromosome;
import sim.Creature;
import sim.World;
import util.Color;

/**
 * @immutable
 */
public abstract class Behavior
{
	/**
	 * ajout de la docu
	 * @invar | chromosome != null
	 */
	private Chromosome chromosome;
	
	/**
	 * methode vide 
	 * ajout de la docu
	 * @throws IllegalArgumentException | chromosome == null
	 * @inspects | chromosome
	 * 
	 */
	public Behavior(Chromosome chromosome)
	{
		if (chromosome == null) {
            throw new IllegalArgumentException("Chromosome cannot be null");
        }
		this.chromosome = chromosome;
	}
	
	/**
	 * méthode vide, 
	 * ajout de la docu
	 * @post | result != null
	 * 
	 */
	public Chromosome getChromosome()
	{
		return this.chromosome;
	}
		
    public abstract void applyBehavior(World world, Creature creature);
    
    /**
     * LEGIT
     */
    public Color getColor() {
    	return Color.BLACK;
    }
    
    /**
     * post: the copy has the specified chromosome
     * @creates | result
     * @post  | result != null
     * @post | result.getChromosome() == chromosome
     */
    public abstract Behavior copyWithChromosome(Chromosome chromosome);
}


