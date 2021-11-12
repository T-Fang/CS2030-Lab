   import java.util.ArrayList;
   import java.util.List;
   class A{
      private final List<Integer> list;

      private A(List<Integer> list){
         this.list = new ArrayList<Integer>(list);
      }

      public A(int x){
         List<Integer> newList = new ArrayList<Integer>();
         newList.add(x);
         this.list = newList;
      }

      public A next(int x){
         List<Integer> newList = new ArrayList <Integer>(list);
         newList.add(x);
         return new A(newList);
      }

      @Override
      public String toString(){
         String result = "";
         for(Integer i: list){
            result += "[A:" + i  + "]";
         }
         return result;
      }
   }
