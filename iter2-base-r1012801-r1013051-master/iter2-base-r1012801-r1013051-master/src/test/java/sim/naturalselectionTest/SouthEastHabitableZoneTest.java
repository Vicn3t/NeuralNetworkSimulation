package sim.naturalselectionTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sim.Chromosome;
import sim.Constants;
import sim.Creature;
import sim.World;
import sim.behaviors.BehaviorA;
import sim.naturalselection.SouthEastHabitableZone;
import util.Orientation;
import util.Point;

class SouthEastHabitableZoneTest {

	public World world;
	
	@BeforeEach
	public void setup() {
		Creature creature1 = new Creature(new BehaviorA(Chromosome.createRandom()), new Point(0, 0),
				new Orientation(0));
		Creature creature2 = new Creature(new BehaviorA(Chromosome.createRandom()), new Point(230, 230),
				new Orientation(0));
		Creature[] creatures = new Creature[] { creature1, creature2 };
		world = new World(Constants.WSIZE, Constants.WSIZE, creatures);
	}

	@Test
	public void survives() {
		SouthEastHabitableZone nsel = new SouthEastHabitableZone();
		assertFalse(nsel.survives(world, new Point(0, 0)));
		assertTrue(nsel.survives(world, new Point(230, 230)));
	}
}
