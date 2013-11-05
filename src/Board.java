
public class Board {
	
	private int leftHS;
	private int rightHS;
	
	public Board(int leftHS, int rightHS) {
		this.leftHS = leftHS;
		this.rightHS = rightHS;
	}
	
	public Equality equalityCheck() {
		
		if (leftHS < rightHS) {
			return Equality.LESS_THAN;
		} else if (leftHS > rightHS) {
			return Equality.GREATER_THAN;
		} else {
			return Equality.EQUAL_TO;
		}
	}
	
}
