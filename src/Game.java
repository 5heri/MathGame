import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Game {

	private static final int SCORE_TO_WIN = 10;
	private static final int START_PLAYER = 1;
	private static final int SECOND_PLAYER = 2;
	private static final int CHANGE_PLAYER = 3;
	private static final int EASY_DIFF = 11;
	private static final char ADD = '+';
	private static final char SUB = '-';
	private static final char MULT = '*';
	
	private Player player1;
	private Player player2;
	private int currentPlayer;
	private Map<Integer, Equality> values;
	private Map<Integer, Operation> operations;
	private Map<Integer, Change> changes;
	
	public Game() {
		player1 = new Player();
		player2 = new Player();
		currentPlayer = START_PLAYER;
		values = new HashMap<Integer, Equality>();
		operations = new HashMap<Integer, Operation>();
		changes = new HashMap<Integer, Change>();
		values.put(1, Equality.LESS_THAN);
		values.put(2, Equality.EQUAL_TO);
		values.put(3, Equality.GREATER_THAN);
		operations.put(0, Operation.ADD);
		operations.put(1, Operation.MULT);
		operations.put(2, Operation.SUB);
		changes.put(0, Change.INC);
		changes.put(1, Change.DEC);
		changes.put(2, Change.NOCHANGE);
		
	}
	
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	
	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2() {
		return player2;
	}
	
	public void swapPlayers() {
		currentPlayer = CHANGE_PLAYER - currentPlayer;
	}
	
	public boolean isOver() {
		return player1.getScore() >= SCORE_TO_WIN 
				|| player2.getScore() >= SCORE_TO_WIN;
	}
	
	public int getLeadingPlayer() {
		if (player1.getScore() > player2.getScore()) {
			return START_PLAYER;
		} else {
			return SECOND_PLAYER;
		}
	}
	
	public int getCorrectRHS(int leftLeftHS, int leftRightHS, Operation opr) {
		int correctRHS;
		
		if (opr == Operation.ADD) {
			correctRHS = leftLeftHS + leftRightHS;
		} else if (opr == Operation.MULT) {
			correctRHS = leftLeftHS * leftRightHS;
		} else {
			correctRHS = leftLeftHS - leftRightHS;
		}
		return correctRHS;
	}
	
	public void takeTurn(int value, Operation opr, int correctRHS, int incorrectRHS) {
		
		Equality playerAns = values.get(value);
		
		Board turn = new Board(correctRHS, incorrectRHS);
		Equality correctAns = turn.equalityCheck();
		
		if (playerAns == correctAns) {
			if (currentPlayer == START_PLAYER) {
				player1.incScore();
			} else {
				player2.incScore();
			}
		} else {
			if (currentPlayer == START_PLAYER) {
				player1.decScore();
			} else {
				player2.decScore();
			}
		}	
	}
	
	public Operation opSelect(int left, int right) {
		Random rand = new Random();
		if (left < right) {
			int randomV = rand.nextInt(2);
			return operations.get(randomV);
		} else {
			int randomV = rand.nextInt(3);
			return operations.get(randomV);
		}
	}
	
	public int change(int value) {
		
		int result = value;
		Random random = new Random();
		int rand1 = random.nextInt(3);
		Change changeReq = changes.get(rand1);
		int rand2 = random.nextInt(EASY_DIFF);
		
		if (changeReq == Change.INC) {
			return result + rand2;
		} else if (changeReq == Change.DEC) {
			return result - rand2;
		} else {
			return result;
		}
	}
	
	public char oprDisplay(Operation opr) {
		if (opr == Operation.ADD) {
			return ADD;
		} else if (opr == Operation.MULT) {
			return MULT;
		} else {
			return SUB; 
		}
	}
	
}
