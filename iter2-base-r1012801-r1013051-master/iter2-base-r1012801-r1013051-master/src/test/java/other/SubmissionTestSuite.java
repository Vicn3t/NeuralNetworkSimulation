package other;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import sim.Chromosome;
import sim.Constants;
import sim.Creature;
import sim.Simulation;
import sim.World;
import sim.behaviors.Behavior;
import sim.behaviors.BehaviorA;
import sim.behaviors.BehaviorB;
import sim.behaviors.ImmobileBehavior;
import sim.behaviors.NeuralNetworkBehavior;
import sim.naturalselection.BorderHabitableZone;
import sim.naturalselection.CircularHabitableZone;
import sim.naturalselection.Disjunction;
import sim.naturalselection.NaturalSelection;
import sim.naturalselection.SouthEastHabitableZone;
import sim.neuralnet.ActivationFunctionNeuron;
import sim.neuralnet.BinarySensorNeuron;
import sim.neuralnet.FreePassageSensorNeuron;
import sim.neuralnet.HorizontalOrientationSensorNeuron;
import sim.neuralnet.HorizontalPositionSensorNeuron;
import sim.neuralnet.LinearFunctionNeuron;
import sim.neuralnet.NeuralNetwork;
import sim.neuralnet.Neuron;
import sim.neuralnet.OrientationSensorNeuron;
import sim.neuralnet.RectifiedLinearUnitFunctionNeuron;
import sim.neuralnet.SensorNeuron;
import sim.neuralnet.VerticalOrientationSensorNeuron;
import sim.neuralnet.VerticalPositionSensorNeuron;
import util.Color;
import util.Orientation;
import util.Pair;
import util.Point;
import util.Vector;

class SubmissionTestSuite {
	public static World createWorld()
	{
		var chromosome = Chromosome.createRandom();
		var behavior = new ImmobileBehavior(chromosome);
		var creature = new Creature(behavior, new Point(0, 0), Orientation.north()); 
		var population = new Creature[] { creature };
		var world = new World(Constants.WSIZE, Constants.WSIZE, population);
		
		return world;
	}
	
	@Test
	public void mkBehaviors()
	{
		var chromosome = Chromosome.createRandom();
		Behavior b;
		
		b = new BehaviorA(chromosome);
		b = new BehaviorB(chromosome);
		b = new ImmobileBehavior(chromosome);
		b = new NeuralNetworkBehavior(chromosome);
	}
	
	@Test
	public void checkBehavior()
	{
		var chromosome = Chromosome.createRandom();
		Behavior b = new ImmobileBehavior(chromosome);
		b = b.copyWithChromosome(chromosome);
		
		chromosome = b.getChromosome();
		Color color = b.getColor();

		var world = createWorld();
		var creature = world.getPopulation()[0];
		b.applyBehavior(world, creature);
	}
	
	@Test
	public void mkCreature()
	{
		var chromosome = Chromosome.createRandom();
		Behavior behavior = new ImmobileBehavior(chromosome);
		var position = new Point(0, 0);
		Creature c = new Creature(behavior, position, Orientation.north());
	}
	
	@Test
	public void checkActivationFunctionNeuron()
	{
		Neuron sensor = new HorizontalOrientationSensorNeuron();
		ActivationFunctionNeuron neuron = new RectifiedLinearUnitFunctionNeuron();
		
		boolean b = neuron.connect(sensor, 500);
		
		ArrayList<Pair<Neuron, Integer>> pairs = neuron.getDependencies();
		neuron.setDependencies(pairs);
		neuron.setBias(neuron.getBias());
	}
	
	@Test
	public void mkLinearFunctionNeuron()
	{
		Neuron vpsn = new VerticalPositionSensorNeuron();
		var neuron = new LinearFunctionNeuron();
		neuron.connect(vpsn, 10);
		neuron.doubleSensor(0);
	}

	@Test
	public void neuronHierarchy()
	{
		{	
			ActivationFunctionNeuron n = new RectifiedLinearUnitFunctionNeuron();
		}
		{
			ActivationFunctionNeuron n = new LinearFunctionNeuron();
		}
		{
			BinarySensorNeuron n = new FreePassageSensorNeuron(Orientation.north());
		}
		{
			OrientationSensorNeuron n = new HorizontalOrientationSensorNeuron();
		}
		{
			OrientationSensorNeuron n = new VerticalOrientationSensorNeuron();
		}
		{
			SensorNeuron n = new HorizontalPositionSensorNeuron();
		}
		{
			SensorNeuron n = new VerticalPositionSensorNeuron();
		}
	}

	@Test
	public void checkNeuron()
	{
		World world = createWorld();
		var creature = world.getPopulation()[0];
		
		Neuron n = new HorizontalPositionSensorNeuron();
		n.computeOutput(world, creature);
	}
	
	@Test
	public void neuralNetwork()
	{
		var nn = new NeuralNetwork();
		nn = NeuralNetwork.fromChromosome(Chromosome.createRandom());
		
		SensorNeuron[] inputs = nn.getInputNeurons();
		ActivationFunctionNeuron[] outputs = nn.getOutputNeurons();
		ActivationFunctionNeuron output = nn.getMoveForwardNeuron();
		output = nn.getTurnClockwiseNeuron();
		output = nn.getTurnCounterclockwiseNeuron();
	}
	
	
	@Test
	void checkChromosome() {
		int[] weights = new int[Constants.CHROM_SIZE];
		Arrays.fill(weights, Constants.GENE_MIN);
		Chromosome c1 = new Chromosome(weights);
		Chromosome c2 = Chromosome.createRandom();
		Chromosome c3 = c1.crossover2(c2);
		Chromosome c4 = c1.mutate(0, 0);
		Chromosome c5 = c1.randomlyMutate();

		{
			int x = c1.getGene(0);
			boolean b = Chromosome.isValidGene(0);
		}
		
		{
			boolean b = c1.matchesFrom(c2, 0);
		}
		{
			boolean b = c1.matchesUntil(c2, 0);
		}
		{
			boolean b = c1.onlyDiffersAt(c2, 0);
		}
	}
	
	@Test
	public void mkChromosomes() {
		Chromosome[] cs = Chromosome.createRandom(10);
	}
	
	@Test
	public void checkCreature()
	{
		Behavior behavior = new ImmobileBehavior(Chromosome.createRandom());
		Point position = new Point(0, 0);
		Orientation orientation = Orientation.northWest();
		Creature creature = new Creature(behavior, position, orientation);
		
		{
			Point p = creature.getPosition();
		}
		{
			Orientation o = creature.getOrientation();
		}
		{
			Chromosome c = creature.getChromosome();
		}
		{
			Behavior b = creature.getBehavior();
		}
		creature.turnClockwise();
		creature.turnCounterclockwise();
		{
			boolean b = creature.isEqual(creature);
		}
		{
			Creature c = creature.giveCopy();
		}
	}
	
	@Test
	public void checkCreature2()
	{
		World world = createWorld();
		Creature creature = world.getPopulation()[0];
		
		{
			Point p = creature.destination(new Vector(0, 0));
		}
		{
			creature.moveForward(world, new Vector(0, 0));
		}
		{
			creature.performAction(world);
		}
	}
	
	@Test
	public void naturalSelectionHierarchy()
	{
		NaturalSelection ns1 = new BorderHabitableZone(1);
		NaturalSelection ns2 = new CircularHabitableZone(new Point(0, 0), 1);
		NaturalSelection ns3 = new SouthEastHabitableZone();
		NaturalSelection ns4 = new Disjunction(ns1,  ns2);
	}
	
	@Test
	public void checkNaturalSelection()
	{
		var world = createWorld();
		NaturalSelection ns = new SouthEastHabitableZone();
		boolean b = ns.survives(world, new Point(0, 0));
	}
	
	@Test
	public void checkSimulation()
	{
		Simulation simulation = new Simulation(100, 10, new SouthEastHabitableZone());
		
		{
			NaturalSelection ns = simulation.getNaturalSelection();
		}
		{
			int x = simulation.getPopulationSize();
		}
		{
			World w = simulation.getWorld();
		}
		simulation.nextGeneration();
	}
	
	@Test
	public void createRandWorldWith()
	{
		Behavior[] behaviors = new Behavior[] { new ImmobileBehavior(Chromosome.createRandom()) };
		World w = Simulation.createRandWorldWith(10, 1, behaviors);
	}
	
	@Test
	public void createInitWorldNeuralnets()
	{
		World w = Simulation.createInitWorldNeuralnets(10, 10);
	}
	
	@Test
	public void checkWorld()
	{
		World world = createWorld();
		
		{
			int x = world.getWidth();
		}
		{
			int y = world.getHeight();
		}
		{
			boolean b = world.isInside(new Point(0, 0));
		}
		{
			boolean b = world.isLimPos(new Point(0, 0));
		}
		{
			boolean b = world.isFree(new Point(0, 0));
		}
		{
			Creature[] creatures = world.getPopulation();
			boolean b = World.areEqualCreatureArrays(creatures, creatures);
		}
		world.step();
	}
	
	@Test
	public void checkColor()
	{
		Color c = new Color(0, 0, 0);
		{
			int x = c.asInteger();
		}
		{
			boolean b = Color.isValidColorComponent(0);
		}
	}
	
	@Test
	public void checkPair()
	{
		Pair<String, Integer> pair = new Pair<String, Integer>("a", 5);
		Pair<Integer, String> pair2 = new Pair<>(8, "aaa");
		
		{
			String s = pair.getFirst();
		}
		{
			Integer x = pair.getSecond();
		}
		{
			String s = "aa";
			pair.setFirst(s);
		}
		{
			Integer x = 1;
			pair.setSecond(x);
		}
	}
	
	@Test
	public void checkPoint()
	{
		Point point = Point.createRandom(100, 100);
		
		{
			int x = point.getX();
		}
		{
			int y = point.getY();
		}
		{
			Point p = point.move(new Vector(0, 0));
		}
		{
			int x = point.distanceSquared(point);
		}
		{
			boolean b = Point.isWithin(point, 10, 10);
		}
	}
	
	@Test
	public void checkVector()
	{
		Vector vector = new Vector(1, 2);
		
		{
			int x = vector.getX();
		}
		{
			int y = vector.getY();
		}
		{
			Vector v = vector.scaleWith(1);
		}
		{
			Vector v = vector.plus(vector);
		}
	}
}
