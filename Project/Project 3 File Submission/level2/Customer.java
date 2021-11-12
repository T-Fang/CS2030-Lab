package cs2030.simulator;

/**
 * The Customer class encapsulates information and methods associated with a
 * Customer in the simulation. A customer has an unique id and an arrival time.
 */
public class Customer {
    /**
     * The unique ID of this customer.
     */
    private final int id;

    /**
     * The arrival time of this customer .
     */
    private double ArrivalTime;

    /**
     * Constructs a new customer.
     *
     * @param ArrivalTime       The arrival time of this customer in the simulation.
     * @param id                The unique ID of this customer
     */
    public Customer(double ArrivalTime, int id) {
        this.ArrivalTime = ArrivalTime;
        this.id = id;
    }

    /**
     * Return whether this customer is greedy
     * @return true if the customer is greedy;
     *          false otherwise.
     */

    public boolean isGreedy() {
        return false;
    }

    /**
     * Return the waiting time of this customer.
     *
     * @param t a given time to be compared with the arrival time
     * @return The waiting time of this customer.
     */
    public double timeWaited(double t) {
        return t - ArrivalTime;
    }

    /**
     * Return a string representation of this customer.
     *
     * @return The id of the customer
     */
    public String toString() {
        return Integer.toString(this.id);
    }

    /**
     * Return the customer's id
     *
     * @return the customer's id
     */
    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Customer) {
            Customer c = (Customer) obj;
            return c.id == this.id;
        }
        return false;
    }

}
