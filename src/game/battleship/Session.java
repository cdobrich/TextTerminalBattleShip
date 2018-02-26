package game.battleship;

import game.battleship.grid.Grid;
import game.battleship.grid.GridTarget;
import game.battleship.grid.Positions;
import game.battleship.grid.objects.GridCell;
import game.battleship.grid.objects.Ship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.in;
import static java.lang.System.out;
import static java.lang.System.setOut;

/**
 * A game session consists of two players. Whenever a player's points drops to zero, the other player wins and the game is over.
 *
 * Architecture comments: The Session object knows more rules, such as allowing or disallowing striking previously attacked areas.
 */
public class Session
{
	Player player1, player2;
	Integer boardSize;
	Integer numberOfShips, offsetNumberOfShips;
	final Integer numberOfDirections = 4;
	ArrayList<Player> players = new ArrayList<>();

	private static final Logger log = Logger.getLogger(Session.class.getName());

	public Session( int boardSize )
	{
		this.boardSize = boardSize;
		player1 = new Player( boardSize,"Player");
		players.add(player1);
		player2 = new Player( boardSize,"Computer");
		players.add(player2);

		// Defaulting to use the player1's numbers, but should both be the same
		numberOfShips = player1.getNumberOfShips();
		offsetNumberOfShips = player1.getOffsetOfMyShipsList();
	}

	/**
	 *
	 * @param disallowStrikingPreviousMisses Flag to set whether to disallow Computer to strike previously targeted miss locations
	 */
	public void startComputerVsComputer( boolean disallowStrikingPreviousMisses, boolean showStepsOutput )
	{
		// Random choice for which player gots first
		int playerToGoFirstChoice = (int) ( Math.random() * 2 + 1);

		Player attackingPlayer;
		Player targetPlayer;
		Player firstPlayer, secondPlayer;
		if( playerToGoFirstChoice == 1 )
		{
			attackingPlayer = players.get( 0 );
			targetPlayer = players.get( 1 ); // Always start off the opponent as other player
		}
		else
		{
			attackingPlayer = players.get( 1 );
			targetPlayer = players.get( 0 ); // Always start off the opponent as other player
		}
		firstPlayer = attackingPlayer;
		secondPlayer = targetPlayer;


		// Populate the board pieces
		putShipPiecesOntoBoard( attackingPlayer );
		putShipPiecesOntoBoard( targetPlayer );
		displayBothPlayerGrids( attackingPlayer.getLabel(), targetPlayer.getLabel() );

		boolean gameIsRunning = true;
		while( gameIsRunning )
		{
			// Get a random location
			GridTarget randomTarget = generateRandomTargetPosition();

			if( disallowStrikingPreviousMisses && attackingPlayer.getLabel().contentEquals( "Computer" ) ) // prevent computer from hitting previous selection
			{
				while(true)
				{
					GridCell targetStrike = targetPlayer.getGridCell( randomTarget.getVertical(), randomTarget.getHorizontal() );

					if( targetStrike.isHit() ) // Check if already hit.
					{
						// This spot has been hit before so do not break the loop and allow it to generate another target
						randomTarget = generateRandomTargetPosition();
					}
					else
					{
						break; // Have not hit this one before so it may be used
					}
				}
			}


			GridCell targetStrike = targetPlayer.getGridCell( randomTarget.getVertical(), randomTarget.getHorizontal() );
			if( targetStrike.isHit() ) // Check if already hit.
			{
				// Already hit. Do nothing.
			}
			else
			{
				GridCell targetCell;
				if( targetStrike.isOccupied() ) // HIT
				{ // Note on their grid there was a hit
					targetCell = new GridCell(" X ");
					targetPlayer.decrementGamePoints(); // update other player's points
					if( showStepsOutput )
					{
						out.println( attackingPlayer.getLabel() + " hit on target '" + Positions.translateHorizontalNumberPositionToLetterLabel( randomTarget.getHorizontal() ) + randomTarget.getVertical() + "'" );
					}
				}
				else // MISS
				{
					targetCell = new GridCell(" . ");
					if( showStepsOutput )
					{
						out.println( attackingPlayer.getLabel() + " miss on target '" + Positions.translateHorizontalNumberPositionToLetterLabel( randomTarget.getHorizontal() ) + randomTarget.getVertical() + "'" );
					}
				}
				targetCell.setHit( true );
				Grid grid = targetPlayer.getGrid(); // adjust target's grid
				grid.setGridCell( targetCell, randomTarget.getVertical(), randomTarget.getHorizontal() );
				targetPlayer.setGrid( grid ); // Update the target grid
			}
			attackingPlayer.incrementTurns();

			// check whether there is a victor
			if( !gameRunning() )
			{
				gameIsRunning = false; // end the game
			}

			// Swap the players
			Player tempPlayer = attackingPlayer;
			attackingPlayer = targetPlayer;
			targetPlayer = tempPlayer;
		}

		displayBothPlayerGrids( firstPlayer.getLabel(), secondPlayer.getLabel() );
		Player winner = players.get( declareWinner() );
		out.println("'" + firstPlayer.getLabel() + "' went first." );
		out.println(winner.getLabel()+" is the winner!");
		out.println("  Winner points remaining: " + winner.getGamePoints());
		if( showStepsOutput )
		{
			out.println( player1.getLabel() + " took " + player1.getTurns() + " turns." );
			out.println( player2.getLabel() + " took " + player2.getTurns() + " turns." );
		}

	}

	/**
	 *
	 * @param disallowStrikingPreviousMisses Flag to set whether to disallow Computer to strike previously targeted miss locations
	 */
	public void startPlayerVsComputer( boolean disallowStrikingPreviousMisses, boolean showStepsOutput, boolean showGridEachRound )
	{
		Player humanPlayer;
		Player computerPlayer;
		humanPlayer = players.get( 0 );
		computerPlayer = players.get( 1 ); // Always start off the opponent as other player

		if( showStepsOutput )
		{
			out.println( "In Player vs Computer mode, the player always goes first." );
		}

		// Populate the board pieces
		putShipPiecesOntoBoard( humanPlayer );
		putShipPiecesOntoBoard( computerPlayer );
		displayBothPlayerGrids( humanPlayer.getLabel(), computerPlayer.getLabel() );

		boolean gameIsRunning = true;
		while( gameIsRunning )
		{
			//=======================================================================
			// Human Player section, targets human Computer's grid
			//=======================================================================

			// Ask user for input on location
			System.out.println();
			System.out.print( "  Enter target (example A1, D4, J9): " );
			try
			{
				BufferedReader is = new BufferedReader( new InputStreamReader( System.in ) );
				String inputline = is.readLine();

				// Simple regex splitting, putting the answer in the first index of each String array
				String[] posVerticalArray = inputline.substring( 1 ).split( "[a-jA-J]" );
				String[] posHorizontalLetterInputArray = inputline.split( "[1-9]+" );

				Character inputLetter = new Character( posHorizontalLetterInputArray[0].charAt( 0 ) );

				inputLetter = inputLetter.toUpperCase(inputLetter);

				Integer posHorizontal = Positions.translateLetterToHorizontal( inputLetter );

				GridTarget manualTarget = new GridTarget( new Integer( posVerticalArray[0] ), posHorizontal );
				out.println( "Targeting" + manualTarget );

				GridCell manualStrike = computerPlayer.getGridCell( manualTarget.getVertical(), manualTarget.getHorizontal() );

				if( manualStrike.isHit() ) // Check if already hit.
				{
					// Already hit. Do nothing. User is allowed to hit something already targeted.
				}
				else
				{
					GridCell targetCell;
					if( manualStrike.isOccupied() ) // HIT
					{ // Note on their grid there was a hit
						targetCell = new GridCell( " X " );
						computerPlayer.decrementGamePoints(); // update other player's points
						if( showStepsOutput )
						{
							out.println( humanPlayer.getLabel() + " hit on target '" + inputLetter + manualTarget.getVertical() + "'" );
						}
					}
					else // MISS
					{
						targetCell = new GridCell( " . " );
						if( showStepsOutput )
						{
							out.println( humanPlayer.getLabel() + " miss on target '" + inputLetter + manualTarget.getVertical() + "'" );
						}
					}
					targetCell.setHit( true );
					Grid grid = computerPlayer.getGrid(); // adjust target's grid
//					grid.setGridCell( targetCell, manualTarget.getHorizontal(), manualTarget.getVertical() );
					grid.setGridCellAlternative( targetCell, manualTarget.getVertical(), manualTarget.getHorizontal() );

					computerPlayer.setGrid( grid ); // Update the target grid
				}
				humanPlayer.incrementTurns();
			}
			catch( IOException e )
			{
				e.printStackTrace();
			}

			//=======================================================================
			// Computer Player section, targets human Player's grid
			//=======================================================================

			// Get a random location
			GridTarget randomTarget = generateRandomTargetPosition();

			if( disallowStrikingPreviousMisses ) // prevent computer from hitting previous selection
			{
				while(true)
				{
					GridCell targetStrike = humanPlayer.getGridCell( randomTarget.getVertical(), randomTarget.getHorizontal() );

					if( targetStrike.isHit() ) // Check if already hit.
					{
						// This spot has been hit before so do not break the loop and allow it to generate another target
						randomTarget = generateRandomTargetPosition();
					}
					else
					{
						break; // Have not hit this one before so it may be used
					}
				}
			}

			GridCell targetStrike = humanPlayer.getGridCell( randomTarget.getVertical(), randomTarget.getHorizontal() );
			if( targetStrike.isHit() ) // Check if already hit.
			{
				// Already hit. Do nothing.
			}
			else
			{
				GridCell targetCell;
				if( targetStrike.isOccupied() ) // HIT
				{ // Note on their grid there was a hit
					targetCell = new GridCell(" X ");
					humanPlayer.decrementGamePoints(); // update other player's points
					if( showStepsOutput )
					{
						out.println( computerPlayer.getLabel() + " hit on target '" + Positions.translateHorizontalNumberPositionToLetterLabel( randomTarget.getHorizontal() ) + randomTarget.getVertical() + "'" );
					}
				}
				else // MISS
				{
					targetCell = new GridCell(" . ");
					if( showStepsOutput )
					{
						out.println( computerPlayer.getLabel() + " miss on target '" + Positions.translateHorizontalNumberPositionToLetterLabel( randomTarget.getHorizontal() ) + randomTarget.getVertical() + "'" );
					}
				}
				targetCell.setHit( true );
				Grid grid = humanPlayer.getGrid(); // adjust target's grid
				grid.setGridCellAlternative( targetCell, randomTarget.getVertical(), randomTarget.getHorizontal() );
				humanPlayer.setGrid( grid ); // Update the target grid
			}
			computerPlayer.incrementTurns();

			//=======================================================================
			// Update scores and statuses
			//=======================================================================

			// check whether there is a victor
			if( !gameRunning() )
			{
				gameIsRunning = false; // end the game
			}
			else // Show the grid for the next round
			{
				if( showGridEachRound )
				{
					displayBothPlayerGrids( humanPlayer.getLabel(), computerPlayer.getLabel() );
				}
			}
		}

		displayBothPlayerGrids( humanPlayer.getLabel(), computerPlayer.getLabel() );
		Player winner = players.get( declareWinner() );
		out.println(winner.getLabel()+" is the winner!");
		out.println("  Winner points remaining: " + winner.getGamePoints());
		if( showStepsOutput )
		{
			out.println( player1.getLabel() + " took " + player1.getTurns() + " turns." );
			out.println( player2.getLabel() + " took " + player2.getTurns() + " turns." );
		}
	}


	/**
	 * @return the number index of player (in the list of players) who won with more points than zero. Or return -1 if there is no victor yet.
	 */
	public Integer declareWinner()
	{
		if( ! gameRunning() ) // Is the game over
		{
			for (int i = 0; i < players.size(); i++)
			{
				if( players.get(i).getGamePoints() > 0 ) // Who had points remaining
				{
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * Display each player's board grid with their ships, joined together on one output.
	 * @param label1 Player 1 name
	 * @param label2 Player 2 name
	 */
	public void displayBothPlayerGrids( String label1, String label2 )
	{
		// for every new line
		//   concatenate spaces + other line line
		String s1 = player1.displayGridString();
		String s2 = player2.displayGridString();
		String s1Lines[] = s1.split(System.getProperty("line.separator"));
		String s2Lines[] = s2.split(System.getProperty("line.separator"));
		final String spaces = "      ";
		for( int i = 0; i < s1Lines.length; i++ )
		{
			String output = s1Lines[i] + spaces + s2Lines[i];
			out.println( output );
		}

		out.print( "    " + label1 );

		for( int i = 0; i < s1Lines[0].length() - 1; i++ ) // Using the board display string length
		{
			out.print( " " );
		}
		out.println( label2 );
	}

	/**
	 * @return true if game is still running. False if game is over.
	 */
	public boolean gameRunning()
	{
		Iterator<Player> playersIterator = players.iterator();
		while( playersIterator.hasNext() ) // Go through each player
		{
			Player currentPlayer = playersIterator.next();
			if( currentPlayer.getGamePoints() <= 0 )
			{
				return false;
			}
		}
		return true;
	}

	public void putShipPiecesOntoBoard( Player player )
	{
		// We never need to reach zero since we store the ships corresponding to their length value, not memory location value
		// Start off with biggest first because it's probably easier to put the smaller ships secondarily

		// Subtract one on starting integer to avoid zero-based array indices out of bounds errors
		// Loop limiter set to greater-than-or-equals to allow for reaching values again due on zero-based array indices
		for( int i = numberOfShips - 1; i >= offsetNumberOfShips; i-- )
		{
			Ship ship = generateShipAtRandomPosition(i);

			if( player.putShip( ship ) )
			{
//					out.println( player.getLabel() + " puts ship at " + Positions.translateHorizontalNumberPositionToLetterLabel( ship.getPositionHorizontal() ) +
//						ship.getPositionVertical() + ", length " + ship.getLength() + ", direction " + ship.getDirection());
//					player.displayGrid();
			}
			else
			{
//				out.println( "Ship position not valid. Vert:" + ship.getPositionHorizontal() + " Hor: " + ship.getPositionVertical() + " Len: " + ship.getLength() + " Dir: " + ship.getDirection());
				i++; // step the loop back and try again
			}
		}
	}


	/**
	 *
	 * @param length A parameter merely passed along in the ship construction process. Not used in the position generations.
	 * @return
	 */
	public Ship generateShipAtRandomPosition( int length )
	{
		// Generate random number between zero and board size
		// Generate a pseudorandom, uniformly distributed int value between 0 (inclusive) and the specified value (exclusive)"

		Random rn = new Random();
		Integer vertical = rn.nextInt(boardSize) + 1;
		Integer horizontal = rn.nextInt(boardSize) + 1;
		Integer directionNumber = rn.nextInt(numberOfDirections);
		String direction = "";

		if( directionNumber == 0 )
		{ // North
			direction = "N";
		}
		else if( directionNumber == 1 )
		{ // South
			direction = "S";
		}
		else if( directionNumber == 2 )
		{ // East
			direction = "E";
		}
		else if( directionNumber == 3 )
		{ // West
			direction = "W";
		}

		return new Ship(vertical, horizontal, length, direction);
	}

	public GridTarget generateRandomTargetPosition( )
	{
		// Generate random number between zero and board size
		// Generate a pseudo-random, uniformly distributed int value between 0 (inclusive) and the specified value (exclusive)
		Random rn = new Random();

		// Always add +1 OUTISDE the randomization call to avoid the trouble with including zero in the generation
		Integer vertical = rn.nextInt(boardSize) + 1;
		Integer horizontal = rn.nextInt(boardSize) + 1;
		return new GridTarget(vertical, horizontal);
	}

}
