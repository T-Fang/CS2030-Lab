   class X{
      int x;

      public X(int x){
         this.x = x;
      }

      @Override
      public String toString(){
         return "X:" + x;
      }
   }

   class Y{
      X a;

      public Y(X a){
         this.a = a;
      }

      @Override
      public String toString(){
         return "Y->" + a;
      }

   }
