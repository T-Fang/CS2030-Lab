import java.util.Scanner;
import java.util.PriorityQueue;

class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int id = 1;
		PriorityQueue<Customer> queue = new PriorityQueue<>(new ArrivalTimeComparator());
		double arrivalTime;
		
		while(sc.hasNext()){
			queue.add(new Customer(id++, sc.nextDouble()));
		}

		Server server = new Server();

		for(int i = 1; i < id; i++){
			Customer c = queue.poll();
			System.out.println(c);
			if(server.canServe(c)){
				server = server.serve(c);
				System.out.println(server);
			}else {
				System.out.println("Customer leaves");
			}
		}

		System.out.println("Number of customers: " + (id - 1));

		sc.close();

	}
}
