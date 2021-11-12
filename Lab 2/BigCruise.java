
class BigCruise extends Cruise{
	
	private final static int loadingRate = 50;
	private final static int lengthPerLoader = 40;
	
	public BigCruise(String id, int arrivalTime, double len, double passenger){
		super(id, arrivalTime, (int) Math.ceil(len/lengthPerLoader), (int) Math.ceil(passenger/loadingRate));
	}
}
