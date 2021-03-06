import java.time.Instant;
import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CompletableFuture;

/**
 * This program finds different ways one can travel by bus (with a bit 
 * of walking) from one bus stop to another.
 *
 * @author: Ooi Wei Tsang
 * @version: CS2030 AY19/20 Semester 1, Lab 10
 */
public class Main {
  /**
   * The program read a sequence of (id, search string) from standard input.
   * @param args Command line arguments
   */
  public static void main(String[] args) throws ExecutionException, InterruptedException {
  	Instant start = Instant.now();
      Scanner sc = new Scanner(System.in);
	  
	  CompletableFuture<String> combined = CompletableFuture.completedFuture("");
	  
      while (sc.hasNext()) {
          BusStop srcId = new BusStop(sc.next());
          String searchString = sc.next();
          combined = combined.thenCombine(BusSg.findBusServicesBetween(srcId, searchString).thenCompose(BusRoutes::description), (a, b)->(a == "")?a + b : a + "\n" + b);
      }
	  sc.close();
	  
	  CompletableFuture.allOf(combined); // just a placeholder, they have already been combined in the while loop using thenCombine.
	  combined.join();
	  System.out.println(combined.get());
      
      Instant stop = Instant.now();
      System.out.printf("Took %,dms\n", Duration.between(start, stop).toMillis());
  }
}
