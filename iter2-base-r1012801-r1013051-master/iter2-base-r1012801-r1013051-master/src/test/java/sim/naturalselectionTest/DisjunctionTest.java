package sim.naturalselectionTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sim.Chromosome;
import sim.Constants;
import sim.Creature;
import sim.World;
import sim.behaviors.BehaviorA;
import sim.naturalselection.BorderHabitableZone;
import sim.naturalselection.CircularHabitableZone;
import sim.naturalselection.Disjunction;
import util.Orientation;
import util.Point;

class DisjunctionTest {

	public World world;
	public Disjunction nsel;
	public BorderHabitableZone nsel1;
	public CircularHabitableZone nsel2;
	private CircularHabitableZone nsel3;
	private Disjunction nsel4;


	@BeforeEach
	public void setup() {
		Creature creature1 = new Creature(new BehaviorA(Chromosome.createRandom()),new Point(0, 0) , new Orientation(0));
		Creature creature2 = new Creature(new BehaviorA(Chromosome.createRandom()),new Point(230, 230) , new Orientation(0));
		Creature[] creatures = new Creature[] {creature1, creature2};
		nsel2 = new CircularHabitableZone(new Point(60,60), 10);
		nsel1 = new BorderHabitableZone(Constants.WSIZE/4);
		nsel3 = new CircularHabitableZone(new Point(0,0), 10);
		
		nsel = new Disjunction(nsel2, nsel1);
		nsel4 = new Disjunction(nsel3, nsel1);
		world = new World(Constants.WSIZE, Constants.WSIZE, creatures);
	}

	@Test
	public void DisjunctionConstructorIllegalArguments() {
		assertThrows(IllegalArgumentException.class, () -> new Disjunction(null, nsel1));
		assertThrows(IllegalArgumentException.class, () -> new Disjunction(nsel2, null));
		assertThrows(IllegalArgumentException.class, () -> new Disjunction(null, null));
	}
	
	@Test
	public void survivesarea1()
	{
		assertTrue(nsel.survives(world, new Point(60,60)));
		assertFalse(nsel.survives(world, new Point(100,100)));
	}
	@Test
	public void survivesarea2()
	{
        assertFalse(nsel.survives(world, new Point(100,100)));
        assertTrue(nsel.survives(world, new Point(230,230)));
	}
	
	@Test
	public void survivesboth() {
		assertTrue(nsel4.survives(world, new Point(0, 0)));
		assertFalse(nsel4.survives(world, new Point(100, 100)));
	}
	
}
