import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BinaryOperator;

public abstract class InfiniteListImpl<T> implements InfiniteList<T>{
	public static <T> InfiniteListImpl<T> generate(Supplier<? extends T> supplier){
		return new InfiniteListImpl<T>(){
			@Override
			public Optional<T> get(){
				return Optional.of(supplier.get());
			}
		};
	}

	public static <T> InfiniteListImpl<T> iterate(T seed, UnaryOperator<T> f){
		return new InfiniteListImpl<T>() {
			private T element = seed;
			private boolean firstElement = true;

			@Override
			public Optional<T> get(){
				if(firstElement){
					firstElement = false;
				} else {
					element = f.apply(element);
				}
				return Optional.of(element);

			}
		};
	}
	
	@Override
	public InfiniteList<T> limit(long maxSize){
		if(maxSize < 0){
			throw new IllegalArgumentException("" + maxSize);
		} else {
			return new InfiniteListImpl<T>(){
				private long limit = maxSize;
				private int count = 0;
				
				@Override
				public Optional<T> get(){
					// because this is infinite, there is no need to check whether it has next element
					if(count >= limit) {
						return Optional.empty();
					} else {
						count++;
						return InfiniteListImpl.this.get();
					}
				}
			};
		}
	}
	
	@Override
	public void forEach(Consumer<? super T> action){
		Optional<T> curr = get();
		while(curr.isPresent()){
			T e = curr.get();
			action.accept(e);
			curr = get();
		}
	}
	
	@Override
	public Object[] toArray(){
		Optional<T> curr = get();
		ArrayList<T> lst = new ArrayList<>();
		while(curr.isPresent()){
			T e = curr.get();
			lst.add(e);
			curr = get();
		}
		return lst.toArray();
	}
	
	@Override
	public <S> InfiniteList<S> map(Function<? super T, ? extends S> mapper){
		return new InfiniteListImpl<S>(){
			@Override
			public Optional<S> get(){
				return InfiniteListImpl.this.get().map(mapper);
			}	
			
		};
	}
	
	@Override
	public InfiniteList<T> filter(Predicate<? super T> pred) {
		return new InfiniteListImpl<T>(){
			@Override 
			public Optional<T> get(){
				Optional<T> curr = InfiniteListImpl.this.get();
				while(curr.isPresent() && !pred.test(curr.get())){
					curr = InfiniteListImpl.this.get();
				}
				return curr;
			} 
		};
	}
	
	@Override
	public InfiniteList<T> takeWhile(Predicate<? super T> pred){
		return new InfiniteListImpl<T>(){
			@Override
			public Optional<T> get(){
				Optional<T> curr = InfiniteListImpl.this.get();
				if(curr.isEmpty() || !pred.test(curr.get())) {
					return Optional.empty();
				} else {
					return curr;
				}
				
			}	
		};
	}
	
	@Override
	public long count(){
		long count = 0;
		Optional<T> curr = get();
		while(curr.isPresent()){
			count++;
			curr = get();
		}
		return count;
	}
	
	@Override
	public Optional<T> reduce(BinaryOperator<T> accumulator){
		Optional<T> curr = get();
		if(curr.isEmpty()){
			return Optional.empty();
		} else {
			T e1 = curr.get();
			T e2;
			T result = e1;
			curr = get();
			while(curr.isPresent()){
				e2 = curr.get();
				result = accumulator.apply(result, e2);
				curr = get();
			}
			return Optional.of(result);
		}
	}
	
	@Override
	public T reduce(T identity, BinaryOperator<T> accumulator){
		Optional<T> curr = get();
		T result = identity;
		
		while(curr.isPresent()){
			T e = curr.get();
			result = accumulator.apply(result, e);
			curr = get();
		}
		
		return result;
	}
	
	
}
