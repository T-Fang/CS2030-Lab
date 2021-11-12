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
	
	public Server(Customer serving, Customer waiting, double nextServiceTime){
		this.serving = serving;
		this.waiting = waiting;
		this.nextServiceTime = nextServiceTime;
	}
	
	public double getNextServiceTime(){
		return nextServiceTime;
	}
	
	public int canServe(Customer c){
		if (c.getArrivalTime() >= nextServiceTime || serving == null){
			return 1;
		} else if (waiting == null){
			return 2;
		}else if (nextServiceTime - 1.0 <= c.getArrivalTime()){
			return 3;
		}else return 0;
	}

	public Server serve(Customer c){
		if(canServe(c) == 1) return new Server(c);
		else if(canServe(c) == 2) return new Server(serving, c, nextServiceTime + 1.0);
		else if(canServe(c) == 3) return new Server(waiting, c, nextServiceTime + 1.0);
		else return null;
	}

	
	@Override
	
	public String toString(){
		if(serving == null) return "Server available";
		else if(waiting == null) return "Serving: " + serving + " Next Service Time: " + nextServiceTime;
		else return "Serving: " + serving + " Waiting: " + waiting + " Next Service Time: " + nextServiceTime;
	}

}
