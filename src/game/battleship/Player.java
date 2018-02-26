package game.battleship;

import game.battleship.grid.Grid;
import game.battleship.grid.objects.GridCell;
import game.battleship.grid.objects.Ship;

import java.util.ArrayList;

/**
 * Each player's ship pieces are unique and there are no duplicates of the same size.
 *
 Players consist of their following:
 1 game.battleship.grid
 a number of ships (default pieces size)
 points remaining (added together from all nonDamaged ships values)

	Should the player contain the ships, or the player?

 The game.battleship.grid contains GridCells, but the concept of a ship is a higher one than just the GridCell's.

 */

public class Player
{
	private Grid grid;
	private Integer gamePoints = 0;
	private String label;


	// Length of the array is the number of pieces
	// Contents of each address index is the size of each corresponding piece
	private Integer myShipLengths[] = { 0, 1, 2, 3, 4, 5 };

	private ArrayList<Ship> myShips = new ArrayList<>(  );


	public Player(Integer boardSize, String label)
	{
		grid = new Grid( boardSize );
		this.label = label;

		// Add up all the game points at the start of the game
		for( int i = 0; i < myShipLengths.length; i++ )
		{
			gamePoints += myShipLengths[i];
		}

		// Create each ship piece with valid lengths but no defined valid position and direction
		for( int i = 0; i < myShipLengths.length; i++ )
		{
			myShips.add( new Ship( -1, -1, myShipLengths[i], "Z") );
		}
	}

	public void decrementGamePoints( )
	{
		gamePoints--;
	}

	public void displayGrid()
	{
		grid.displayGrid();
	}

	public String displayGridString()
	{
		return grid.displayGridString();
	}

	public Integer getGamePoints()
	{
		return gamePoints;
	}

	public String getLabel() {
		return label;
	}

	public Grid getGrid() {
		return grid;
	}

	public GridCell getGridCell( Integer vertical, Integer horizontal )
	{
		return grid.getGridCell( vertical, horizontal );
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

	public Integer getNumberOfShips()
	{
		return myShips.size();
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
		if( ship.getLength() > (myShips.size() - 1) || ship.getLength() <= 0 )
		{
			return false;
		}

		boolean status = grid.addShip( ship ); // Was the defined location accepted
		if( status )
		{
			myShips.set( ship.getLength(), ship ); // Update player's records of where the ship pieces are
		}
		return status;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
