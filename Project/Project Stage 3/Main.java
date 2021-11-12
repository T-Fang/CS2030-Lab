import java.util.Scanner;
import java.util.stream.Stream;

import cs2030.simulator.SimulationStage;
import cs2030.simulator.RandomGeneratorGetter;


/**
 * Entry class into the project.
 */
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int seed = sc.nextInt();
        int numOfServers = sc.nextInt();
        int numOfSelfCheck = 0;
        int maxQueueLen = sc.nextInt();
        int numOfCustomers = sc.nextInt();
        double lambda = sc.nextDouble();
        double mu = sc.nextDouble();
        double rho = sc.nextDouble();
        double restingProb = sc.nextDouble();
        double greedyProb = 0;
        RandomGeneratorGetter randomGenerator = new RandomGeneratorGetter();
        randomGenerator.instantiate(seed, lambda, mu, rho);
        SimulationStage ss = new SimulationStage(numOfServers, numOfSelfCheck, maxQueueLen, randomGenerator, restingProb, greedyProb);

        int customerCount = 1;
        double timestamp = 0;
        while(customerCount <= numOfCustomers){
            double arrivalTime = timestamp;
            timestamp += randomGenerator.genInterArrivalTime();
            ss.addEvent(arrivalTime, customerCount++, state -> state.simulateArrival(arrivalTime));
        }

        ss = ss.run();
        System.out.println(ss);

        sc.close();

    }

    /**
     * Merges this stream with another stream.
     * @param s2        the stream to be merged with the current stream
     * @param lambda    the lambda expression to be applied to an element from this stream and an element from s2
     * @return          a new stream got from merging this stream and s2
     */
    <R, U> Stream<R> merge(Stream<U> s2, BiFunction<? super T, ? super U, ? extends R> lambda ){

    }

}
