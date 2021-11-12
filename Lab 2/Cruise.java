import java.lang.String;

public class Cruise{
    private final String id;
    private final int arrivalTime;
    private final int serveTime;
    private final int loaderNumber;
    
   	Cruise(String id, int arrivalTime, int loaderNumber, int serveTime){
      	this.id = id;
        this.arrivalTime = arrivalTime;
        this.serveTime = serveTime;
        this.loaderNumber = loaderNumber;
        
    }
    

    public int getArrivalTime(){
        int arrivalMins = arrivalTime % 100;
        int arrivalHrs= (arrivalTime - arrivalMins) / 100;
        int totalArrivalTime = arrivalMins + arrivalHrs * 60;
        return totalArrivalTime;
    }

    public int getNumOfLoadersRequired(){
        return loaderNumber;   
    }

    public int getServiceCompletionTime(){
        return getArrivalTime() + serveTime;
    }
    @Override
    public String toString(){
        return String.format("%s@%04d", id, arrivalTime);
        
    }
}

