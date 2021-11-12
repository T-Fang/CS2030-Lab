   class X{
      int x;

      public X(int x){
         this.x = x;
      }

      @Override
      public String toString(){
         return "X : " + x;
      }
   }

   class B{
      A a;

      public B(A a){
         this.a = a;
      }

      @Override
      public String toString(){
         return "Y -> " + a;
      }

   }
