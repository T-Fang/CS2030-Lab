import java.util.ArrayList;
import java.util.List;

public class D {
	
    public static <T> List<T> add(List<T> list, T t) {
        list.add(t);
        return list;
    }
	
    public static <T> List<T> join(List<T> list1, List<? extends T> list2) {
        List<T> lst = new ArrayList<>();
		
        if (list1 == list2) {
            for (T t: list1) lst.add(t);
        } else {
			for (T t: list1) lst.add(t);
	        for (T t: list2) lst.add(t);
        }
   		return lst;
    }
}

