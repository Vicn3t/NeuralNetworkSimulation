package sim.neuralnet;


public class HorizontalOrientationSensorNeuron  extends OrientationSensorNeuron
{
	/**
	 * changement de la valeur en raccord avec le graphe sur horizontal
	 */
    @Override
    /**
     * toutes les méthodes retournaient 0
     * @post | result == 0
     */
    public int north()
    {
        return 0;
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
	 * @post | result == 1000
	 */
    public int east()
    {
        return 1000;
    }

    @Override
        /**
         * @post | result == 500
         */
    public int southEast()
    {
        return 500;
    }

    @Override
        /**
         * @post | result == 0
         */
    public int south()
    {
        return 0;
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
         * @post | result == -1000
         */
    public int west()
    {
        return -1000;
    }

    @Override
	/**
	 * @post | result == -500
	 */
    public int northWest()
    {
        return -500;
    }
}
