package cs2030.simulator;

/**
 * The GreedyCustomer class encapsulates information and methods associated with a
 * greedy customer in the simulation.
 * A greedy customer has an unique id and an arrival time.
 * A greedy customer will join the queue with the shortest length if there is no idle server
 */
public class GreedyCustomer extends Customer {
    /**
     * Create and initialize a new customer. (Constructor for Customer)
     *
     * @param ArrivalTime The arrival time of this greedy customer in the simulation.
     * @param id          The unique ID of this greedy customer
     */
    public GreedyCustomer(double ArrivalTime, int id) {
        super(ArrivalTime, id);
    }

    @Override
    public boolean isGreedy() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "(greedy)";
    }
}
