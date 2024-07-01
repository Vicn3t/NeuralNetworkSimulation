package sim.neuralnetTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sim.neuralnet.HorizontalOrientationSensorNeuron;

class HorizontalOrientationSensorNeuronTest {

	private HorizontalOrientationSensorNeuron weight;

	@BeforeEach
	public void setup() {
		weight = new HorizontalOrientationSensorNeuron();
	}
	
	@Test
	public void orientation() 
	{
		assertEquals(0, weight.north());
		assertEquals(0, weight.south());
		assertEquals(1000, weight.east());
		assertEquals(-1000, weight.west());
		assertEquals(500, weight.northEast());
		assertEquals(-500, weight.northWest());
		assertEquals(500, weight.southEast());
		assertEquals(-500, weight.southWest());
		}
}