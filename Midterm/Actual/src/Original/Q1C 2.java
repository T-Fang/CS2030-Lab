   import java.util.ArrayList;
   import java.util.List;

   class B{
      private final List<Object> list

     public B(){
         this.list = new ArrayList<Object>();
      }

      private B(List<Object> list){
         this.list = list;
      }
      public B add(Object b){
         List<Object> newList = new ArrayList<Object>();
         newList.add(b);
          return new B(newList);
      }

      @Override
      public String toString(){

      }
   }

   class A{
      private final List<Object> list;

   }
