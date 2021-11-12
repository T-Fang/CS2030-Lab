   String pattern(int n) {
      return IntStream.rangeClosed(1, n).map(x -> IntStream.iterate(x, x>0, x-> x-1).reduce(0, (a,b) -> a *10 + b)).forEach(System.out::println);
   }
