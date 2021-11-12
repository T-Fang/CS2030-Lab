package cs2030.simulator;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Optional;

/**
 * The Shop class encapsulates information and methods associated with the
 * shop in the simulation.
 * A shop keeps track of the list of servers and support queries for server.
 */
class Shop {
    /**
     * List of servers in the shop.
     */
    private final List<Server> servers;

    /**
     * the maximum queue length
     */
    private int maxQueueLen;

    /**
     * Create a new shop with a given number of servers.
     *
     * @param numOfServers The number of servers.
     * @param maxQueueLen the maximum queue length
     */
    public Shop(int numOfServers, int numOfSelfCheck, int maxQueueLen) {
        this.servers = Stream.iterate(1, i -> i + 1)
                .map(i -> i <= numOfServers ? new Server(i) : new SelfCheckout(i))
                .limit(numOfServers + numOfSelfCheck)
                .collect(Collectors.toList());
        this.maxQueueLen = maxQueueLen;
    }

    /**
     * A private constructor to update the shop.
     * @param servers       list of servers
     * @param maxQueueLen   the maximum queue length
     */
    private Shop(List<Server> servers, int maxQueueLen) {
        this.servers = servers;
        this.maxQueueLen = maxQueueLen;
    }

    /**
     * Finds a server for the given customer if there is no idle server
     *
     * @param customer the customer to be to served
     * @return Optional.empty if no server can serve the customer, or the
     * optional of the server if there is one server
     */
    public Optional<Server> findFor(Customer customer) {

        Predicate<Server> predicate = server -> server.getLenOfQueue() < maxQueueLen;
        if(customer.isGreedy()){
            int min = servers.stream()
                    .filter(predicate)
                    .mapToInt(server -> server.getLenOfQueue())
                    .min()
                    .orElse(0);
            predicate = server -> server.getLenOfQueue() == min;
        }
        return find(predicate);
    }

    /**
     * Finds the first server that matches the predicate
     *
     * @param predicate the given predicate to match the server with
     * @return the first server that matches the predicate
     */
    public Optional<Server> find(Predicate<Server> predicate) {
        return this.servers.stream()
                .filter(predicate)
                .findFirst();
    }

    /**
     * Returns a new shop with the updated state of the server.
     *
     * @param server    the updated server
     * @return a new shop with the updated state of the server.
     */

    public Shop replace(Server server) {
        return new Shop(
                servers.stream()
                        .map(s -> (s.equals(server) ? server : s))
                        .collect(Collectors.toList()),
                maxQueueLen
        );
    }

    /**
     * Return a string representation of this shop.
     *
     * @return a string representation of this shop.
     */
    public String toString() {
        return servers.toString();
    }
}
