import java.util.Comparator;

/**
 * class EventComparator
 * Compares the arrival times of customers
 */
public class ArrivalTimeComparator implements Comparator<Customer>{

    /**
     * Overrides the compare method
     * @param c1    the first customer
     * @param c2    the second customer
     * @return      0 if they have the same arrival times,
     *              1 if the first customer arrives after the second customer,
     *              or -1 otherwise.
     */
	@Override
	public int compare(Customer c1, Customer c2){
		if (c1.getArrivalTime() == c2.getArrivalTime()) {
            return 0;
        } else if (c1.getArrivalTime() > c2.getArrivalTime()) {
            return 1;
        } else {
            return -1;
        }
	}
}
