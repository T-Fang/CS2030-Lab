   import java.util.*;

   interface TypeCaster<T, S>{
      public T cast(S s);
   }

   class ToString<S> implements TypeCaster<String, S>{
      public String cast(S s){
         return s.toString();
      }
   }

   class Round implements TypeCaster<Integer, Double>{
      public Integer cast(Double d){
         return (int) Math.round(d);
      }
   }

   class ToList<T> implements TypeCaster<List<T>, T[]>{
      public List<T> cast(T[] arr){
         List<T> list = Arrays.asList(arr);
         return list;
      }
   }

   class ListCaster{
      public static <S, T> List<T> castList(List<S> input, TypeCaster<T,
   S> tc){
          List<T> result = new ArrayList<>();
           for(S s: input){
              result.add(tc.cast(s));
            }
         return result;
      }
   }
