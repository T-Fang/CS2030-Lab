public class LongerThan implements BooleanCondition<String>{
	private final int x;

	public LongerThan(int x){
		this.x = x;
	}

	@Override
	public boolean test(String other){
		if(other == null) return false;
		else {
			int len = other.length();
			return len > x;
		}

	}
}
