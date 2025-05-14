package gem2gem;

import java.util.Random;

import gem2gem.item.Item;

public class Game
{
	public static final int BOARD_SIZE = 10;
	private Item[][] board;

	public static final Random rand = new Random();

	public Game(){
		this.board = new Item[BOARD_SIZE][BOARD_SIZE];
	}

	// Facade
	// This method is the entry point for the game
	public void start()
	{
		System.out.println("Game started");
		createBoard();
		printBoard();
	}

	public void createBoard() {
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				Item c = (char) ('1' + rand.nextInt(5));
				this.board[row][col] = c;
			}
		}
	}

	public void printBoard()
	{
		if (board == null) return;

		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++)
			{
				System.out.print(" " + board[i][j] + " ");
			}
			System.out.println();
		}
	}
}