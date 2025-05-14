package gem2gem;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Game
{
	public static final int BOARD_SIZE = 10;

	public char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
	public static final char EMPTY = '=';

	public static final Random rand = new Random();

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
				char c;
				do{
					c = generateNewValue();
					board[row][col] = c;
				}
				while(isNotValid(row,col,c));
			}
		}
	}

	public char generateNewValue() {
		return (char)('1' + rand.nextInt(5));
	}

	public boolean isNotValid(int row, int col,char c){
		return (col >= 2 && board[row][col - 1] == c && board[row][col - 2] == c) ||
			(row >= 2 && board[row - 1][col] == c && board[row - 2][col] == c);
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
	
	public void updateBoard()
	{
		for(int i = 0; i < board.length; i++)
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

	private boolean isWithinBoard(int row, int col) {
		return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
	}

	private ArrayList<Position> findHorizontalMatch(int row, int col) {
		ArrayList<Position> result = new ArrayList<>();
		char gem = board[row][col];
		int currentCol = col;
		while (isWithinBoard(row, currentCol) && board[row][currentCol] == gem) {
			result.add(new Position(row, currentCol));
			currentCol++;
		}
		return result;
	}

	private ArrayList<Position> findVerticalMatch(int row, int col) {
		ArrayList<Position> result = new ArrayList<>();
		char gem = board[row][col];
		int currentRow = row;
		while (isWithinBoard(currentRow, col) && board[currentRow][col] == gem) {
			result.add(new Position(currentRow, col));
			currentRow++;
		}
		return result;
	}

	public List<ArrayList<Position>> findMatchingGems() {
		ArrayList<ArrayList<Position>> matches = new ArrayList<>();
		boolean[][] visited = new boolean[BOARD_SIZE][BOARD_SIZE];

		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				if (col + 2 < BOARD_SIZE && !visited[row][col]) {
					ArrayList<Position> horizontalMatch = findHorizontalMatch(row, col);
					if (horizontalMatch.size() >= 3) {
						matches.add(horizontalMatch);
						for (Position pos : horizontalMatch) {
							visited[pos.getX()][pos.getY()] = true;
						}
					}
				}
				if (row + 2 < BOARD_SIZE && !visited[row][col]) {
					ArrayList<Position> verticalMatch = findVerticalMatch(row, col);
					if (verticalMatch.size() >= 3) {
						matches.add(verticalMatch);
						for (Position pos : verticalMatch) {
							visited[pos.getX()][pos.getY()] = true;
						}
					}
				}
			}
		}
		return matches;
	}

	private char[] extractNonEmptyGemsFromColumn(int col) {
		char[] temp = new char[BOARD_SIZE];
		int count = 0;
		for (int row = 0; row < BOARD_SIZE; row++) {
			if (board[row][col] != EMPTY) {
				temp[count++] = board[row][col];
			}
		}
		char[] gems = new char[count];
		System.arraycopy(temp, 0, gems, 0, count);
		return gems;
	}

	private void fillColumnWithGems(int col, char[] gems) {
		int count = gems.length;
		for (int row = 0; row < BOARD_SIZE - count; row++) {
			board[row][col] = EMPTY;
		}
		for (int row = BOARD_SIZE - count; row < BOARD_SIZE; row++) {
			board[row][col] = gems[row - (BOARD_SIZE - count)];
		}
	}

	public void pullDownAllGems() {
		for (int col = 0; col < BOARD_SIZE; col++) {
			char[] gems = extractNonEmptyGemsFromColumn(col);
			fillColumnWithGems(col, gems);
		}
	}

	public void remove() {
		List<ArrayList<Position>> matches = findMatchingGems();
		for (ArrayList<Position> match : matches) {
			for (Position pos : match) {
				board[pos.getX()][pos.getY()] = EMPTY;
			}
		}
		pullDownAllGems();
		updateBoard();
	}
}
