public class Server{
	private final Customer serving;
	private final Customer waiting;
	private final double nextServiceTime;
	
	public Server(){
		serving = null;
		waiting = null;
		nextServiceTime = 0;
	}

	public Server(Customer c){
		serving = c;
		waiting = null;
		nextServiceTime = c.getArrivalTime() + 1.0;
	}

	public boolean canServe(Customer c){
		if(serving == null || c.getArrivalTime() >= nextServiceTime) return true;
		else return false;
	}

	public Server serve(Customer c){
		if(canServe(c)) return new Server(c);
		else return null;
	}

	@Override
	public String toString(){
		if(serving != null) return (String.format("Customer served; next service @%.3f", nextServiceTime));
		else return "Server available";

	}


}
