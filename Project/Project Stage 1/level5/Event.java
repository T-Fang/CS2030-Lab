public class Event{
	private final double time;
	private final int id;
	private final String state;

	public Event(double time, int id, String state){
		this.time = time;
		this.id = id;
		this.state = state;
	}

	public Event(Customer c, String state){
		this.time = c.getArrivalTime();
		this.id = c.getID();
		this.state = state;
	}

	public double getTime(){
		return time;
	}
	
	public int getID(){
		return id;
	}
	
	public String getState(){
		return state;
	}
	@Override
	public String toString(){
		return String.format("%.3f %d %s", time, id, state);
	}

}
