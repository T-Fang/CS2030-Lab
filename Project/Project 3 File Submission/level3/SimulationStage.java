package cs2030.simulator;

import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.Stream;
import java.util.function.Function;

/**
 * This class encapsulates the simulation stages.
 * Each stage has a event queue, statistics, the shop, the event log, and other parameters
 * related to generating different kinds of events
 */
public class SimulationStage {

    /**
     * The Event class encapsulates information and methods associated with an event in the simulation.
     * There are four types of events associated with a customer's ARRIVAL and DONE, and a server's REST and BACK
     * the event has a transformation function that can push forward the simulation progress
     * It also keeps track of the id of the associated customer to facilitate comparing of two events.
     */
    private class Event implements Comparable<Event> {
        /**
         * The time this event happens.
         */
        private double time;
        /**
         * The id of the associated customer.
         */
        private int customerId;

        /**
         * A transformation function that this event will execute, which will change the Simulation Stage.
         */
        private Function<SimulationStage, SimulationStage> transform;

        /**
         * Creates an event and initializes it.
         *
         * @param time          The time of occurrence.
         * @param customerId    The id of the associated customer.
         * @param transform         The function that this event will execute.
         */
        public Event(double time, int customerId, Function<SimulationStage, SimulationStage> transform) {
            this.time = time;
            this.customerId = customerId;
            this.transform = transform;
        }

        /**
         * Defines the natural ordering of events.
         * Events are ordered in ascending order of their timestamps.
         * if two events have the same timestamps, they will be compared using their associated customer's id
         *
         * @param other Another event to compare against.
         * @return 0 if two events have the same timestamp and customerId , a positive number if
         * this event has later than other event, a negative number otherwise.
         */
        public int compareTo(Event other) {
            int result = (int) Math.signum(this.time - other.time);
            if(result == 0) {
              return (int) Math.signum(this.customerId - other.customerId);
            } else {
              return result;
            }
        }

        /**
         * Simulates this event by applying the transformation function.
         *
         * @param stage The current simulation stage.
         * @return A new stage of simulation.
         */
        public SimulationStage simulate(SimulationStage stage) {
            return this.transform.apply(stage);
        }
    }

    /**
     * The priority queue of events.
     */
    private PriorityQueue<Event> events;

    /**
     * The associated statistics.
     */
    private final Statistics stats;

    /**
     * The shop of servers.
     */
    private final Shop shop;

    /**
     * The event logs.
     */
    private final String log;

    /**
     * The last customer's id.
     */
    private final int lastCustomerId;

    /**
     * The given random generator
     */
    private final RandomGeneratorGetter randomGenerator;

    /**
     * probability of resting
     */
    private final double restingProb;

    /**
     * probability of a greedy customer occurring
     */
    private final double greedyProb;

    /**
     * A private constructor of internal states.
     *
     * @param shop              The list of servers.
     * @param stats             The statistics being kept.
     * @param events            A priority queue of events.
     * @param log               A log of what's happened so far.
     * @param lastCustomerId    the last customer's id
     * @param randomGenerator   the given generator
     * @param restingProb       probability of resting
     * @param greedyProb        probability of a greedy customer occurring
     */
    private SimulationStage(Shop shop, Statistics stats, PriorityQueue<Event> events, String log, int lastCustomerId,
                            RandomGeneratorGetter randomGenerator, double restingProb, double greedyProb) {
        this.shop = shop;
        this.stats = stats;
        this.events = events;
        this.log = log;
        this.lastCustomerId = lastCustomerId;
        this.randomGenerator = randomGenerator;
        this.restingProb = restingProb;
        this.greedyProb = greedyProb;
    }

    /**
     * Constructs a simulation stage from scratch.
     *
     * @param numOfServers      The number of regular servers
     * @param numOfSelfCheck    The number of self-check counters
     * @param maxQueueLen       The maximum queue length
     * @param randomGenerator   The given random generator
     * @param restingProb       probability of resting
     * @param greedyProb        probability of a greedy customer occurring
     */
    public SimulationStage(int numOfServers, int numOfSelfCheck, int maxQueueLen, RandomGeneratorGetter randomGenerator,
                           double restingProb, double greedyProb) {
        this(new Shop(numOfServers, numOfSelfCheck, maxQueueLen),
                new Statistics(),
                new PriorityQueue<Event>(),
                "", 1,
                randomGenerator,
                restingProb,
                greedyProb);
    }

    /**
     * Updates the statistics of this simulation.
     *
     * @param stats The statistics to be updated
     * @return The new simulation stage with the updated statistics.
     */
    private SimulationStage updateStats(Statistics stats) {
        return new SimulationStage(this.shop, stats, this.events, this.log, this.lastCustomerId,
                this.randomGenerator, this.restingProb, this.greedyProb);
    }

    /**
     * Updates a server of this simulations.
     *
     * @param s The server to be updated
     * @return The new simulation stage with the updated statistics
     */
    private SimulationStage updateServer(Server s) {
        return new SimulationStage(shop.replace(s), this.stats, this.events, this.log,
                this.lastCustomerId, this.randomGenerator, this.restingProb, this.greedyProb);
    }

    /**
     * Updates the event queue of this simulations.
     *
     * @param pq The priority queue to be updated
     * @return The new simulation stage with the updated event queue.
     */
    private SimulationStage updateEvents(PriorityQueue<Event> pq) {
        return new SimulationStage(this.shop, this.stats, pq, this.log, this.lastCustomerId,
                this.randomGenerator, this.restingProb, this.greedyProb);
    }

    /**
     * Updates the event log of this simulations.
     *
     * @param s The log string to be append the current event log.
     * @return The new simulation stage  with the updated log.
     */
    private SimulationStage updateLog(String s) {
        return new SimulationStage(this.shop, this.stats, this.events, this.log + s, this.lastCustomerId,
                this.randomGenerator, this.restingProb, this.greedyProb);
    }

    /**
     * Updates the last customer's id of this simulations.
     *
     * @param id The new "last customer's id"
     * @return The new simulation stage with updated last customer's id.
     */
    private SimulationStage updateId(int id) {
        return new SimulationStage(this.shop, this.stats, this.events, this.log, id,
                this.randomGenerator, this.restingProb, this.greedyProb);
    }

    /**
     * Adds an event to the simulation's event queue.
     *
     * @param time          The time the event to be added occur.
     * @param customerId    the associated customer's id
     * @param transform        How the stage to be updated upon execution of this event.
     * @return The new simulation stage.
     */
    public SimulationStage addEvent(double time, int customerId, Function<SimulationStage, SimulationStage> transform) {
        PriorityQueue<Event> newEvents = events;
        newEvents.add(new Event(time, customerId, transform));
        return updateEvents(newEvents);
    }

    /**
     * Retrieves the next event with earliest time stamp from the
     * priority queue along with the new stage.  If there is no more event, an
     * Optional.empty will be returned.
     *
     * @return A pair object with an (optional) event and the new simulation stage.
     */
    private Pair<Optional<Event>, SimulationStage> nextEvent() {
        Optional<Event> next = Optional.ofNullable(this.events.poll());
        return Pair.of(next, updateEvents(this.events));
    }

    /**
     * updates the logs of simulation when a customer arrived in the simulation.
     *
     * @param time The time the customer arrives.
     * @param c    The customer that arrrives.
     * @return A new stage of the simulation after the customer arrives.
     */
    public SimulationStage logArrival(double time, Customer c) {
        return updateLog(String.format("%.3f %s arrives\n", time, c));
    }

    /**
     * updates the logs of simulation when a customer is notified to wait in the simulation.
     *
     * @param time The time the customer arrives.
     * @param c    The customer that is going to wait.
     * @return A new stage of the simulation after the customer start to wait.
     */
    public SimulationStage logWait(double time, Server s, Customer c) {
        return updateLog(String.format("%.3f %s waits to be served by %s\n", time, c, s));
    }

    /**
     * updates the logs of simulation when a customer is served in the simulation.
     *
     * @param time The time the customer is being served.
     * @param s    The server that is serving the customer.
     * @param c    The customer that is being served.
     * @return A new stage of the simulation after the customer is served.
     */
    public SimulationStage logServed(double time, Server s, Customer c) {
        return updateLog(String.format("%.3f %s served by %s\n", time, c, s))
                .updateStats(stats
                        .serveOneCustomer()
                        .recordWaitingTime(c.timeWaited(time)));
    }

    /**
     * updates the logs of simulation when a customer is done being served
     *
     * @param time The time the customer is done being served.
     * @param s    The server that served the customer.
     * @param c    The customer that is served.
     * @return A new stage of the simulation after the customer is done being served.
     */
    public SimulationStage logDone(double time, Server s, Customer c) {
        return updateLog(String.format("%.3f %s done serving by %s\n", time, c, s));
    }

    /**
     * updates the logs of simulation when a customer leaves the shops without being served.
     *
     * @param time     The time this customer leaves.
     * @param customer The customer who leaves.
     * @return A new stage of the simulation after the customer leaves.
     */
    public SimulationStage logLeave(double time, Customer customer) {
        return updateLog(String.format("%.3f %s leaves\n", time, customer))
                .updateStats(stats.looseOneCustomer());
    }

    /**
     * Simulates what happened when a customer arrives.
     * Here, it is possible that the customer is greedy
     * The customer is either served, waiting to be served, or leaves.
     * @param time The time the customer arrives.
     * @return A new stage of the simulation.
     */
    public SimulationStage simulateArrival(double time) {
        Customer customer;
        if( randomGenerator.genCustomerType() < greedyProb) {
            customer = new GreedyCustomer(time, this.lastCustomerId);
        } else {
            customer = new Customer(time, this.lastCustomerId);
        }
        return logArrival(time, customer)
                .updateId(this.lastCustomerId + 1)
                .processArrival(time, customer);
    }

    /**
     * Finds an idle server to serve the customer,
     * or a server that the customer can wait for (if no server is available at the moment),
     * or leave.
     *
     * @param time     The time the customer arrives.
     * @param customer The customer to be served.
     * @return A new stage of the simulation.
     */
    private SimulationStage processArrival(double time, Customer customer) {
        return shop.find(server -> server.isIdle())
                .map(server -> serveCustomer(time, server, customer))
                .or(() -> shop
                        .findFor(customer)
                        .map(server -> logWait(time, server, customer)
                                .updateServer(server.askToWait(customer))))
                .orElse(logLeave(time, customer));
    }

    /**
     * Simulates what happened when a customer is done being served.
     * It is possible for the server to go to rest after serving this customer. If so,
     * add two events representing the server going for resting and coming back to work.
     * Otherwise, continue serving the next customer if there is one.
     *
     * @param time     The time the service is done.
     * @param server   The server serving the customer.
     * @param customer The customer being served.
     * @return A new stage of the simulation.
     */
    public SimulationStage simulateDone(double time, Server server, Customer customer) {
        if(!server.restLess()){
            if(randomGenerator.genRandomRest() < restingProb) {
                double backTime = randomGenerator.genRestPeriod() + time;
                return logDone(time, server, customer)
                        .addEvent(time, customer.getId(), stage -> stage.simulateRest(server))
                        .addEvent(backTime, customer.getId(), stage -> stage.simulateBack(backTime, server));
            }
        }
        return shop.find(s -> s.equals(server))
                .flatMap(s -> s.getNextWaitingCustomer())
                .map(c -> logDone(time, server, customer).serveCustomer(time, server, c))
                .orElseGet(() -> logDone(time, server, customer).updateServer(server.becomeIdle()));
    }

    /**
     * Updates the simulation stage if a customer is served by a server
     * A new done event is generated and scheduled.
     *
     * @param time     The time this customer is served.
     * @param server   The server serving this customer.
     * @param customer The customer being served.
     * @return A new stage of the simulation.
     */
    public SimulationStage serveCustomer(double time, Server server, Customer customer) {
        double doneTime = time + randomGenerator.genServiceTime();
        return updateServer(server.serve(customer))
                .logServed(time, server, customer)
                .addEvent(doneTime, customer.getId(), stage -> stage.simulateDone(doneTime, server, customer));
    }

    /**
     * Simulates what happened when a server rests
     *
     * @param server the server who is going to rest
     * @return      A new stage of the simulation
     */
    public SimulationStage simulateRest(Server server) {
        return updateServer(server.rest());
    }

    /**
     * Simulates what happened when a server come back to work,
     * he will start to serve the next customer immediately
     * or become idle if there is no one waiting.
     *
     * @param time      the time when the server comes back
     * @param server    the server who is coming back
     * @return      A new stage of the simulation
     */
    public SimulationStage simulateBack(double time, Server server) {

        return shop.find(s -> s.equals(server))
                .flatMap(s -> s.getNextWaitingCustomer())
                .map(c -> updateServer(server.back()).serveCustomer(time, server, c))
                .orElseGet(() -> updateServer(server.back()).updateServer(server.becomeIdle()));
    }

    /**
     * Runs the simulation by repeatedly getting events from the event queue,
     * simulating and updating the events.
     * Return the final simulation stage.
     *
     * @return The final stage of the simulation.
     */
    public SimulationStage run() {
        Pair<Optional<Event>, SimulationStage> s = Stream.iterate(this.nextEvent(),
                p -> p.first.isPresent(),
                p -> p.first.get().simulate(p.second).nextEvent())
                .reduce((p, q) -> q)
                .orElseThrow();
        return s.first.get().simulate(s.second);
    }

    /**
     * Return a string representation of the simulation stage,
     * which consists of all the logs and the stats.
     *
     * @return A string representation of the simulation.
     */
    public String toString() {
        return log + stats.toString();
    }
}
