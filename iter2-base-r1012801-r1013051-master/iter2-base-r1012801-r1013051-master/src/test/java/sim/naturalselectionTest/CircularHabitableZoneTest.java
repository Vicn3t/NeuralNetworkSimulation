package sim.naturalselectionTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import sim.Chromosome;
import sim.Creature;
import sim.World;
import sim.behaviors.BehaviorA;
import sim.naturalselection.CircularHabitableZone;
import util.Orientation;
import util.Point;
import sim.Constants;


class CircularHabitableZoneTest {

	public World world;
	public CircularHabitableZone nsel;

	@BeforeEach
	public void setup() {
		Creature creature1 = new Creature(new BehaviorA(Chromosome.createRandom()),new Point(0, 0) , new Orientation(0));
		Creature creature2 = new Creature(new BehaviorA(Chromosome.createRandom()),new Point(230, 230) , new Orientation(0));
		Creature[] creatures = new Creature[] {creature1, creature2};
		nsel = new CircularHabitableZone(new Point(1,1), 10);
		world = new World(Constants.WSIZE, Constants.WSIZE, creatures);
	}

	@Test
	public void CircularHabitableZoneConstructorIllegalArguments() {
		assertThrows(IllegalArgumentException.class, () -> new CircularHabitableZone(null, 10));
		assertThrows(IllegalArgumentException.class, () -> new CircularHabitableZone(new Point(1, 1), -1));
	}
	
	@Test
	public void survives()
	{
		assertTrue(nsel.survives(world, new Point(0,0)));
        assertFalse(nsel.survives(world, new Point(230,230)));
	}

}
