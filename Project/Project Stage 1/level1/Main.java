import java.util.Scanner;
import java.util.PriorityQueue;

class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int id = 1;
		PriorityQueue<Customer> queue = new PriorityQueue<>(new ArrivalTimeComparator());
		
		while(sc.hasNext()){
			queue.add(new Customer(id++, sc.nextDouble()));
		}

		

		for(int i = 1; i < id; i++){
			System.out.println(queue.poll());
		}

		System.out.println("Number of customers: " + (id - 1));

		sc.close();

	}
}
