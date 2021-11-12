import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Iterator;

/**
 * class Main
 * Takes in input and prints out outputs
 */
class Main{

	/**
	 * Takes in number of servers and customers who are going to arrive
	 * Prints out events happened and statistics of the shop
	 * @param args
	 */
	public static void main(String[] args){
		Shop shop = new Shop();
		Scanner sc = new Scanner(System.in);
		int numOfServers = sc.nextInt();
		// add servers in the shop
		for(int i = 1; i <= numOfServers; i++){
			shop.addServer(new Server(i));
		}

		// add customers seeking service from the shop
		int id = 1;
		while(sc.hasNext()){
			shop.addCustomer(new Customer(id++, sc.nextDouble(), CustomerState.ARRIVES));
		}

		// serve all the customers
		shop.serve();

		// Print events and statistics
		PriorityQueue<Event> events = shop.showEvents();
		Iterator<Event> iter = events.iterator();
		while (iter.hasNext()) {
			System.out.println(events.poll());
		}
		System.out.println(String.format("[%.3f %d %d]",shop.avgWaitTime(), shop.numOfServed(), shop.numOfLeft()));

		sc.close();

	}
}
