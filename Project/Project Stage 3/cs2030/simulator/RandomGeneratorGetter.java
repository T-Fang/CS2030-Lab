package cs2030.simulator;

/**
 * The RandomGeneratorManager is used to retrieve methods in RandomGenerator
 */
public class RandomGeneratorGetter {
    public static RandomGenerator randomGenerator;

    /**
     * @param seed          The random seed.
     * @param lambda        The arrival rate.
     * @param mu            The service rate.
     * @param rho           The resting rate.
     */
    public static void instantiate(int seed, double lambda, double mu,
            double rho) {
        randomGenerator = new RandomGenerator(seed, lambda, mu, rho);
    }

    /**
     * Return a random inter-arrival time.
     * 
     * @return a random inter-arrival time.
     */
    public static double genInterArrivalTime() {
        return randomGenerator.genInterArrivalTime();
    }

    /**
     * Return a random service time.
     *
     * @return a random service time.
     */
    public static double genServiceTime() {
        return randomGenerator.genServiceTime();
    }

    /**
     * Return a random number between 0 and 1 to compare to a server's probability of taking a rest.
     * 
     * @return a random number between 0 and 1 to compare to a server's probability of taking a rest
     */
    public static double genRandomRest() {
        return randomGenerator.genRandomRest();
    }

    /**
     * Return a random resting period for server.
     * 
     * @return a random resting period for server.
     */
    public static double genRestPeriod() {
        return randomGenerator.genRestPeriod();
    }

    /**
     * Return a random number between 0 and 1 to determine the type of customer.
     * 
     * @return a random number between 0 and 1 to determine the type of customer.
     */
    public static double genCustomerType() {
        return randomGenerator.genCustomerType();
    }
}
