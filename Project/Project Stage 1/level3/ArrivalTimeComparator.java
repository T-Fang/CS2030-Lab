import java.util.Comparator;

public class ArrivalTimeComparator implements Comparator<Customer>{
	
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
