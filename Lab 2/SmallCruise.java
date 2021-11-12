import java.lang.Math;
class SmallCruise extends Cruise{
	
	private final static int loadTime = 30;
	private final static int loaderNumber = 1;
	
	public SmallCruise(String id, int arrivalTime){
		super(id, arrivalTime, loaderNumber, loadTime);	
	}
	

}
