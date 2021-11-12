import java.util.*;
public class Result<T>{
   Optional<T> value;
   Optional<String> errormsg;
   String type;
   
   Result(T value){
      this.value = Optional.of(value);
      this.errormsg = Optional.empty();
      this.type = "success";
   }
   
   Result(String errormsg) {
      this.value = Optional.empty();
      this.errormsg = errormsg;
      this.type = "failure";
   }
   public static Result<T> success(T value){
      return new Result<T>(value);
   }
 
   public static Result<T> error(String errorMessage){
      return new Result<T>(errorMessage);
   }
 
   public Result<T> filter(Predicate<? super T> predicate, String errormsg){
      if(type == "failure") {
         return this;
      } else {
         if(predicate.test(value.get())) {
            return this;
         }  else {
            return Result.error(errormsg);
         }
      }
   }
   
   public Result<R> flatMap(Function<? super T, ? extends Result<R>> mapper) {
      if(type == "failure") {
         return this;
      } else {
         try{
            return mapper.apply(value.get());       
         } catch(Exception e) {
            return Result.error(e.getMessage());
         }
      }
   }
   
   
   public orElseThrow(Exception e){
      if(type == "success") {
         return value.get();
      } else {
         throw e;
      }
   }
   @Override
   public String toString(){
      if(type == "success") {
         return "Success: " + value.toString();
      } else {
         return "Error: " + errormsg;
      }
   }
}
