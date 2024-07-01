package simTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.function.BooleanSupplier;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import sim.Chromosome;
import sim.Constants;

class ChromosomeTest {

	@Test
	public void ChromosomeConstructorIllegalArguments() {
		assertThrows(IllegalArgumentException.class, () -> new Chromosome(null));
		assertThrows(IllegalArgumentException.class, () -> new Chromosome(new int[Constants.CHROM_SIZE + 1]));
		assertThrows(IllegalArgumentException.class, () -> new Chromosome(
				new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, Constants.GENE_MAX + 1, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 }));
		assertThrows(IllegalArgumentException.class, () -> new Chromosome(
				new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, Constants.GENE_MIN -1, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 }));
		
	}
	
	@Test
	public void createRandom() {
		var chromosome1 = Chromosome.createRandom();
		assertTrue(Chromosome.isValidGene(chromosome1.getGene(0)));
	}
	
	@Test 
	public void createRandomArray() {
    	var chromosome1 = Chromosome.createRandom(2);
    	assertEquals(2, chromosome1.length);
    	assertTrue(Arrays.stream(chromosome1).allMatch(c -> c != null));
    	chromosome1[0]=null;
    	assertFalse(Arrays.stream(chromosome1).allMatch(c -> c != null));
    	
    }
	
	@Test
	public void isValidGene()
	{
		var chromosome1 = new Chromosome(
				new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
		assertTrue(Chromosome.isValidGene(chromosome1.getGene(0)));
	}
	
	@Test
	public void isEqualOnSameChromosome()
	{
		var chromosome = new Chromosome(
				new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
		
		assertTrue(chromosome.isEqual(chromosome));
	}

	@Test
	public void isEqualOnEqualChromosomes()
	{
		var chromosome1 = new Chromosome(
				new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
		var chromosome2 = new Chromosome(
				new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
		
		assertTrue(chromosome1.isEqual(chromosome2));
		assertTrue(chromosome2.isEqual(chromosome1));
	}

	@Test
	public void isEqualOnChromosomesWithDifferentGenes()
	{
		var chromosome1 = new Chromosome(
				new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
		var chromosome2 = new Chromosome(
				new int[] { 1, 2, 5, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
		
		assertFalse(chromosome1.isEqual(chromosome2));
		assertFalse(chromosome2.isEqual(chromosome1));
	}

	@Test
	public void getters()
	{var chromosome1 = new Chromosome(
			new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
	assertEquals(1, chromosome1.getGene(0));
    }
		

	
	
	@Test
	public void matches()
	{
		var chromosome1 = new Chromosome(
				new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
		var chromosome2 = new Chromosome(
				new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
		var chromosome3 = new Chromosome(
				new int[] { 1, 2, 5, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
		
		assertTrue(chromosome1.matchesUntil(chromosome2,12));
		assertTrue(chromosome2.matchesFrom(chromosome1,12));
		assertFalse(chromosome1.matchesUntil(chromosome3,12));
		assertFalse(chromosome1.matchesFrom(chromosome3,1));
	}	
	
	@Test
	public void mutation()
	{
		var chromosome1 = new Chromosome(
				new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
		var chromosome2 = chromosome1.mutate(1,2);
		assertFalse(chromosome1.isEqual(chromosome2));
		assertTrue(Chromosome.isValidGene(chromosome2.getGene(1)));
		assertEquals(4, chromosome2.getGene(1));
		assertFalse(chromosome1.isEqual(chromosome2));
		assertTrue(chromosome1.onlyDiffersAt(chromosome2,1));
		
		var chromosome3 = chromosome1.mutate(1,1010);
		assertTrue(chromosome1.isEqual(chromosome3));
		
		Chromosome chrom = chromosome1.mutate(1, -1010);
        assertTrue(chromosome1.isEqual(chrom));
        
        
		assertTrue(chromosome1.isEqual(chromosome1.mutate(1,Constants.GENE_MAX + 1)));
		assertTrue(chromosome1.isEqual(chromosome1.mutate(1,-Constants.GENE_MIN -1)));

		System.out.print(chromosome1.mutate(1,Constants.GENE_MAX).getGene(1));
	}
	
	 @Test 
		public void randomlyMutate() {
	    	var chromosome1 = Chromosome.createRandom();
	    	var chromosome2 = chromosome1.randomlyMutate();
	    	assertTrue(IntStream.range(0, Constants.CHROM_SIZE).anyMatch(i -> chromosome1.onlyDiffersAt(chromosome2, i)));
	    	
		
	}
    @Test 
	public void crossover2() {
		Chromosome c1 = new Chromosome(new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
		Chromosome c2 = new Chromosome(new int[] { 2, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
		Chromosome offspring = c1.crossover2(c2);
	}
    
	@Test
	public void immutability()
	{
		var chromosome1 = new Chromosome(
				new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 });
		chromosome1.mutate(0, 10);
		assertTrue(Chromosome.isValidGene(11));
		assertEquals(1, chromosome1.getGene(0));
		var chromosome2 = chromosome1.mutate(0,10);
		assertEquals(11, chromosome2.getGene(0));
		assertFalse(chromosome1.isEqual(chromosome2));
	}

	}

