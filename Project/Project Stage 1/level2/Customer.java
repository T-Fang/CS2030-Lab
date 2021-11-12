public class Customer {
	private final int ID;
	private final double arrivalTime;

	public Customer(int ID, double arrivalTime){
		this.ID = ID;
		this.arrivalTime = arrivalTime;
	}

		
	public double getArrivalTime(){
		return arrivalTime;
	}

	@Override
	public String toString(){
		return String.format("%d arrives at %.3f", ID, arrivalTime);
	}

}
