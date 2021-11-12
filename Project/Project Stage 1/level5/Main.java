import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Iterator;

class Main{

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int id = 1;
		PriorityQueue<Customer> queue = new PriorityQueue<>(new ArrivalTimeComparator());
		
		int servedPeople = 0;
		double waitingTime = 0;
		
		
		while(sc.hasNext()){
			queue.add(new Customer(id++, sc.nextDouble()));
		}

		Server server = new Server();
		
		PriorityQueue<Event> events = new PriorityQueue<>(new EventComparator());
		
		//add all arrivals to events
		for(Customer c: queue){
			Event event = new Event(c, "arrives");
			events.add(event);
		}
		
		
		for(int i = 1; i < id; i++){
			Customer c = queue.poll();
			
			if(server.canServe(c) == 1){
				
				servedPeople ++;
				
				server = server.serve(c);
				
				events.add(new Event(c, "served"));
				events.add(new Event(c.getArrivalTime() + 1.0, c.getID(), "done"));
				
			} else if(server.canServe(c) == 2 || server.canServe(c) == 3){
				
				double servedTime = server.getNextServiceTime();
				waitingTime += servedTime - c.getArrivalTime();
				servedPeople ++;
				
				server = server.serve(c);
				
				events.add(new Event(c, "waits"));
				events.add(new Event(servedTime, c.getID(), "served"));
				events.add(new Event(servedTime + 1.0, c.getID(), "done"));
			} else {
				events.add(new Event(c, "leaves"));
			}
			
		}
		
		Iterator<Event> iter = events.iterator();
		while (iter.hasNext()) {
            System.out.println(events.poll());
        }
		
		System.out.println(String.format("[%.3f %d %d]", waitingTime/servedPeople, servedPeople, id - 1 - servedPeople));

		sc.close();

	}
}
