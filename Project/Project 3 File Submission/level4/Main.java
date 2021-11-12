import java.util.Scanner;
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
        int numOfSelfCheck = sc.nextInt();
        int maxQueueLen = sc.nextInt();
        int numOfCustomers = sc.nextInt();
        double lambda = sc.nextDouble();
        double mu = sc.nextDouble();
        double rho = sc.nextDouble();
        double restingProb = sc.nextDouble();
        double greedyProb = sc.nextDouble();
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

}
