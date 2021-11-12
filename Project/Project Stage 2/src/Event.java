/**
 * class Event
 * Keeps track of a happened event, indicating what happened to a specific customer
 */

public class Event{
	protected final double time;
	protected final Customer customer;
	protected final Server server;

	/**
	 * Constructor of Event
	 * @param c	customer that arrives at the shop
	 */
	public Event(Customer c){
		this.time = c.getArrivalTime();
		this.customer = c;
		this.server = null;
	}

	/**
	 * Constructor of Event
	 * @param time		time when the event happened
	 * @param customer	customer related to the event
	 */
	public Event(double time, Customer customer){
		this.time = time;
		this.customer = customer;
		this.server = null;
	}

	/**
	 * Constructor of Event
	 * @param time		time when the event happend
	 * @param customer	customer related to the event
	 * @param server	server related to the event
	 */
	public Event(double time, Customer customer, Server server){
		this.time = time;
		this.customer = customer;
		this.server = server;
	}

	/**
	 * @return the time of the event
	 */
	public double getTime(){
		return time;
	}

	/**
	 * @return	the customer related to the event
	 */
	public Customer getCustomer(){
		return customer;
	}

	/**
	 * @return	the String representation of the event
	 */
	@Override
	public String toString(){
		if(server == null){
			return String.format("%.3f %d %s", time, customer.getID(), customer.getState().getState());
		} else {
			return String.format("%.3f %d %s by %d", time, customer.getID(), customer.getState().getState(), server.getId());
		}

	}

}
