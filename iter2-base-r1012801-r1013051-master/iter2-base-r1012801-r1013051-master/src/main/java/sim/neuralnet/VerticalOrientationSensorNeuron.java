package sim.neuralnet;

/**
 * @immutable
 */
public class VerticalOrientationSensorNeuron extends OrientationSensorNeuron
{
    @Override
    /**
     * toutes les méthodes retournaient 0
     * @post | result == 1000
     */
    public int north()
    {
        return 1000;
    }

    @Override
	/**	 
	 * @post | result == 500
	 */
    public int northEast()
    {
        return 500;
    }

    @Override
        /**
         * @post | result == 0
         */
    public int east()
    {
        return 0;
    }

    @Override
	/**
	 * @post | result == -500
	 */
    public int southEast()
    {
        return -500;
    }

    @Override
	/**
	 * @post | result == -1000
	 */
    public int south()
    {
        return -1000;
    }

    @Override
        /**
         * @post | result == -500
         */
    public int southWest()
    {
        return -500;
    }

    @Override
	/**
	 * @post | result == 0
	 */
    public int west()
    {
        return 0;
    }

    @Override
        /**
         * @post | result == 500
         */
    public int northWest()
    {
        return 500;
    }
}
