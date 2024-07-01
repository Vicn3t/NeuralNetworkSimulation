package sim;


import java.util.ArrayList;
import java.util.Arrays;
import sim.behaviors.Behavior;
import sim.behaviors.NeuralNetworkBehavior;
import sim.naturalselection.NaturalSelection;
import util.Orientation;
import util.Point;
import util.RandomUtil;

public class Simulation
{

	private final NaturalSelection nsel;
	
	/**
	 * not a representation object for performance
	 * @invar | world != null
	 */
	private World world;
	
	/**
	 * @invar | populationSize > 0
	 */
    private final int populationSize;
    
    /**
     * @invall | nsel != null
     */
    public NaturalSelection getNaturalSelection() {
    	return nsel;
    }
    /**
     * @post | result > 0
     */
	public int getPopulationSize() {
		return populationSize;
	}

	/**
	 * LEGIT
	 * @post | result != null
	 * @post | result.getPopulation().length == getPopulationSize()
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * LEGIT
	 * 
	 * You may change this function to experiment *but*
	 * remember to restore this function upon submitting.
	 * 
	 */
    public Simulation(int size, int populationSize, NaturalSelection nsel) {
    	this.populationSize = populationSize;
    	this.world = createInitWorldNeuralnets(size, populationSize);
    	this.nsel = nsel;
    }
    
    /**
     * LEGIT
     * 
     * Returns a square World of side `size` with popuSize creatures.
     * The creatures have Chromosomes as in `chroms` and behaviors as in `behaviors`.
     * Moreover all creatures have random positions (anywhere within the world) and orientations.
     * 
     * @param size is the length of the side of the returned world
     * 
     * @pre | size > 0
     * @pre | popuSize > 0
     * 
     * @pre | behaviors != null && behaviors.length == popuSize
     * @pre | Arrays.stream(behaviors).allMatch(b -> b != null)
     * 
     * @creates | result
     * @post | result != null
     * @post | result.getPopulation().length == popuSize
     * // post | IntStream.range(0, numA).allMatch(i -> result.getPopulationA()[i].getChromosome().isEqual(chromsA[i]))
     * // post | Arrays.stream(result.getPopulation()).allMatch(c -> Point.isWithin(c.getPosition().move(new Vector(-size/4, -size/4)), size/2, size/2))
     */
    public static World createRandWorldWith(int size, int popuSize, Behavior[] behaviors) {
        Creature[] pop = new Creature[popuSize];
        
        for (int i = 0 ; i < popuSize ; i ++) {
          Point position = Point.createRandom(size,size);
          //Point position = Point.createRandom(size/2, size/2).move(new Vector(size/4, size/4));
      	  Orientation orient = Orientation.createRandom();
      	  var behavior = behaviors[i];
      	  pop[i] = new Creature(behavior, position, orient);
        }
               
        return new World(size, size, pop);
    }
    

    	
    /**
     * LEGIT
     */
    public static World createInitWorldNeuralnets(int size, int popuSize) {
    	Behavior[] behaviors = new Behavior[popuSize];
    	for (int i = 0 ; i < popuSize ; i++) {
    		behaviors[i] = new NeuralNetworkBehavior(Chromosome.createRandom());
    	}
    	return createRandWorldWith(size, popuSize, behaviors);
    }

    /**
     * méthode vide
     * Replaces the current world with a new one.
     * - If no creature survived (see private method) we reset world with createInitWorldNeuralnets. Else:
     * - We gather surviving creatures in a list `surv`.
     * - We compute the offpsring chromosomes (see private method) based on that list.
     *   Note that there should be `populationSize` chromosomes.
     * - Each new behavior is then obtained from an offspring chromosome by using
     *   Behavior.copyWithChromosome. To determine which kind of behavior to use, we cycle through `surv`.
     * - Finally the world is reset with the latter offspring behaviors using
     *   createRandWorldWith method.
     *  
     * @inspects | getWorld()
     * @creates | getWorld()
     * @mutates | this
     * 
     */
    public void nextGeneration() {
        ArrayList<Creature> survivors = survivingCreatures();

        if (survivors.isEmpty()) {
            world = createInitWorldNeuralnets(world.getWidth(), populationSize);
            return;
        }

        ArrayList<Chromosome> survivorChromosomes = new ArrayList<>();
        
        for (Creature creature : survivors) {
            survivorChromosomes.add(creature.getBehavior().getChromosome());
        }

        Chromosome[] offspringChromosomes = computeOffspringWithSize(survivorChromosomes, populationSize);

        Behavior[] offspringBehaviors = new Behavior[populationSize];
        for (int i = 0; i < populationSize; i++) {
            Behavior parentBehavior = survivors.get(i % survivors.size()).getBehavior();
            offspringBehaviors[i] = parentBehavior.copyWithChromosome(offspringChromosomes[i]);
        }

        world = createRandWorldWith(world.getWidth(), populationSize, offspringBehaviors);
    }
    	

    /** méthode vide
     * 
     * 
     */
    private ArrayList<Creature> survivingCreatures(){
	    Creature[] allCreatures = this.world.getPopulation();
	    
	    ArrayList<Creature> survivors = new ArrayList<>();
	    
	    for (Creature creature : allCreatures) {
	        if (nsel.survives(this.world, creature.getPosition())) {
	            survivors.add(creature);
	        }
	    }
	    return survivors;
	}
    
    /**
     * LEGIT
     * 
     * @pre | parentGeneration != null && parentGeneration.size() > 0
     * @post | result != null && result.length == ofSize 
     */
    private Chromosome[] computeOffspringWithSize(ArrayList<Chromosome> parentGeneration, int ofSize) {

        var offspringChromosomes = new Chromosome[ofSize];

        for ( int i = 0; i != ofSize; ++i )
        {
            var parent1 = RandomUtil.pick(parentGeneration);
            var parent2 = RandomUtil.pick(parentGeneration);

            var offspring = parent1.crossover2(parent2);

            if ( RandomUtil.integer(100) < Constants.MUT_RATE )
            {
                offspring = offspring.randomlyMutate();
            }

            offspringChromosomes[i] = offspring;
        }

        return offspringChromosomes;  
    }
    

    
 
}
