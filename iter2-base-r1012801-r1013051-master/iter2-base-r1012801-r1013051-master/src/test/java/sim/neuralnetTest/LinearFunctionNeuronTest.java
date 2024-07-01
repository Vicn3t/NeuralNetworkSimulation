package sim.neuralnetTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sim.neuralnet.LinearFunctionNeuron;
import sim.neuralnet.VerticalOrientationSensorNeuron;

class LinearFunctionNeuronTest {

	public LinearFunctionNeuron linear;
	
	@BeforeEach
	public void setup() {
        linear = new LinearFunctionNeuron();
        
    }
	
	@Test
	public void applyActivationFunction() {
		assertEquals(1, linear.applyActivationFunction(1));
		assertEquals(1000, linear.applyActivationFunction(1000));
		assertEquals(-1000, linear.applyActivationFunction(-1000));
	}
	
	@Test
	public void doublesensor() {
		assertEquals(0, linear.getBias());
		linear.connect(new VerticalOrientationSensorNeuron(), 1);
		assertEquals(1,linear.getDependencies().size());
		
		linear.doubleSensor(0);
		assertEquals(2,linear.getDependencies().size());
		assertEquals(1,linear.getDependencies().get(0).getSecond());
		assertEquals(1,linear.getDependencies().get(1).getSecond());
		assertEquals(0, linear.getBias());
		assertTrue(linear.getDependencies().get(0).isEqual(linear.getDependencies().get(1)));
	}
	}


