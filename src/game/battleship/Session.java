package game.battleship;

import game.battleship.grid.GridTarget;
import game.battleship.grid.objects.GridCell;
import game.battleship.grid.objects.Ship;

import java.util.Random;

import static java.lang.System.out;

/**
 * A game session consists of two players. Whenever a player's points drops to zero, the other player wins and the game is over.
 */
public class Session
{
	// Write a random ship placement function
	// For the computer opponent, use the random ship placement function
	// After each attempted strike, calculate both sides points to check if there is a winner

	Player player1, player2;
	Integer boardSize;
	Integer numberOfShips;
	final Integer numberOfDirections = 4;

	public Session( int boardSize )
	{
		this.boardSize = boardSize;
		player1 = new Player( boardSize );
		player2 = new Player( boardSize );

		// Defaulting to use the player1's numbers, but should both be the same
		numberOfShips = player1.getNumberOfShips() - 1; // account for array indices sizing
	}

	public void start()
	{
		putShipPiecesOntoBoard(player1);
		putShipPiecesOntoBoard(player2);
		displayBothPlayerGrids( "Player1", "Player2" );

		// Play!
		// Get a random location
		GridTarget randomTarget = generateRandomTargetPosition();

		// Ask the target player what his grid has at that GridCell location
		GridCell target = player1.getGridCell( randomTarget.getVertical(), randomTarget.getHorizontal() );
		if( target.isOccupied() )
		{
			out.println( "HIT!" );
		}
		else
		{
			out.println( "MISS!" );
		}

		// hit the board gridcell at that target
		// update board display, and ship points if necessary
		// update each players total points
		// check whether there is a victor

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


	private void putShipPiecesOntoBoard( Player player )
	{
		// We never need to reach zero since we store the ships corresponding to their length value, not memory location value
		// Start off with biggest first because it's probably easier to put the smaller ships secondarily
		for( int i = numberOfShips; i > 0; i-- )
		{

			Ship ship = generateShipAtRandomPosition(i);
			if( player.putShip( ship ) )
			{
//				out.println( "Ship placed!" );
			}
			else
			{
//				out.println( "Ship position not valid. Vert:" + ship.getPositionHorizontal() + " Hor: " + ship.getPositionVertical() + " Len: " + ship.getLength() + " Dir: " + ship.getDirection());
				i++;
			}
		}
	}

	private Ship generateShipAtRandomPosition( int length )
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

	private GridTarget generateRandomTargetPosition( )
	{
		// Generate random number between zero and board size
		// Generate a pseudorandom, uniformly distributed int value between 0 (inclusive) and the specified value (exclusive)"
		Random rn = new Random();

		// Note we are not including the +1 for these because we are translating back to 0-based grid locations for targets
		// This avoids an array index out of bounds error
		Integer vertical = rn.nextInt(boardSize);
		Integer horizontal = rn.nextInt(boardSize);
		return new GridTarget(vertical, horizontal);
	}

}
