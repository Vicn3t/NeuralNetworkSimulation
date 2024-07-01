package sim.neuralnetTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sim.Chromosome;
import sim.Constants;
import sim.Creature;
import sim.World;
import sim.behaviors.BehaviorA;
import sim.neuralnet.HorizontalPositionSensorNeuron;
import util.Orientation;
import util.Point;

class HorizontalPositionSensorNeuronTest {

private Creature creature1;
private Creature creature2;
private World world;
private HorizontalPositionSensorNeuron weight;

@BeforeEach
public void setup() {
	creature1 = new Creature(new BehaviorA(Chromosome.createRandom()), new Point(0, 0), new Orientation(0));
	creature2 = new Creature(new BehaviorA(Chromosome.createRandom()), new Point(Constants.WSIZE - 1, 0),
			new Orientation(0));
	Creature[] creatures = new Creature[] { creature1, creature2 };
	world = new World(Constants.WSIZE, Constants.WSIZE, creatures);
	weight = new HorizontalPositionSensorNeuron();

}

@Test
public void computeOutput() {
	assertEquals(-1000, weight.computeOutput(world, creature1));
	assertEquals(1000, weight.computeOutput(world, creature2));
	Creature creature3 = new Creature(new BehaviorA(Chromosome.createRandom()), new Point(10, 10), new Orientation(0));
	assertEquals(-917, weight.computeOutput(world, creature3));
}
}
