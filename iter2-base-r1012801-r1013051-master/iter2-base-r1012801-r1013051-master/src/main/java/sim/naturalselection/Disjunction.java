package sim.naturalselection;

import sim.World;
import util.Point;

public class Disjunction implements NaturalSelection
{
	private final NaturalSelection area1;
	private final NaturalSelection area2;
	
	/**
	 * ajout de la docu
	 * @throws IllegalArgumentException | area1 == null || area2 == null
	 * @inspects | area2
	 * @inspects | area1
	 */
	public Disjunction(NaturalSelection area1, NaturalSelection area2)
	{
		if (area1 == null || area2 == null) {
			throw new IllegalArgumentException();
		}
		this.area1 = area1;
		this.area2 = area2;
	}
	/**
	 * méthode vide
	 * @inspects | world
	 * @inspects | position
	 */
	@Override
	public boolean survives(World world, Point position)
	{
		// Check if the position satisfies the survival criteria of either area1 or area2
	    return area1.survives(world, position) || area2.survives(world, position);
	}
}
