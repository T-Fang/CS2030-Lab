import java.lang.String;

class Loader{
    protected final int id;
    protected final int serveEndTime;
    protected final String serving;
	
    public Loader(int id){
        this.id = id;
        this.serveEndTime = -1;
        this.serving = "";
    }
    
	protected Loader(int id, int serveEndTime, String serving){
		this.id = id;
		this.serveEndTime = serveEndTime;
		this.serving = serving;
	}

    public Loader serve(Cruise cruise){
        if(cruise == null){
			return new Loader(id, serveEndTime, serving);
        } else if(canServe(cruise)) {
            return new Loader(id, cruise.getServiceCompletionTime(), " serving " + cruise);
        } else {
            return null;   
        }
    }

    boolean canServe(Cruise cruise){
        if(cruise == null){
            return true;
        } else {
            return serveEndTime <= cruise.getArrivalTime();
        }
    }


    @Override
    public String toString(){
        return "Loader " + id + serving;
    }
}
