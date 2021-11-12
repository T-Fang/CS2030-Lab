import java.util.Comparator;

/**
 * class EventComparator
 * Compares two events
 */
public class EventComparator implements Comparator<Event>{

	/**
	 * Overrides the compare method
	 * @param e1	the first event
	 * @param e2	the second event
	 * @return		0 if they are the same event, 1 if e1 happened after e2, or -1 otherwise.
	 */
	@Override
	public int compare(Event e1, Event e2){
		if(e1.getTime() == e2.getTime()){
			if(e1.getCustomer().getID() == e2.getCustomer().getID()) {
				return e1.getCustomer().getState().ordinal() - e2.getCustomer().getState().ordinal();
			} else return e1.getCustomer().getID() - e2.getCustomer().getID();
		} else if (e1.getTime() > e2.getTime()){
			return 1;
		} else {
			return -1;
		}
	}
}
