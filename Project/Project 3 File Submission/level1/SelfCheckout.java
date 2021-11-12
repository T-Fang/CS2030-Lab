package cs2030.simulator;

import java.util.LinkedList;
import java.util.Optional;

/**
 * The SelfCheckout class encapsulates information and methods associated with a
 * self-checkout counter in the simulation.
 * Unlike normal server, it does not have to rest.
 * All self-checkout counter has a shared waiting queue.
 */
public class SelfCheckout extends Server{

    /**
     * The waiting queue shared among all self-checkout counters.
     */
    private static LinkedList<Customer> SelfCheckQueue = new LinkedList<>();

    /**
     * Creates a self-checkout counter and initializes it with a unique id.
     *
     * @param id the unique id of this self-checkout counter
     */
    public SelfCheckout(int id) {
        super(id);
    }

    /**
     * A private constructor for SelfCheckout
     * @param id                the unique id of this self-checkout counter
     * @param servingCustomer   the customer the counter is currently serving
     */
    private SelfCheckout(int id, Optional<Customer> servingCustomer) {
        super(id, false, servingCustomer, new LinkedList<>());
    }

    /**
     * Ask if this server(self-checkout counter) needs to rest, which is obvious false.
     * @return  false as this is a self-checkout counter
     */
    @Override
    public boolean restLess() {
        return true;
    }

    /**
     * Makes this self-checkout counter idle by removing its current customer.
     *
     * @return A new self-checkout counter with the current customer removed.
     */
    @Override
    public SelfCheckout becomeIdle() {
        return new SelfCheckout(id, Optional.empty());
    }

    /**
     * Retrieves the next customer from the shared queue (if anuy)
     * @return the next customer from the shared queue (if anuy)
     */
    @Override
    public Optional<Customer> getNextWaitingCustomer() {
        return Optional.ofNullable(SelfCheckout.SelfCheckQueue.poll());
    }

    /**
     * Gets the length of the shared queue
     * @return  the length of the shared queue
     */
    @Override
    public int getLenOfQueue() {
        return SelfCheckout.SelfCheckQueue.size();
    }

    /**
     * Serves the customer
     * @param customer The customer to be served.
     * @return  the updated self-checkout counter
     */
    @Override
    public SelfCheckout serve(Customer customer) {
        if (SelfCheckout.SelfCheckQueue.stream().anyMatch(c -> c.equals(customer))) {
            SelfCheckout.SelfCheckQueue.remove(customer);
        }
        return new SelfCheckout(id, Optional.of(customer));
    }

    /**
     * Makes a customer wait for self-checkout by adding the customer to the shared waiting queue.
     * @param customer The customer waiting to be served by self-checkout counter.
     * @return  this self-checkout counter
     */
    @Override
    public SelfCheckout askToWait(Customer customer) {
        SelfCheckout.SelfCheckQueue.add(customer);
        return this;
    }

    /**
     * Return a string representation of this self-checkout counter.
     *
     * @return The id of this self-checkout counter along with a prefix of "self-check "
     */
    @Override
    public String toString() {
        return "self-check " + this.id;
    }
}
