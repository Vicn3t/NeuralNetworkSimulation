package sim.naturalselectionTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sim.Chromosome;
import sim.Constants;
import sim.Creature;
import sim.World;
import sim.behaviors.BehaviorA;
import sim.naturalselection.BorderHabitableZone;
import util.Orientation;
import util.Point;

class BorderHabitableZoneTest {

	public World world;
	public BorderHabitableZone nsel;

	@BeforeEach
	public void setup() {
		Creature creature1 = new Creature(new BehaviorA(Chromosome.createRandom()),new Point(0, 0) , new Orientation(0));
		Creature creature2 = new Creature(new BehaviorA(Chromosome.createRandom()),new Point(230, 230) , new Orientation(0));
		Creature[] creatures = new Creature[] {creature1, creature2};
		nsel = new BorderHabitableZone(Constants.WSIZE/4);
		world = new World(Constants.WSIZE, Constants.WSIZE, creatures);
	}

	@Test
	public void BorderHabitableZoneConstructorIllegalArguments()
	{
		assertThrows(IllegalArgumentException.class, () -> new BorderHabitableZone(-1));
	}
	
	@Test
	public void getters() {
		assertEquals(Constants.WSIZE / 4, nsel.getBorderSize());
	}
	
	@Test
	public void survives()
	{
		assertFalse(nsel.survives(world, new Point(100,100)));
		assertTrue(nsel.survives(world, new Point(230,230)));
	}
}
