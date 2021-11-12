/**
 * class Customer
 * Seeks service from the shop
 * has an id, an arrival time, and a state.
 */

public class Customer {
	private final int id;
	private final double arrivalTime;
	private final CustomerState state;

	/**
	 * Constructor for Customer
	 * @param id			customer's unique id
	 * @param arrivalTime	customer's arrival time
	 * @param state			customer's state (arrives, waits, served, leaves, or done)
	 */
	public Customer(int id, double arrivalTime, CustomerState state){
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.state = state;
	}

	/**
	 * @return the customer's id
	 */
	public int getID(){
		return id;
	}

	/**
	 * @return	the customer's arrival time
	 */
	public double getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * @return	the customer's state
	 */
	public CustomerState getState() {
		return state;
	};

	/**
	 * @return	a new customer with updated status of "served"
	 */
	public Customer served() {
		return new Customer(id, arrivalTime, CustomerState.SERVED);
	}

	/**
	 * @return	a new customer with updated status of "waits"
	 */
	public Customer waits() {
		return new Customer(id, arrivalTime, CustomerState.WAITS);
	}

	/**
	 * @return	a new customer with updated status of "done"
	 */
	public Customer done() {
		return new Customer(id, arrivalTime, CustomerState.DONE);
	}

	/**
	 * @return	a new customer with updated status of "leaves"
	 */
	public Customer leaves() {
		return new Customer(id, arrivalTime, CustomerState.LEAVES);
	}

	/**
	 * @return	the String representation of the customer
	 */
	@Override
	public String toString(){
		return String.format("%.3f %d ", arrivalTime, id);
	}

}
