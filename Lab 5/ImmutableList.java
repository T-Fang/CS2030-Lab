import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;

public class ImmutableList<T> {
	private final List<T> list;

	
	public ImmutableList(List<T> list){
		List<T> temp = new ArrayList<>(list);
		this.list = temp;
	}
	
	@SafeVarargs
	public ImmutableList(T... list){
		List<T> temp = new ArrayList<>();
		for(T t: list){
			temp.add(t);
		}
		this.list = temp;
	}

	public ImmutableList<T> add(T t){
		List<T> temp = new ArrayList<>(list);
		temp.add(t);
		return new ImmutableList<T>(temp);
	}

	public ImmutableList<T> replace(T original, T replacement){
		List<T> temp = new ArrayList<>(list);
		temp.replaceAll(t -> (t == original)? replacement : t);
		return new ImmutableList<T>(temp);
	}

	public ImmutableList<T> remove(T t){
		List<T> temp = new ArrayList<>(list);
		temp.remove(t);
		return new ImmutableList<T>(temp);
	}

	public ImmutableList<T> filter(Predicate<? super T> pred){
		List<T> temp = new ArrayList<>();
		for(T t: list){
			if(pred.test(t)){
				temp.add(t);
			}
		}
		// alternatively
		// List<T> temp = new ArrayList<>(list);
		// temp.removeIf(x -> !pred.test(x));
		
		return new ImmutableList<T>(temp);
	}

	public <R> ImmutableList<R> map(Function<? super T, ? extends R> func){
		List<R> temp = new ArrayList<>();
		for(T t: list){
			temp.add(func.apply(t));
		}
		return new ImmutableList<R>(temp);
	}
	
	public ImmutableList<T> limit(long limit){
		int size = list.size();
		if(limit >= size){
			return new ImmutableList<T>(list);
		} else if(limit < 0){
			throw new IllegalArgumentException("limit size < 0");
		} else {
			// alternatively
			// List<T> temp = new ArrayList<>(list);
			// temp.subList(0, (int) limit);
			
			List<T> temp = new ArrayList<>();
			for(int i = 0; i < limit; i++){
				temp.add(list.get(i));
			}
			return new ImmutableList<T>(temp);
		}

	}

	public ImmutableList<T> sorted(){
		if(list.size() == 0){
			return new ImmutableList<T>(list);
		} else if(list.get(0) instanceof Comparable){
			@SuppressWarnings("unchecked")
			T[] arr = (T[]) list.toArray();
            Arrays.sort(arr);
			List<T> temp = new ArrayList<T>(Arrays.asList(arr));
            return new ImmutableList<T>(temp);
		} else {
			throw new IllegalStateException("List elements do not implement Comparable");
		}
	}

	public ImmutableList<T> sorted(Comparator<T> cmp){
		if(cmp == null){
			throw new NullPointerException("Comparator is null");
		} else {
			List<T> temp = new ArrayList<>(list);
			Collections.sort(temp, cmp);
			return new ImmutableList<T>(temp);
		}
	}

	public Object[] toArray(){
		return list.toArray();
	}
	
	public <U> U[] toArray(U[] inputArr){
		if(inputArr == null){
			throw new NullPointerException("Input array cannot be null");
		} else {
			try{
				list.toArray(inputArr);
			} catch(ArrayStoreException e){
				throw new ArrayStoreException("Cannot add element to array as it is the wrong type");
			}
			return list.toArray(inputArr);
		}
	}

	@Override
	public String toString(){
		return list.toString();
	}


}
