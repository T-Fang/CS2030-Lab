package cs2030.simulator;

import java.util.LinkedList;
import java.util.Optional;

/**
 * The Server class encapsulates information and methods associated with a
 * Server in the simulation.
 * A server has a unique id and keeps track of the customer being served (if any)
 * and a queue of customers waiting to be served (if any).
 *
 */
public class Server {

    /**
     * The unique ID of this server.
     */
    protected final int id;

    /**
     * a boolean indicating whether the server is resting
     */
    private boolean resting;

    /**
     * The customer currently being served, if any.
     */
    private Optional<Customer> servingCustomer;

    /**
     * The queue of customers waiting to be served by this server, if any.
     */
    private LinkedList<Customer> waitingCustomers;

    /**
     * Creates a server and initializes it with a unique id.
     *
     * @param  id the id of this server
     */
    public Server(int id) {
        this.id = id;
        this.resting = false;
        this.servingCustomer = Optional.empty();
        this.waitingCustomers = new LinkedList<>();
    }

    /**
     * Private constructor for a server.
     *
     * @param id                server's unique id
     * @param resting           whether the server is resting
     * @param servingCustomer   customer that is currently being served by this server (if any)
     * @param waitingCustomers  a queue of customers that are waiting to be served by this server (if any)
     */
    protected Server(int id, boolean resting, Optional<Customer> servingCustomer,
                   LinkedList<Customer> waitingCustomers) {
        this.id = id;
        this.resting = resting;
        this.servingCustomer = servingCustomer;
        this.waitingCustomers = waitingCustomers;
    }


    /**
     * Helps to identify whether this server is a self-check counter
     *
     * @return false by default (this a regular server that needs to rest)
     */
    public boolean restLess(){
        return false;
    }


    /**
     * Makes this server idle by removing its current customer.
     *
     * @return A new server with the current customer removed.
     */
    public Server becomeIdle() {
        return new Server(id, resting, Optional.empty(), waitingCustomers);
    }

    /**
     * Asks whether the current server is idle.
     *
     * @return true if the server is idle (no current customer);
     *          false otherwise.
     */
    public boolean isIdle() {
        return !this.servingCustomer.isPresent() && !resting;
    }

    /**
     * Changes this server's state to resting
     * @return a new server who is resting
     */
    public Server rest() {
        return new Server(id, true, servingCustomer, waitingCustomers);
    }

    /**
     * Makes this server come back to work
     * @return a new server who has come back to work
     */
    public Server back() {
        return new Server(id, false, servingCustomer, waitingCustomers);
    }

    /**
     * Find the next waiting customers to be served by this server (if any)
     *
     * @return Optional.empty if there is no waiting customer, or the
     *          optional of the next waiting customer
     */
    public Optional<Customer> getNextWaitingCustomer() {
        return Optional.ofNullable(waitingCustomers.poll());
    }

    /**
     * Return the length of the waiting queue
     * @return the length of the waiting queue
     */
    public int getLenOfQueue(){
        return waitingCustomers.size();
    }

    /**
     * Serves a customer.
     *
     * @param customer The customer to be served.
     * @return The new server serving this customer.
     */
    public Server serve(Customer customer) {
        if (waitingCustomers.stream().noneMatch(c -> c.equals(customer))) {
            return new Server(id, resting, Optional.of(customer), waitingCustomers);
        } else {
            LinkedList<Customer> newQueue = waitingCustomers;
            newQueue.remove(customer);
            return new Server(id, resting, Optional.of(customer), newQueue);
        }
    }

    /**
     * Makes a customer wait for this server by adding the customer to the waiting queue.
     *
     * @param customer  The customer waiting to be served this server.
     * @return          The new server with updated waiting queue.
     */
    public Server askToWait(Customer customer) {
        LinkedList<Customer> newQueue = waitingCustomers;
        newQueue.add(customer);
        return new Server(id, resting, servingCustomer, newQueue);
    }

    /**
     * Return a string representation of this server.
     *
     * @return The id of the server along with a prefix of "server "
     */
    @Override
    public String toString() {
        return "server " + this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Server)) {
            return false;
        }
        return (this.id == ((Server) obj).id);
    }

}
