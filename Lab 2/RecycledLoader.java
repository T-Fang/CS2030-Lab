public class RecycledLoader extends Loader{
	
	public RecycledLoader(int id){
		super(id);
	}
	
	protected RecycledLoader(int id, int serveEndTime, String serving){
		super(id, serveEndTime, serving);
	}
	@Override
	public Loader serve(Cruise cruise){
        if(cruise == null){
			return new RecycledLoader(this.id, this.serveEndTime, this.serving);
        } else if(canServe(cruise)) {
            return new RecycledLoader(this.id, cruise.getServiceCompletionTime() + 60, "serving " + cruise);
        } else {
            return null;   
        }
    }
	
	@Override
    public String toString(){
        return "Loader " + id + " (recycled) " + serving;
    }
}
