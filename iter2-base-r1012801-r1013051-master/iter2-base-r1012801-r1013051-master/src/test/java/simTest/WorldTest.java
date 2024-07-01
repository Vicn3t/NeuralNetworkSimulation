package simTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sim.Chromosome;
import sim.Constants;
import sim.Creature;
import sim.World;
import sim.behaviors.BehaviorA;
import util.Orientation;
import util.Point;

class WorldTest {

	public World world;
	
	@BeforeEach
	public void setup(){
		Creature creature1 = new Creature(new BehaviorA(Chromosome.createRandom()),new Point(0, 0) , new Orientation(0));
		Creature creature2 = new Creature(new BehaviorA(Chromosome.createRandom()),new Point(0, 0) , new Orientation(0));
		Creature[] creatures = new Creature[] {creature1, creature2}; 
        world = new World(Constants.WSIZE, Constants.WSIZE, creatures);
    }
	@Test
	public void AssertionTests() {
		assertEquals(Constants.WSIZE, world.getWidth());
		assertEquals(Constants.WSIZE, world.getHeight());
	}
	@Test
	public void isInsideTest() {
		assertTrue(world.isInside(new Point(1,1)));
		assertFalse(world.isInside(new Point(Constants.WSIZE +1,0)));
	}
	
	@Test
	public void isLimPosTest() {
		assertFalse(world.isLimPos(new Point(-1,-1)));
		
		assertTrue(world.isLimPos(new Point(0,0)));
		assertTrue(world.isLimPos(new Point(0,10)));
		assertTrue(world.isLimPos(new Point(10,0)));
		assertTrue(world.isLimPos(new Point(world.getWidth()-1,10)));
		assertTrue(world.isLimPos(new Point(10,world.getHeight()-1)));
		assertTrue(world.isLimPos(new Point(world.getWidth()-1,world.getHeight()-1)));
	}
	@Test
	public void isFreeTest() {
		assertTrue(world.isFree(new Point(1,1)));
		assertFalse(world.isFree(new Point(0,0)));
		assertFalse(world.isFree(new Point(-1,-1)));
	}
	@Test 
	public void stepTest() {
		world.step();
	}
	
}
	



