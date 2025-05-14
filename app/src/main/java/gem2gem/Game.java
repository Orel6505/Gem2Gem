package gem2gem;

import java.util.Random;
import java.util.ArrayList;

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

	public static void pullDownAllGems() {
    for (int x = 0; x < BOARD_SIZE; x++) {
        char[] gemsInColumn = new char[BOARD_SIZE];
        int count = 0;
     
        for(int y = 0; y < BOARD_SIZE; y++) {
            if (board[x][y] != EMPTY) {
                gemsInColumn[count++] = board[x][y];
            }
        }
        for(int y = 0; y < BOARD_SIZE - count; y++) {
            board[x][y] =  EMPTY;
        }
        for(int y = 0; y < count; y++) {
            board[x][BOARD_SIZE - count + y] = gemsInColumn[y];
        }
    }
	}

	public ArrayList<ArrayList<gem2gem.Position>> find_matching_gems(){
    ArrayList<ArrayList<gem2gem.Position>> match = new ArrayList<ArrayList<gem2gem.Position>>();
    for(int r = 0; r < 10; r++){
        for(int c = 0; c<10; c++){

            if (c+2<10 &&board[r][c]==board[r][c+1]&& board[r][c+1] ==board[r][c+2]){
                ArrayList<gem2gem.Position> row = new ArrayList<gem2gem.Position>();
                int count = 0;
                while( c + count < 10 && board[r][c] ==board[r][c+count]){
                        row.add(new Position(r, c + count));
                        count += 1;
                match.add(row);
                }

            }

            if (r+2 < 10 && board[r][c] == board[r+1][c] &&board[r+1][c]== board[r+2][c]){
                ArrayList<gem2gem.Position> row = new ArrayList<gem2gem.Position>();
                int count = 0;
                while( r + count < 10 && board[r][c] ==board[r+count][c]){
                    row.add(new Position(r, c + count));
                    count += 1;
                match.add(row);
                }
            }
        }
    }
    return match;
   }
   public void remove(){
	ArrayList<ArrayList<gem2gem.Position>> match = find_matching_gems();
	for (ArrayList<gem2gem.Position> row : match) {
		for (gem2gem.Position pos : row) {
			board[pos.getX()][pos.getY()] = EMPTY;
		}
	}
	pullDownAllGems();
	updateBoard();



   }
}

