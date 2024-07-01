package sim.neuralnetTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sim.neuralnet.VerticalOrientationSensorNeuron;

class VerticalOrientationSensorNeuronTest {

	public VerticalOrientationSensorNeuron weight;

	@BeforeEach
	public void setup() {
		weight = new VerticalOrientationSensorNeuron();
	}
	
	@Test
	public void orientation()
	{
		assertEquals(1000, weight.north());
		assertEquals(-1000, weight.south());
		assertEquals(0, weight.east());
		assertEquals(0, weight.west());
		assertEquals(500, weight.northEast());
		assertEquals(500, weight.northWest());
		assertEquals(-500, weight.southEast());
		assertEquals(-500, weight.southWest());
	}

}
