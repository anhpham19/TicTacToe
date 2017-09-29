/**
 * This classes makes change to the TTT model and view.
 * It controls the data flow into model object and updates the view.
 * It keeps view and model separate.
 * 
 * @author Anh Chau Pham
 * @version Oct 22nd
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TTTController 
{

	//instance of the TTTModel class. We will control it from here
	private TTTModel game;;

	//view class instance. Pass it the board
	private TTTView view;

	/**
	 * Constructor: starts the game when run
	 */
	public TTTController() {
		game = new TTTModel();
		view = new TTTView(game);
		//begin game
		playGame();
	}

	/**
	 * Get input from the user, looping until the user types "Quit"
	 * Handles unexpected values with exceptions
	 **/
	private void playGame()
	{
		// Buffered readers are used to read text input from the command line
		BufferedReader in = 
			new BufferedReader( new InputStreamReader( System.in ) );

		// Prompt the user for input
		System.out.println( "Tic Tac Yo! \n X starts! Type 'help' for instructions! "  ); 

		//Used to hold what the user inputs
		String line = "";
		
		// Bufferedreaders require a try/catch block, to prevent exceptions from crashing
		// your program
		try 
		{

			System.out.println("Enter a location for your piece (1-9)");
			// Try to read a line
			// This function potentially throws an IOException
			line = in.readLine(); 

			// Loop until the user types "quit"
			while ( !line.toLowerCase().equals( "quit" )  ) {

				//If the user types "help", print out help
				if (line.toLowerCase().equals( "help" ))
				{
					view.printHelp();	
				}
				else 
				{
					//Otherwise, check to make sure they input a number between 1 and 9
					//Needs another try/catch in case a number wasn't input
					try {
						int input = Integer.parseInt(line);
						if (input > 0 && input < 10) {
							playRound(Integer.parseInt(line));
						}
						else
						{
							System.out.println("Error: Numbers must be 1-9. Enter help for help.");
						}
					}
					catch (NumberFormatException e) 
					{
						System.out.println("Error: That wasn't a number.");
					}
				}

				//Ask for the next input
				line = in.readLine(); 
			}
		} 
		
		// catch I/O exception
		catch ( IOException ioenfe )
		{
			//inform user of problem
			System.out.println("Error: IOException. ");
		}
	}

	/**
	 * Plays a round, testing if the input is valid, if the game was won or drawn, 
	 * and prints out the state of the board
	 * @param loc
	 */
	private void playRound(int loc)
	{
		//Play a round in the model
		boolean validMove = game.attemptMove(loc);

		//Update the view
		view.printBoard();

		//If the move wasn't valid, print out a message
		if( !validMove )
		{
			System.out.println("Invalid move; check the free spaces and try again!");
			return;
		}

		//If the game is won, print out a message and reset the game
		else if (game.isGameWon())
		{
			//Get the current player who is also winning the game
			String currentPlayer = game.getCurrentPlayer();
			
			//Print out the winner and reset the game
			if ( currentPlayer == "X")
			{
				System.out.println("Player X wins! Resetting the board...");
				game = new TTTModel();
				view = new TTTView(game);
				view.printBoard();
			}
			else if (currentPlayer == "O")
			{
				System.out.println("Player O wins! Resetting the board...");
				game = new TTTModel();
				view = new TTTView(game);
				view.printBoard();
			}	
		}

		//If the game is drawn, print out a message and reset the game
		else if (game.isGameOver())
		{
			//Reset the game with a new board
			System.out.println("No one won. Resetting the board...");
			game = new TTTModel();
			view = new TTTView(game);
			view.printBoard();
		}

		//Otherwise, end the round and swap players
		else
		{
			game.endRound();
		}
		
		//print the current player to the console
		System.out.println("It is now player " + game.getCurrentPlayer() + "'s turn");
	}
}