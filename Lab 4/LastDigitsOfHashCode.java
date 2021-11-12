class LastDigitsOfHashCode implements Transformer  <Object, Integer>{
	private final Integer num;

	public LastDigitsOfHashCode(Integer num){
		this.num = num;
	}

	@Override
	public Integer transform(Object o){
		int hash = o.hashCode();
		return Math.abs((int) (hash % (Math.pow(10, num))));
	}

}
