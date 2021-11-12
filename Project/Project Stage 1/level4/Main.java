import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Collections;

class Main{

	private static void printAll(PriorityQueue<Event> events){
		for(Event e: events){
			System.out.println(e);
		}
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int id = 1;
		PriorityQueue<Customer> queue = new PriorityQueue<>(new ArrivalTimeComparator());
		double arrivalTime;
		
		while(sc.hasNext()){
			queue.add(new Customer(id++, sc.nextDouble()));
		}

		Server server = new Server();
		
		PriorityQueue<Event> events = new PriorityQueue<>(new EventComparator());
		
		System.out.println("# Adding arrivals");

		for(Customer c: queue){
			Event event = new Event(c, "arrives");
			events.add(event);
			System.out.println(c + "arrives");
		}
		
		
		
		for(int i = 1; i < id; i++){
			Customer c = queue.poll();
			
			
			while(events.peek().getState() == "done"){
				Event event = events.poll();
				System.out.println("\n# Get next event: " + event);
				printAll(events);		
			}

			if(server.canServe(c)){
				server = server.serve(c);
				
				//get the arrive event
				Event arriveEvent = events.poll();
				
				
				
				//add a serve event
				events.add(new Event(c, "served"));
				
				
				System.out.println("\n# Get next event: " + arriveEvent);
				
				//get the served event
				Event serveEvent = events.poll();
				
				//print all events
				System.out.println(serveEvent);
				printAll(events);
				
				events.add(new Event(c.getArrivalTime() + 1.0, c.getID(), "done"));
				
				events.add(serveEvent);
				events.poll();
				//print all events
				System.out.println("\n# Get next event: " + serveEvent);
				printAll(events);		
				
				
			}else {

				//get the arrive event
				Event arriveEvent = events.poll();
				//add a leave event
				events.add(new Event(c, "leaves"));
				
				
				System.out.println("\n# Get next event: " + arriveEvent);
				
				//get the leave event
				Event leaveEvent = events.poll();
				
				//print all events
				System.out.println(leaveEvent);
				printAll(events);		

				//print all events
				System.out.println("\n# Get next event: " + leaveEvent);
				printAll(events);		
			}
		}
		
		
		while(events.peek() != null && events.peek().getState() == "done"){
				Event event = events.poll();
				System.out.println("\n# Get next event: " + event);
				printAll(events);		
		}
			
		System.out.println("\nNumber of customers: " + (id - 1));

		sc.close();

	}
}
