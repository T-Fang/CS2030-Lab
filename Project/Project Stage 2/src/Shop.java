import java.util.PriorityQueue;
import java.util.ArrayList;

/**
 * class Shop
 * Assign servers to serve different customers and keeps track of events and statistics.
 * */

public class Shop {

    private int numOfCustomers = 0;
    private int served = 0;
    private double waitingTime = 0;

    private ArrayList<Server> servers = new ArrayList<>();
    private PriorityQueue<Customer> queue = new PriorityQueue<>(new ArrivalTimeComparator());
    private PriorityQueue<Event> events = new PriorityQueue<>(new EventComparator());

    /**
     * Constructor of Shop
     */
    public Shop(){

    }

    /**
     * Adds a server to the shop
     * @param server    a new server being employed
     */
    public void addServer(Server server) {
        servers.add(server);
    }

    /**
     * Adds a customer to the queue
     * @param customer  a customer seeking service from the shop
     */
    public void addCustomer(Customer customer) {
        queue.add(customer);
        numOfCustomers++;
    }

    /**
     * @return all the events happened
     */
    public PriorityQueue<Event> showEvents() {
        return events;
    }

    /**
     * @return  number of people served
     */
    public int numOfServed() {
        return served;
    }

    /**
     * @return  number of people left without being served
     */
    public int numOfLeft(){
        return numOfCustomers - served;
    }

    /**
     * @return  the average waiting time of a customer
     */
    public double avgWaitTime() {
        return waitingTime / served;
    }

    /**
     * Starts serving all the customers with all servers in the shop
     */
    public void serve(){

        // add all arrivals to events
        for(Customer c: queue){
            events.add(new Event(c));
        }

        for(int i = 1; i <= numOfCustomers; i++){
            Customer c = queue.poll();

            // keeps a flag to indicate whether the customer is being served
            boolean isServed = false;

            // firstly, check if there is an idle server
            for(Server server: servers) {
                if (server.canServe(c) == ServerResponse.IDLE) {
                    isServed = true;
                    served++;
                    c = c.served();

                    // update servers' status after the server served the customer
                    int index = servers.indexOf(server);
                    server = server.serve(c);
                    servers.set(index, server);

                    // add events of "being served"
                    events.add(new Event(c.getArrivalTime(), c, server));

                    c = c.done();
                    // add events of "done"
                    events.add(new Event(c.getArrivalTime() + 1.0, c, server));

                    break;
                }
            }

            // if the customer is still not served, that means no server is idle
            // check if there is a server who serve the customer after serving the server's current customer
            for(Server server: servers) {
                if(!isServed && (server.canServe(c) == ServerResponse.CANSERVEAFTERONECUSTOMER
                        || server.canServe(c) == ServerResponse.CANSERVEDAFTERTWOCUSTOMER)) {

                    double servedTime = server.getNextServiceTime();
                    // in this case, the customer has to wait. Add corresponding waiting time to the waitingTime property.
                    waitingTime += servedTime - c.getArrivalTime();
                    c = c.waits();
                    // add events of "waits"
                    events.add(new Event(c.getArrivalTime(), c, server));

                    // update servers' status after the server served the customer
                    int index = servers.indexOf(server);
                    server = server.serve(c);
                    servers.set(index, server);

                    c = c.served();
                    isServed = true;
                    served++;
                    // add events of "being served"
                    events.add(new Event(servedTime, c, server));

                    c = c.done();
                    // add events of "done"
                    events.add(new Event(servedTime + 1.0, c, server));

                    break;
                }
            }

            // if the flag is still false, it means that the customer cannot be served. And the customer will leave.
            if(!isServed) {
                c = c.leaves();
                events.add(new Event(c));
            }

        }

    }

}
