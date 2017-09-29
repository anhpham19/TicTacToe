/**
 * The view of the Tic Tac Toe game
 * @author Anh Chau Pham
 * @version Oct 22nd
 *
 */
public class TTTView 
{
	//The board object from the game
	private TTTModel model;
	
	/**
	 * constructor initializes the board array
	 * @param model the model
	 */
	public TTTView(TTTModel model){
		this.model = model;
	}
	
	/**
	 * Print the board to the console
	 */
	public void printBoard()
	{
		// get a new board from the model
		int [][] newBoard = model.getBoard();		
		
		// loop through the rows and columns board to print the current state of the board
		// check if the board is occupied by player X, or player O, or neither
		for (int row = 0; row < newBoard.length; row++)
		{
			for (int col = 0; col < newBoard.length; col++)
			{
				// print the current state of each "cell" of the board
				printCell(newBoard[row][col]);
				
				// We only need to print board size - 1 "|"
				if (col != newBoard.length-1)
				{
					System.out.print("|");
				}
			}
			System.out.println();
			
			// We only need to print board size - 1 "-----"
			if (row != newBoard.length-1)
			{
				System.out.println("-----");
			}
		}
	}
	
	/**
	 * Print the current state of each "cell" of the board
	 * @param number 1 represents first player, 2 represents second player and 0 represents empty cell
	 */
	public void printCell (int number)
	{
		switch(number)
		{
		case 0: System.out.print(" ");
		break;
		case 1: System.out.print("X");
		break;
		case 2: System.out.print("O");
		break;
		}
	}
	
	/** 
	 * Print out the instruction/ help information to the console
	 */
	public void printHelp()
	{
		System.out.println("Instructions: You will play on a 3 by 3 game board. The first player places X and the second player places O.");
		System.out.println ("Players places X and O alternatively on the board and X always goes first. ");
		System.out.println ("The first player to get 3 marks in a row (horizontally, vertically or diagonally is the winner. ");
		System.out.println( "When the 9 squares are full, the game is over and the board is reset. ");
		System.out.println( "The location of each grid in the board is based on the phone keyboard layout. ");
		System.out.println("The first row is 1,2,3; second row is 4,5,6 and third row is 7,8,9.");	
	}
}


