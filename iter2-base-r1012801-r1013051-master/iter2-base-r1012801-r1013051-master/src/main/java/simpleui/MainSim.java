package simpleui;

import sim.Constants;
import sim.Simulation;
import sim.naturalselection.CircularHabitableZone;
// import sim.naturalselection.Disjunction;
import sim.naturalselection.NaturalSelection;
import sim.naturalselection.BorderHabitableZone;
import util.Point;
import util.RandomUtil;


/**
 * LEGIT
 * 
 * 
 * Make sure to test your program with different parameters.
 * Don't forget to restore the present class before submitting.
 * 
 * See *Info about other classes and general remarks.* in the assignment.
 */
public class MainSim
{
	public static NaturalSelection eastCircle() {
		return new CircularHabitableZone(new Point(Constants.WSIZE / 2,Constants.WSIZE/2),Constants.WSIZE/5);
	}
	
	/**
	 * à suppr plus tard
	 */
	public static NaturalSelection BorderhabitableZone() {
		return new BorderHabitableZone(5);
	}
    public static void main(String[] args)
    {

    	RandomUtil.seed(1234);
    	
    	NaturalSelection nsel = eastCircle();
    			
    	
    	Simulation sim = new Simulation(
    			Constants.WSIZE,
    			Constants.POPU_SIZE,
    			nsel);
    	
    	SimLifeWindow.create("Iteration 2", Constants.WSIZE, Constants.WSIZE, sim);
    }
    
}
