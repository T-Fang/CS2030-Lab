package cs2030.simulator;

/**
 * The Statistics class stores statistics about the simulation, which includes:
 * 1. the average waiting time
 * 2. the number of customer who left
 * 3. the number of customers who are served
 */
class Statistics {
    /**
     * customers' total waiting time.
     */
    private final double totalWaitingTime;

    /**
     * Total number of served customers.
     */
    private final int totalNumOfServedCustomer;

    /**
     * Total number of customers who left without being served.
     */
    private final int totalNumOfLostCustomer;

    /**
     * Constructor for Statistics
     */
    public Statistics() {
        this.totalWaitingTime = 0;
        this.totalNumOfServedCustomer = 0;
        this.totalNumOfLostCustomer = 0;
    }

    /**
     * Constructor for a new Statistics object.
     *
     * @param totalWaitingTime         the total waiting time so far.
     * @param totalNumOfServedCustomer the number of customers served so far.
     * @param totalNumOfLostCustomer   the number of customers left so far.
     */
    private Statistics(double totalWaitingTime, int totalNumOfServedCustomer, int totalNumOfLostCustomer) {
        this.totalWaitingTime = totalWaitingTime;
        this.totalNumOfServedCustomer = totalNumOfServedCustomer;
        this.totalNumOfLostCustomer = totalNumOfLostCustomer;
    }

    /**
     * Adds 1 to the total number of served customer
     *
     * @return A new Statistics object with updated statistics
     */
    public Statistics serveOneCustomer() {
        return new Statistics(
                this.totalWaitingTime,
                this.totalNumOfServedCustomer + 1,
                this.totalNumOfLostCustomer
        );
    }

    /**
     * Adds 1 to the total number of lost customer
     *
     * @return A new Statistics object with updated statistics
     */
    public Statistics looseOneCustomer() {
        return new Statistics(
                this.totalWaitingTime,
                this.totalNumOfServedCustomer,
                this.totalNumOfLostCustomer + 1
        );
    }

    /**
     * Adds the waiting time of a customer to the total waiting time
     *
     * @param time The time a customer waited.
     * @return A new Statistics object with updated stats
     */
    public Statistics recordWaitingTime(double time) {
        return new Statistics(
                this.totalWaitingTime + time,
                this.totalNumOfServedCustomer,
                this.totalNumOfLostCustomer
        );
    }

    /**
     * Return a string representation of the statistics collected.
     *
     * @return a string indicating three types of statistics:
     * 1. the average waiting time
     * 2. the number of served customer
     * 3. the number of lost customer
     */
    public String toString() {
        return String.format("[%.3f %d %d]",
                totalNumOfServedCustomer == 0 ? 0 : totalWaitingTime / totalNumOfServedCustomer,
                totalNumOfServedCustomer, totalNumOfLostCustomer);
			}
}
