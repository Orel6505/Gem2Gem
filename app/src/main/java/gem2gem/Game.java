package gem2gem;

import java.util.Random;

public class Game
{
	public static final int BOARD_SIZE = 10;

	public static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

	public static void createBoard() {
		Random rand = new Random();

		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				char c = (char) ('1' + rand.nextInt(5));

				board[row][col] = c;
				while(isNotValid(row,col,c)){
					c = (char) ('1' + rand.nextInt(5));
					board[row][col] = c;
				}

			}
		}
	}

		public static boolean isNotValid(int row, int col,char c){
			return (col >= 2 && board[row][col - 1] == c && board[row][col - 2] == c) ||
			 (row >= 2 && board[row - 1][col] == c && board[row - 2][col] == c);
		}


	public static void printBoard()
	{
		if (board == null)
			System.out.println("Null board");
		else
		{
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
	
	public static void updateBoard()
	{
		if (board == null)
		{
			board = new char[BOARD_SIZE][BOARD_SIZE];
			
			for (int i = 0; i < board.length; i++)
			{
				for (int j = 0; j < board[i].length; j++)
				{
					board[i][j] = '=';
				}
			}
		}
	}
}