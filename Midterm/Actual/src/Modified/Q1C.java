   import java.util.ArrayList;
   import java.util.List;

   class A{
      private final List<Object> list;
	  String head;

     public A(String h){
         this.list = new ArrayList<Object>();
		 head = h;
      }

      private A(List<Object> list, String h){
         this.list = list;
		 head = h;
      }
      public A add(Object b){
         List<Object> newList = new ArrayList<Object>(list);
         newList.add(b);
          return new A(newList, head);
      }
		
      @Override
      public String toString(){
		  String x = head;
		  for(Object o: list) x += o;
		  return x;
      }
   }

   class B extends A{
	   public B() { super("B"); }
		public String toString(){
			return "B";
		}
   }
   class C extends A{
	   public C() { super("C"); }
	   public String toString(){
		   return "C";
	   }
   }
