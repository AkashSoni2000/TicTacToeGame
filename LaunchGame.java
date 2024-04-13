import java.util.Random;
import java.util.Scanner;

class TicTacToe{
	
	static char[][] board;

//	declared the constructor
	
	public TicTacToe()
	{
	   board = new char[3][3];
	   inItBoard();
	}
	
//	to making board blank be call this method just after constructor execute
	
	void inItBoard()
	{
		for(int i=0; i<board.length; i++)
		{
			for(int j=0; j<board[i].length; j++)
			{
				board[i][j] = ' ';
			}
		}
	}
	
//	display board structure method 
	
	static void displayBoard() 
	{
		System.out.println("-------------");
		for(int i=0; i<board.length; i++)
		{
			System.out.print("| ");
			for(int j=0; j<board[i].length; j++)
			{
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}
	
//	method for palace the value in the cell to play game
	
	static void placeMark(int row, int col, char mark) 
	{
		if(row >= 0 && row <= 2 && col >= 0 && col <= 2) 
		{
			board[row][col] = mark;
		}else
		{
			System.out.println("Invalid input");
		} 
	}

//	method to check in column is win or not
	
	static boolean checkColWin() {
		for(int j = 0; j<=2; j++) 
		{
		   if(board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j])
		   {
			   return true;
		   }	
		}
		return false;
	}
	
//	method for check in row is win or not
	static boolean checkRowWin() {
		for(int i = 0; i<=2; i++) 
		{
		   if(board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
		   {
			   return true;
		   }	
		}
		return false;
	}
	
//	method for check in Diagonal is win or not 
	static boolean checkDiagWin()
	{
		if(board[0][0] != ' ' && board[0][0]==board[1][1] && board[1][1] == board[2][2]
				|| board[0][2] != ' ' && board[0][2]==board[1][1] && board[1][1] == board[2][0])
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	static boolean checkDraw() {
		for(int i=0; i<=2; i++) {
			for(int j=0; j<=2; j++) {
				if(board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}
}

// parents Class player is created 
abstract class Player{
	String name;
	char mark;
	
	abstract void makeMove();
	
	boolean isValidMove(int row, int col)
	{
		if(row >= 0 && row <= 2 && col >= 0 && col <= 2)
		{
			if(TicTacToe.board[row][col] == ' ')
			{
				return true;
			}
		}
		return false;
	}
}

//create the HumanPlayer class
class HumanPlayer extends Player{
	
	HumanPlayer(String name, char mark)
	{
		this.name = name;
		this.mark = mark;
	}
	
	void makeMove() 
	{
		Scanner sc = new Scanner(System.in);
		int row, col;
		do {
			System.out.println("Enter the Row and Column");
			 row = sc.nextInt();
			 col = sc.nextInt();
		}while(!isValidMove(row, col));
		
		TicTacToe.placeMark(row, col, mark);
	}
	
	
}


//AI class is created 
class AIPlayer extends Player{
	
	
	AIPlayer(String name, char mark)
	{
		this.name = name;
		this.mark = mark;
	}
	
	void makeMove() 
	{
		Scanner sc = new Scanner(System.in);
		int row, col;
		do {
			Random r = new Random();
			row = r.nextInt(3);
			col = r.nextInt(3);
		}while(!isValidMove(row, col));
		
		TicTacToe.placeMark(row, col, mark);
	}
	
}



public class LaunchGame {

	public static void main(String[] args) {
		
		TicTacToe t = new TicTacToe();
		
		HumanPlayer p1 = new HumanPlayer("Bob", 'X');
		AIPlayer p2 = new AIPlayer("Tai", 'O');
		
		Player cp;  // cp means current player
		cp = p1;
		
		while(true) {
			System.out.println(cp.name + " turn");
			cp.makeMove();
			TicTacToe.displayBoard();
			
			if(TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagWin()) {
				System.out.println(cp.name + " has Win");
				break;
			}
			else if(TicTacToe.checkDraw()) {
				System.out.println("Game is Draw");
				break;
			}
			else {
				if(cp == p1) {
					cp = p2;
				}else {
					cp = p1;
				}
			}
		}
	}	

}

