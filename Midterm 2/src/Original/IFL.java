import java.util.function.Supplier;
import java.util.function.Predicate;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

class IFL<T> {
    Supplier<T> head;
    Supplier<IFL<T>> tail;
    /* FIELDS AND METHODS START: DO NOT REMOVE */

    /* FIELDS AND METHODS END: DO NOT REMOVE */

    IFL(Supplier<T> head, Supplier<IFL<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    static <T> IFL<T> of(List<? extends T> list) {
        /* OF START: DO NOT REMOVE!!! */
if(list.size() == 0) {
      return new IFL(()->null, ()->null);
   } else if (list.size() == 1){
      return new IFL(()-> list.get(0), () -> null);
   } else {
      return new IFL(()-> list.get(0), () -> IFL.of(list.sublist(1, list.size())));
   }
        /* OF END: DO NOT REMOVE!!! */
    }

    Optional<T> findMatch(Predicate<? super T> predicate) {
        /* FINDMATCH START: DO NOT REMOVE!!! */
if(head.get() == null) {
   return Optional.empty();   
} else if(tail.get() == null){
   if(predicate.test(head.get())){      return Optional.of(head.get());
   } else {
      return Optional.empty();
   }
} else {
   if(predicate.test(head.get())){
      return Optional.of(head.get());
   } else {
      return tail.get().findMatch(predicate);
   }
}
        /* FINDMATCH END: DO NOT REMOVE!!! */
    }
}

/* ADDITIONAL CODE START: DO NOT REMOVE */

/* ADDITIONAL CODE END: DO NOT REMOVE */
