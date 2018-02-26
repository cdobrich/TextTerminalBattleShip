package game.battleship;

import game.battleship.grid.Grid;
import game.battleship.grid.GridTarget;
import game.battleship.grid.objects.GridCell;
import game.battleship.grid.objects.Ship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static java.lang.System.out;

/**
 * A game session consists of two players. Whenever a player's points drops to zero, the other player wins and the game is over.
 */
public class Session
{
	Player player1, player2;
	Integer boardSize;
	Integer numberOfShips;
	final Integer numberOfDirections = 4;
	ArrayList<Player> players = new ArrayList<>();
	
	public Session( int boardSize )
	{
		this.boardSize = boardSize;
		player1 = new Player( boardSize,"Player");
		players.add(player1);
		player2 = new Player( boardSize,"Opponent");
		players.add(player2);

		// Defaulting to use the player1's numbers, but should both be the same
		numberOfShips = player1.getNumberOfShips() - 1; // account for array indices sizing
	}

	public void start2()
	{
		// Always start off with user as first player to go
		Player attackingPlayer = players.get( 0 );
		Player targetPlayer = players.get( 1 ); // Always start off the opponent as other player

		// Populate the board pieces
		putShipPiecesOntoBoard( attackingPlayer );
		putShipPiecesOntoBoard( targetPlayer );
		displayBothPlayerGrids( attackingPlayer.getLabel(), targetPlayer.getLabel() );

		boolean gameIsRunning = true;
		while( gameIsRunning )
		{
			// Get a random location
			GridTarget randomTarget = generateRandomTargetPosition();

			// Examine the target player's grid at that target GridCell location
//			out.println(attackingPlayer.getLabel() + " attacks the " + targetPlayer.getLabel() + " at " + randomTarget);

			GridCell targetStrike = targetPlayer.getGridCell( randomTarget.getVertical(), randomTarget.getHorizontal() );
			if( targetStrike.isHit() ) // Check if already hit.
			{
				// Already hit. Do nothing.
				// They are allowed to hit this multiple times if they choose.
			}
			else
			{
				GridCell targetCell;
				if( targetStrike.isOccupied() )
				{ // Note on their grid there was a hit
					targetCell = new GridCell(" X ");
					targetCell.setHit( true );
//					out.println( "  HIT!!" );
					// update other player's points
					targetPlayer.decrementGamePoints();
				}
				else
				{
					targetCell = new GridCell(" . ");
				}
				Grid grid = targetPlayer.getGrid(); // adjust otherPlayer's grid
				grid.setGridCell( targetCell, randomTarget.getVertical(), randomTarget.getHorizontal() );
				targetPlayer.setGrid( grid ); // Update the player grid
			}

			// check whether there is a victor
			if( !gameRunning() )
			{
				gameIsRunning = false; // end the game
//				break; // Leave this current inner loop
			}

			// Swap the players
			Player tempPlayer = attackingPlayer;
			attackingPlayer = targetPlayer;
			targetPlayer = tempPlayer;
		}

//		displayBothPlayerGrids( attackingPlayer.getLabel(), targetPlayer.getLabel() );
		displayBothPlayerGrids( player1.getLabel(), player2.getLabel() );
		Player winner = players.get( declareWinner() );
		out.println(winner.getLabel()+" is the winner!");
		out.println("Winner points remaining: " + winner.getGamePoints());

	}




	public void start()
	{
		putShipPiecesOntoBoard(player1);
		putShipPiecesOntoBoard(player2);
		displayBothPlayerGrids( player1.getLabel(), player2.getLabel() );

		// Play!
		boolean gameIsRunning = true;
		Integer playerNumber = 0;
		Player otherPlayer = players.get(1); // Always start off the opponent as other player

		while( gameIsRunning )
		{
			while( playerNumber >= 0 && playerNumber < 2 ) // Go through each player
			{
				Player currentPlayer = players.get(playerNumber);
				// toggle between the two players
				// This is obviously a little limited but for the simple demo we will stick with only two players
				if( playerNumber == 0 )
				{
					playerNumber = 1;
				}
				else
				{
					playerNumber = 0;
				}

				// Get a random location
				GridTarget randomTarget = generateRandomTargetPosition();

				// Ask the target player what his grid has at that GridCell location
//				out.println(currentPlayer.getLabel() + " attacks " + randomTarget);


				GridCell target = otherPlayer.getGridCell( randomTarget.getVertical(), randomTarget.getHorizontal() );
				if( target.isHit() )
				{
					// Already hit. Do nothing.
					// They are allowed to hit this multiple times if they choose.
				}
				else
				{
					Grid grid = otherPlayer.getGrid();
					if( target.isOccupied() )
					{
//					out.println( "  HIT!" );

						GridCell targetCell = new GridCell(" X ");
//						targetCell.setOccupied( true );
						targetCell.setHit( true );
						grid.setGridCell( targetCell, randomTarget.getVertical(), randomTarget.getHorizontal() );

						// update other player's points
						otherPlayer.decrementGamePoints();

						// switch to using for loop, and use the i++ or i-- to toggle the other players values?
						// Or key a previousPlayer variable? Do we want to make a pair of opponents?
					}
					else
					{ // Note on their grid there was a miss
//					out.println( "  MISS!" );

						grid.setGridCell(new GridCell(" . "), randomTarget.getVertical(), randomTarget.getHorizontal());
					}
					otherPlayer.setGrid( grid ); // Update the player grid
				}

				// check whether there is a victor
				if( ! gameRunning() )
				{
					gameIsRunning = false; // end the game
					break; // Leave this current inner loop
				}
				otherPlayer = currentPlayer; // Set other player for next round to the current player
				currentPlayer = players.get(playerNumber); // Set to next player
			}
		}

		displayBothPlayerGrids( player1.getLabel(), player2.getLabel() );
		Player winner = players.get( declareWinner() );
		out.println(winner.getLabel() + " is the winner!");
		out.println("Winner points remaining: " + winner.getGamePoints());

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

	private void putShipPiecesOntoBoard( Player player )
	{
		// We never need to reach zero since we store the ships corresponding to their length value, not memory location value
		// Start off with biggest first because it's probably easier to put the smaller ships secondarily
		for( int i = numberOfShips; i > 0; i-- )
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
		// Generate a pseudo-random, uniformly distributed int value between 0 (inclusive) and the specified value (exclusive)"
		Random rn = new Random();

		// Note we are not including the +1 for these because we are translating back to 0-based grid locations for targets
		// This avoids an array index out of bounds error
		Integer vertical = rn.nextInt(boardSize + 1);
		Integer horizontal = rn.nextInt(boardSize + 1);
		return new GridTarget(vertical, horizontal);
	}

}
