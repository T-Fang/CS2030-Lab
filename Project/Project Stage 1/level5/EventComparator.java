import java.util.Comparator;

public class EventComparator implements Comparator<Event>{
	
	@Override
	public int compare(Event e1, Event e2){
		if(e1.getTime() == e2.getTime()){
			if(e1.getID() == e2.getID()) {
				if(e1.getState() == e2.getState()) return 0;
				else if(e1.getState() == "waits" && e2.getState() == "arrives") return 1;
				else if(e1.getState() == "served" && e2.getState() == "arrives") return 1;
				else if(e1.getState() == "leaves" && e2.getState() == "arrives") return 1;
				else return -1;
			}
			else if(e1.getID() > e2.getID()) return 1;
			else return -1;
		} else if (e1.getTime() > e2.getTime()){
			return 1;
		} else {
			return -1;
		}
	}
}
