package simTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sim.Chromosome;
import sim.Creature;
import sim.World;
import sim.behaviors.BehaviorA;
import sim.behaviors.BehaviorB;
import util.Orientation;
import util.Point;

class CreatureTest {
	
	public Chromosome chromosome;
	public BehaviorA behaviorA;
	public Creature creature;
	public World world;
	
	
	@BeforeEach
	public void setup() {
		 chromosome = new Chromosome(new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
		 creature = new Creature(new BehaviorA(chromosome),new Point(0, 0) , new Orientation(0));
		 world = new World(10, 10, new Creature[0]);	
	}
	
	@Test
	public void constructorTest()
	{
        assertEquals(new Point(0, 0), creature.getPosition());        
        assertTrue(new Orientation(0).isEqual(creature.getOrientation()));
        assertEquals(new BehaviorA(chromosome).getClass(),(creature.getBehavior()).getClass());
        assertThrows(IllegalArgumentException.class, () -> new Creature(null, new Point(0, 0), new Orientation(0)));
        assertThrows(IllegalArgumentException.class, () -> new Creature(new BehaviorA(chromosome), null, new Orientation(0)));
        assertThrows(IllegalArgumentException.class, () -> new Creature(new BehaviorA(chromosome), new Point(0, 0), null));
    }
	
	private static Creature createCreature(Point position, Orientation orientation)
	{
		var chromosome = new Chromosome(
				new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
		var behavior = new BehaviorA(chromosome);
		
		return new Creature(behavior, position, orientation);
	}

	@Test 
	public void turnClockwiseCounterclockwiseTest(){
        creature.turnClockwise();
        System.out.println(creature.getOrientation());
        assertTrue(Orientation.northEast().isEqual(creature.getOrientation()));
        creature.turnCounterclockwise();
        assertTrue(Orientation.north().isEqual(creature.getOrientation()));
    }

	@Test
	public void equalCreatures()
	{
		var position = new Point(0, 0);
		var orientation = Orientation.north();
		var creature2 = createCreature(position, orientation);
		assertTrue(creature.isEqual(creature2));
	}

	@Test
	public void unequalCreatures()
	{
		var creature2 = new Creature(new BehaviorA(chromosome),new Point(1, 1), Orientation.north());
		var creature3 = new Creature(new BehaviorA (chromosome), new Point(0,0), Orientation.south());
		var creature4 = new Creature(new BehaviorB(chromosome), new Point(0, 0), Orientation.north());
		assertFalse(creature.isEqual(creature2));
		assertFalse(creature.isEqual(creature3));
		assertFalse(creature.isEqual(creature4));
	}

	@Test
	public void unequalCreaturesDifferentOrientations()
	{
		var position = new Point(51, 22);
		var orientation1 = Orientation.southEast();
		var orientation2 = Orientation.southWest();
		var creature1 = createCreature(position, orientation1);
		var creature2 = createCreature(position, orientation2);
		
		assertFalse(creature1.isEqual(creature2));
	}
	
	@Test
	public void unequalCreaturesNull()
	{
		var position = new Point(51, 22);
		var orientation = Orientation.southEast();
		var creature = createCreature(position, orientation);
		
		assertFalse(creature.isEqual(null));
	}
	

	@Test
	public void moveForwardTest() {
		
		var world = new World(10, 10, new Creature[0]);
		var position = new Point(0, 0);
        var orientation = Orientation.east();
        var creature = createCreature(position, orientation);
        
        creature.moveForward(world, new util.Vector(0, 0));
        assertEquals(new Point(1, 0), creature.getPosition());
        
        creature.performAction(world);
		assertEquals(new Point(1, 0), creature.getPosition());
	}
	
	@Test
	public void giveCopyTest() {
		var world = new World(10, 10, new Creature[0]);
		var position = new Point(0, 0);
        var orientation = Orientation.east();
        var creature = createCreature(position, orientation);
		var copy = creature.giveCopy();

		assertTrue(creature.isEqual(copy));
	}
	}
