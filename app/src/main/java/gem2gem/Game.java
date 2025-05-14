package gem2gem;

import java.util.Random;

public class Game
{
	public static final int BOARD_SIZE = 10;

	public static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
	public static final char EMPTY = '=';

	public static Random rand = new Random();

	public Game(){
		this.board = new char[BOARD_SIZE][BOARD_SIZE];
	}

	// Facade
	// This method is the entry point for the game
	public void start()
	{
		System.out.println("Game started");
		createBoard();
		printBoard();
	}

	public static void createBoard() {

		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				char c;
				do{
					c = generateNewValue();
					board[row][col] = c;
				}
				while(isNotValid(row,col,c));
			}
		}
	}

	public static char generateNewValue() {
		return (char)('1' + rand.nextInt(5));
	}

	public static boolean isNotValid(int row, int col,char c){
		return (col >= 2 && board[row][col - 1] == c && board[row][col - 2] == c) ||
			(row >= 2 && board[row - 1][col] == c && board[row - 2][col] == c);
	}

	public static void printBoard()
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
	
	public static void updateBoard()
	{
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++)
			{
				if (board[i][j] == EMPTY)
				{
					board[i][j] = generateNewValue();
				}
			}
		}
	}
}