import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Predicate;

class Lazy<T>{
	private Optional<Optional<T>> v;
	final private Supplier<? extends T> s;

	
	Lazy(T v){
		this.v = Optional.ofNullable(Optional.ofNullable(v));
		this.s = () -> v;
	}
	
	Lazy(Supplier<? extends T> s){
		this.v = Optional.empty();
		this.s = s;
	} 
	
	static <T> Lazy<T> of(T v){
		return new Lazy<>(v);
	}
	
	
	static <T> Lazy<T> ofNullable(T v){
		return new Lazy<>(v);
	}
	
	static <T> Lazy<T> generate(Supplier<? extends T> supplier) {
		return new Lazy<>(supplier);
	}
	
	Optional<T> get(){
		this.v = this.v.or(() -> Optional.ofNullable(Optional.ofNullable(s.get())));
		return this.v.get();
	}
	
	
	<R> Lazy<R> map(Function<? super T, ? extends R> mapper){
		return Lazy.generate(() -> this.get().map(mapper).orElse(null));
	}
	
	<R> Lazy<R> flatMap(Function<? super T, ? extends Lazy<R>> mapper){
		return Lazy.generate(() -> this.get().map(x -> mapper.apply(x).orElse(null)).orElse(null));
	}
	
	<R, U> Lazy<R> combine(Lazy<U> lazy, BiFunction<T, U, R> func){
		return Lazy.generate(() -> this.get().map(x -> lazy.get().map(y -> func.apply(x, y)).orElse(null)).orElse(null));
	}
	
	Lazy<T> filter(Predicate<? super T> predicate) {
		return Lazy.generate(() -> this.get().filter(predicate).orElse(null));
	}
	
	
	@Override
	public String toString(){
		return this.v.map(x -> x.map(y -> y.toString()).orElse("null")).orElse("?");
	}
}
