/**
 * class Server
 * Serves customers if the server is idle,
 * or let the customer wait if the server is only serving one customer when the customer arrives.
 */

public class Server{
	private final int id;
	private final Customer serving;
	private final Customer waiting;
	private final double nextServiceTime;

	/**
	 * Constructor of Server
	 * @param id	server's unique id
	 */

	public Server(int id){
		this.id = id;
		serving = null;
		waiting = null;
		nextServiceTime = 0;
	}

	/**
	 * Constructor of Server
	 * @param id	server's unique id
	 * @param c		customer to be served immediately (which means the server is idle when the customer arrives)
	 */
	public Server(int id, Customer c){
		this.id = id;
		serving = c;
		waiting = null;
		nextServiceTime = c.getArrivalTime() + 1.0;
	}

	/**
	 * Constructor of Server
	 * @param id				server's unique id
	 * @param serving			customer that is currently being served by the server
	 * @param waiting			customer that is waiting to be served by the server
	 * @param nextServiceTime	the time when the server can continue to serve another customer
	 */
	public Server(int id, Customer serving, Customer waiting, double nextServiceTime){
		this.id = id;
		this.serving = serving;
		this.waiting = waiting;
		this.nextServiceTime = nextServiceTime;
	}

	/**
	 * @return the server's id
	 */
	public int getId(){
		return id;
	}

	/**
	 * @return the time when the server can continue to serve another customer
	 */
	public double getNextServiceTime(){
		return nextServiceTime;
	}

	/**
	 * Checks whether a customer can be served by the server
	 * @param c	a customer who wants to be served
	 * @return 	the server's response that whether the customer can be served by the server
	 */
	public ServerResponse canServe(Customer c){
		if (c.getArrivalTime() >= nextServiceTime || serving == null){
			return ServerResponse.IDLE;
		} else if (waiting == null){
			return ServerResponse.CANSERVEAFTERONECUSTOMER;
		}else if (nextServiceTime - 1.0 <= c.getArrivalTime()){
			return ServerResponse.CANSERVEDAFTERTWOCUSTOMER;
		}else return ServerResponse.BUSY;
	}

	/**
	 * Serves a customer
	 * @param c	customer to be served
	 * @return	a new server with updated status
	 */
	public Server serve(Customer c){
		if(canServe(c) == ServerResponse.IDLE) return new Server(id, c);
		else if(canServe(c) == ServerResponse.CANSERVEAFTERONECUSTOMER) return new Server(id, serving, c, nextServiceTime + 1.0);
		else if(canServe(c) == ServerResponse.CANSERVEDAFTERTWOCUSTOMER) return new Server(id, waiting, c, nextServiceTime + 1.0);
		else return null;
	}

	/**
	 * @return the String representation of a server
	 */
	@Override
	public String toString(){
		if(serving == null) return "Server available";
		else if(waiting == null) return "Serving: " + serving + " Next Service Time: " + nextServiceTime;
		else return "Serving: " + serving + " Waiting: " + waiting + " Next Service Time: " + nextServiceTime;
	}

}
