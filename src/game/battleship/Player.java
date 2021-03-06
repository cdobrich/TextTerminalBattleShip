package game.battleship;

import game.battleship.grid.Grid;
import game.battleship.grid.objects.GridCell;
import game.battleship.grid.objects.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Each player's ship pieces are unique and there are no duplicates of the same size.

 Players consist of their following:
 A single game.battleship.grid
 A number of ships (default pieces size)
 Their points remaining (added together from all nonDamaged ships values)

 The game.battleship.grid contains GridCells, but the concept of a ship is a higher one than just the GridCell's.

 */

public class Player
{
	private Grid grid;
	private Integer gamePoints = 0;

	public int getTurns() { return turns;	}

	public void incrementTurns( ) {	turns++;	}

	private int turns = 0;
	private String label;

	// Length of the array is the number of pieces
	// Contents of each address index is the size of each corresponding piece
	private List<Integer> myShipLengthsTemp = Arrays.asList( 0, 0, 2, 3, 4, 5 ); // Pre-pad the spaces because it's just easier for quick development
	private ArrayList<Integer> myShipLengths = new ArrayList<>(myShipLengthsTemp);
	private ArrayList<Ship> myShips = new ArrayList<>(  );


	/**
	 * Create a player's game board. Add up the player's current points and place his ship pieces into inventory but not onto the board yet..
	 * @param boardSize Size of the game board grid.
	 * @param label Name of the player
	 */
	public Player(Integer boardSize, String label)
	{
		grid = new Grid( boardSize );
		this.label = label;

		// Add up all the game points at the start of the game
		for( int i = 0; i < myShipLengths.size(); i++ )
		{
			gamePoints += myShipLengths.get( i );
		}

		// Create each ship piece with valid lengths but no defined valid position and direction
		for( int i = 0; i < myShipLengths.size(); i++ )
		{
			myShips.add( new Ship( -1, -1, myShipLengths.get( i ), "Z") );
		}
	}

	public void decrementGamePoints( ) { gamePoints--; }

	public void displayGrid() {	grid.displayGrid();	}

	public String displayGridString() {	return grid.displayGridString(); }

	public int getGamePoints() { return gamePoints;	}

	public String getLabel() { return label; }

	public Grid getGrid() { return grid; }

	/**
	 * Retrieve a gridcell from the player's game board at the target coordinates.
	 * @param vertical
	 * @param horizontal
	 * @return the target GridCell object if coordinates are valid, or null if not.
	 */
	public GridCell getGridCell( int vertical, int horizontal )
	{
		if( grid.checkPlayableGridSingleCoordinate( vertical ) && grid.checkPlayableGridSingleCoordinate( horizontal ) )
		{
			return grid.getGridCell( vertical, horizontal );
		}
		else
		{
			return null;
		}
	}

	/**
	 * Returns the player's ship object corresponding to the size of the ship requested.
	 *
	 * @param shipSize The size of the ship requested.
	 * @return player's ship object corresponding to the size of the ship requested.
	 */
	public Ship getShip( Integer shipSize )
	{
		return myShips.get( shipSize );
	}

	public int getNumberOfShips()
	{
		return myShips.size();  // account for array indices sizing and no ship of size 1
	}

	/**
	 * Retrieve the number of empty spaces pre-padding the ships list. Used in array access for proper range targetting.
	 * @return  The number of empty spaces pre-padding the ships list.
	 */
	public int getOffsetOfMyShipsList()
	{
		final int OFFSET_NUMBER_OF_SHIPS = 2;
		return OFFSET_NUMBER_OF_SHIPS;
	}

	/**
	 * Place ship piece onto board and update its record for the player's pieces.
	 *
	 * @param ship Ship piece to be placed onto board with defined location, length, and orientation.
	 * @return true is successfully placed and updated ship piece on game.battleship.grid.
	 *        false if the ship piece does not already exist as a legitimate piece to be replaced (moved on board).
	 *        false if the location specified for adding the ship was not acceptable.
	 */
	public boolean putShip( Ship ship )
	{
		// Make sure we are only updating an existing allowed ship piece for the player's inventory
		if( ! myShipLengths.contains( ship.getLength() ) )
		{
			// Return false if the length isn't allowed
			return false;
		}

		boolean status = grid.addShip( ship ); // Was the defined location accepted
		if( status )
		{
//			System.out.println(myShips);
//			System.out.println("Size of myShips " + myShips.size());
			myShips.set( ship.getLength() - 1, ship ); // Update player's records of where the ship pieces are
		}
		return status;
	}

	public void setGrid(Grid grid) { this.grid = grid;	}

}
