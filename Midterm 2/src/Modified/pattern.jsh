   String pattern(int n) {
      return IntStream.rangeClosed(1, n).map(x -> IntStream.iterate(x, y->y>0, y-> y-1).reduce(0, (a,b) -> a *10 + b)).mapToObj(Integer::toString).reduce("", (x,y) -> x+y+"\n");
   }
