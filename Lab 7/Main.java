import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Arrays;


public class Main {
	
	public static boolean isPrime(int n){
		return IntStream.range(2, n/2 + 1).noneMatch(x -> n % x == 0);
	}
	public static int[] twinPrimes(int n){
		return IntStream.rangeClosed(3, n).filter(x -> isPrime(x) && (isPrime(x + 2) || isPrime(x - 2))).toArray();
	}

	public static int gcd(int m, int n){
		int big = Math.max(m, n);
		int small = Math.min(m, n);
		
		Pair p = new Pair(big, small);

		return Stream.iterate(p, x -> x.getSecond() != 0, x -> new Pair(x.getSecond(), x.getFirst() % x.getSecond())).reduce(p, (a, b) -> b).getSecond();

	}
	
	public static long countRepeats(int... array){
		IntStream s = Arrays.stream(array);
		return s.mapToObj(x -> new Pair(x, 0))
            .reduce((a, b) -> (a.getFirst() == b.getFirst())
                    ? a.appeared()
                        ? new Pair(b.getFirst(), a.getSecond(), true)
                        : new Pair(b.getFirst(), a.getSecond() + 1, true)
                    : new Pair(b.getFirst(), a.getSecond(), false))
					.get()
					.getSecond();
	}
	
	public static double normalizedMean(Stream<Integer> stream){
		Data data = stream.map(x -> new Data(x, x, 1, x))
					.reduce((a, b) -> new Data (Math.max(a.max, b.max), Math.min(a.min,b.min), a.count + b.count, a.sum + b.sum))
					.orElse(new Data(0, 0, 0, 0));
					
		return data.max == data.min || data.count == 0
				? 0
				: (data.sum / data.count - data.min) / (data.max - data.min);
	}
	
	
}

class Data{
	final int max;
	final int min;
	final int count;
	final double sum;
	
	Data(int max, int min, int count, double sum){
		this.max = max;
		this.min = min;
		this.count = count;
		this.sum = sum;
	}
	
	
}

class Pair{
	private final int first;
	private final int second;
	private final boolean appeared;
	
	Pair(int first, int second){
		this.first = first;
		this.second = second;
		this.appeared = false;
	}
	
	Pair(int first, int second, boolean appeared){
		this.first = first;
		this.second = second;
		this.appeared = appeared;
	}
	
	public int getFirst(){
		return first;
	}
	
	public int getSecond(){
		return second;
	}
	
	public boolean appeared(){
		return appeared;
	}
	
}
