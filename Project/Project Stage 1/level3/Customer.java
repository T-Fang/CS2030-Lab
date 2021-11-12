public class Customer {
	private final int id;
	private final double arrivalTime;

	public Customer(int id, double arrivalTime){
		this.id = id;
		this.arrivalTime = arrivalTime;
	}

		
	public double getArrivalTime(){
		return arrivalTime;
	}

	@Override
	public String toString(){
		return String.format("%.3f %d ", arrivalTime, id);
	}

}
