package simTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sim.Chromosome;
import sim.Constants;
import sim.Creature;
import sim.Simulation;
import sim.World;
import sim.behaviors.BehaviorA;
import sim.naturalselection.BorderHabitableZone;
import sim.naturalselection.NaturalSelection;
import util.Orientation;
import util.Point;

class SimulationTest {

	public Simulation simulation;
	public World world;
	public NaturalSelection nsel;
	
	@BeforeEach
	public void setup() {
		Creature creature1 = new Creature(new BehaviorA(Chromosome.createRandom()),new Point(0, 0) , new Orientation(0));
		Creature creature2 = new Creature(new BehaviorA(Chromosome.createRandom()),new Point(230, 230) , new Orientation(0));
		Creature[] creatures = new Creature[] {creature1, creature2};
		nsel = new BorderHabitableZone(Constants.WSIZE/2);
		world = new World(Constants.WSIZE, Constants.WSIZE, creatures);
		simulation = new Simulation(Constants.WSIZE,2,nsel);
	}
	
	@Test
	public void getters()
	{
		assertEquals(2, simulation.getPopulationSize());
		assertEquals(new BorderHabitableZone(Constants.WSIZE/2).getClass(), simulation.getNaturalSelection().getClass());
	}

	@Test
	public void nextgeneration()
	{
		simulation.nextGeneration();
	}
	
}
