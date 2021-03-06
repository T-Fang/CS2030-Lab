import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class InfiniteListImpl<T> implements InfiniteList<T> {
	private final Lazy<T> head;
	private final Supplier<InfiniteList<T>> tail;
	
	InfiniteListImpl(Lazy<T> head, Supplier<InfiniteList<T>> tail){
		this.head = head;
		this.tail = tail;
	}
	
	public static <T> InfiniteList<T> generate(Supplier<? extends T> supplier){
		Lazy<T> newHead = Lazy.generate(supplier);
		Supplier<InfiniteList<T>> newTail = () -> InfiniteListImpl.generate(supplier);
		return new InfiniteListImpl<T>(newHead, newTail);
	}
	
	public static <T> InfiniteList<T> iterate(T seed, UnaryOperator<T> next) {
		Lazy<T> newHead = Lazy.ofNullable(seed);
		Supplier<InfiniteList<T>> newTail = () -> InfiniteListImpl.iterate(next.apply(seed), next);
		return new 	InfiniteListImpl<T>(newHead, newTail);
	}
	
	@Override
	public InfiniteList<T> peek(){
		head.get().ifPresent(System.out::println);
		return tail.get();
	}
	
	@Override
	public <R> InfiniteList<R> map(Function<? super T, ? extends R> mapper){
		Lazy<R> newHead = head.map(mapper);
		Supplier<InfiniteList<R>> newTail = () -> tail.get().map(mapper);
		return new InfiniteListImpl<R>(newHead, newTail);
	}
	
	@Override
	public InfiniteList<T> filter(Predicate<? super T> predicate) {
		Lazy<T> newHead = head.filter(predicate);
		Supplier<InfiniteList<T>> newTail = () -> tail.get().filter(predicate);
		return new InfiniteListImpl<T>(newHead, newTail);
	}
	
	@Override
	public InfiniteList<T> limit(long n) {
		if(n < 1) {
			return new EmptyList<T>();
		} else {
			return new InfiniteListImpl<T>(head, () -> head.get().isEmpty()
												? tail.get().limit(n)
												: n == 1
													? ne1w EmptyList<>()
													: tail.get().limit(n - 1));
		}
	}
	
	@Override
	public InfiniteList<T> takeWhile(Predicate<? super T> predicate){
		Lazy<T> newHead = head.filter(predicate);
		Supplier<InfiniteList<T>> newTail = () ->
			head.get().isPresent() && newHead.get().isEmpty()
				? new EmptyList<>()
				: tail.get().takeWhile(predicate);
		return new InfiniteListImpl<T>(newHead, newTail);
	}
	
	@Override
    public void forEach(Consumer<? super T> action) {
		head.get().ifPresent(action);
		tail.get().forEach(action);
	}

    @Override
    public Object[] toArray() {
        ArrayList<T> list = new ArrayList<>();
		forEach(list::add);
		return list.toArray();
    }

    @Override
    public long count() {
		return head.get().isEmpty()
			? tail.get().count()
			: 1 + tail.get().count();
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator) {
        return tail.get().reduce(
			head.get().map(x -> accumulator.apply(identity, x)).orElse(identity),
			accumulator
		);
    }
	
	
	@Override
	public boolean isEmpty(){
		return false;
	}
	
}