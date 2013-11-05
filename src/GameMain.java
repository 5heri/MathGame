import java.util.Random;
import java.util.Scanner;

public class GameMain {

	private static final int EASY_DIFF = 11;

	public static void main(String[] args) {

		Game game = new Game();
		Random random = new Random();
		Scanner in = new Scanner(System.in);
		
		System.out.println("PUT YOUR MATH SKILLS TO THE TEST!");
		System.out.println("Enter 1 to for less than");
		System.out.println("Enter 2 to for equal to");
		System.out.println("Enter 3 to for greater than");
		System.out.println("Note: Any incorrect inputs\n have the same " +
				"impact as getting\n an answer wrong.");

		while (!game.isOver()) {

			int leftLeftHS = random.nextInt(EASY_DIFF);
			int leftRightHS = random.nextInt(EASY_DIFF);
			Operation opr = game.opSelect(leftLeftHS, leftRightHS);
			char oprDisplay = game.oprDisplay(opr);

			int correctRHS = game.getCorrectRHS(leftLeftHS, leftRightHS, opr);
			int incorrectRHS = game.change(correctRHS);

			System.out.println("---------------------------------");
			System.out.println("Player1's score: "
					+ game.getPlayer1().getScore());
			System.out.println("Player2's score: "
					+ game.getPlayer2().getScore());
			System.out.println("Current Equation:");
			System.out.println(Integer.toString(leftLeftHS) + oprDisplay
					+ Integer.toString(leftRightHS) + "  ??  "
					+ Integer.toString(incorrectRHS));
			System.out.println();

			System.out.println("Player" + game.getCurrentPlayer() + "'s turn.");
			int playerValue = in.nextInt();
			game.takeTurn(playerValue, opr, correctRHS, incorrectRHS);
			game.swapPlayers();

		}
	}

}
