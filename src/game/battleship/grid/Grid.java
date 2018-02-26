package game.battleship.grid;

import game.battleship.grid.objects.GridCell;
import game.battleship.grid.objects.Ship;

import static java.lang.System.out;

/**
 * The actual grid size in memory is 0 to gridSize inclusively. However the playable board dimensions are from 1 to playableGrid inclusively.
 *
 * Note: There are some errors if you make the grid size below
 */

public class Grid
{

	private GridCell[][] grid;
	final char[] horizontalLabels = Positions.getHorizontalLabels();
	private Integer playableGrid = 0; // Playable area of grid
	private Integer gridSizeInMemory = 0; // Actual area size of the grid in memory

	/**
	 * Initialize the playable grid within memory, populated with blank gridcells, and set related class size variables.
	 * If problems are encountered with unusual size grid specifications, method defaults to size 10x10.
	 * @param playableGrid The playable square area of the grid for the game.
	 */
	public Grid( Integer playableGrid )
	{
		if( playableGrid <= 0 )
		{
			out.println( "Grid size must be a positive number. Defaulting to size 10." );
			this.playableGrid = 10;
		}
		else if( playableGrid > 26 && playableGrid > 0 )
		{
			this.playableGrid = 26;
			out.println( "Maximum allowed game.battleship.grid size is 26." );
		}
		else if( playableGrid < 10 )
		{
			out.println( "Minimum grid size is 10. Defaulting to size 10." );
			this.playableGrid = 10;
		}
		else
		{
			this.playableGrid = playableGrid;
		}
		this.gridSizeInMemory = this.playableGrid + 1; // Setup the actual memory grid size area

		// initialize the array(s) because Java does not by default fill them out
		// fill out all the columns, including the unplayable row and column zero.
		grid = new GridCell[gridSizeInMemory][gridSizeInMemory];
		for( int i = 0; i < getGridSizeInMemory(); i++ )
		{
			for( int j = 0; j < getGridSizeInMemory(); j++ )
			{
				grid[i][j] = new GridCell( );
			}
		}

	}

	/**
	 * Tries to add ship to the game.battleship.grid. Succeeds if the ship's full length is within the game.battleship.grid boundary coordinates and does not conflict with any other ship objects.
	 * The Ship's coordinates are translated from Grid layout into memory zero-based layout.
	 *
	 * @param ship A ship with length, contents, position (vertical-horizontal coordinates), and direction defined.
	 * @return true if successfully added the ship. false if not.
	 */
	public boolean addShip(Ship ship)
	{
		boolean acceptablePosition = true;
		Integer positionHorizontal = ship.getPositionHorizontal();
		Integer positionVertical = ship.getPositionVertical();

		if( ! checkPlayableGridSingleCoordinate(positionHorizontal ) || ! checkPlayableGridSingleCoordinate( positionVertical ) )
		{
			out.println( "Acceptable row values are from 1 to " + playableGrid + "." );
			out.println( "Row values like A0, B0, C0, etc. are invalid." );
			out.println( "Minimal possible values are A1, B1, C1, etc...." );
			return false;
		}


		String direction = ship.getDirection();

		// Check all positions the length of the ship could occupied are okay
		// Also check if the location is already occupied by another ship
		for( int i = 0; i < ship.getLength(); i++ )
		{
			if( direction.contentEquals( "N" ) )
			{
				// subtracting in the y-coords
//				out.println( "Checking " + positionHorizontal + "," + (positionVertical - i) );
				if( ! checkPlayableGridPosition( positionVertical - i, positionHorizontal ) )
				{
					acceptablePosition = false;
					break;
				}
			}
			else if( direction.contentEquals( "S" ) )
			{
				// adding in the y-coords
//				out.println( "Checking " + positionHorizontal + "," + (positionVertical + i) );
				if( ! checkPlayableGridPosition( positionVertical + i, positionHorizontal ) )
				{
					acceptablePosition = false;
					break;
				}
			}
			else if( direction.contentEquals( "E" ) )
			{
				// adding in the x-coords
//				out.println( "Checking " + (positionHorizontal+i) + "," + positionVertical );
				if( ! checkPlayableGridPosition( positionVertical, positionHorizontal + i ) )
				{
					acceptablePosition = false;
					break;
				}
			}
			else if( direction.contentEquals( "W" ) )
			{
				// subtracting in the x-coords
//				out.println( "Checking " + (positionHorizontal-i) + "," + positionVertical );
				if( ! checkPlayableGridPosition( positionVertical, positionHorizontal - i ) )
				{
					acceptablePosition = false;
					break;
				}
			}
		}


		if( acceptablePosition )
		{
			GridCell targetGridCell = new GridCell(  );
			targetGridCell.setContents( ship.getContents() );
			targetGridCell.setOccupied( true );

			// Add the ship
			for( int i = 0; i < ship.getLength(); i++ )
			{
				if( direction.contentEquals( "N" ))
				{
					// subtracting in the vertical-coords
					setGridCell(targetGridCell, positionHorizontal, positionVertical - i );
				}
				else if( direction.contentEquals( "S" ) )
				{
					// adding in the vertical-coords
//					out.println( "Setting " + positionHorizontal + "," + (positionVertical + i) );
					setGridCell(targetGridCell, positionHorizontal, positionVertical + i );
				}
				else if( direction.contentEquals( "E" ) )
				{
					// adding in the horizontal-coords
					setGridCell(targetGridCell, positionHorizontal + i, positionVertical );
				}
				else if( direction.contentEquals( "W" ) )
				{
					// subtracting in the horizontal-coords
					setGridCell(targetGridCell, positionHorizontal - i, positionVertical );
				}
			}
		}

		return acceptablePosition;
	}

	/**
	 * Checking if actual coordinate position is acceptable, meaning within boundaries and not occupied by a ship section already.
	 *
	 * @return true if acceptable, false if not.
	 * @param posVertical
	 * @param posHorizontal
	 */
	public boolean checkPlayableGridPosition( int posVertical, int posHorizontal )
	{
		// Horizontal-coord within bounds
		if( checkPlayableGridSingleCoordinate( posHorizontal ) &&	checkPlayableGridSingleCoordinate( posVertical ) )
		{
			// Check for no pre-existing item at coords
			GridCell targetGridCell = getGridCell( posVertical, posHorizontal );
			if( targetGridCell.isOccupied() )
			{
//			out.println( "Position [" Positions.translateHorizontalNumberPositionToLetterLabel(posHorizontal) + posVertical + "]: Space is already occupied" );
				return false;
			}
			else
			{
				// Everything seems okay
				return true;
			}
		}
		else
		{
			// Either or both coordinates are incorrect
			return false;
		}
	}

	/**
	 * Evalutes whether a single input position is allowed within the playable grid area, ranging from 1 to the GridSize, inclusively.
	 * Note this does not check if the space is occupied.
	 *
	 * @param position Single dimensional poosition being checked, such a 1 or 5 or 10.
	 * @return true if position is allowed, false if not.
	 */
	public boolean checkPlayableGridSingleCoordinate( int position )
	{
		if(position > 0 && position <= getPlayableGrid())
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	/**
	 * Display the board game.battleship.grid, including ships, empty spaces, misses, and hits.
	 *
	 * Horizontal labels are from the alphabet, corresponding to the X-axis.
	 * Vertical labels are from the row numbers, corresponding to the Y-axis.
	 *
	 * Grid limit size is 26, due to alphabet size.
	 */
	public void displayGrid()
	{
		final String gridLineMarkers = "---";
		boolean markerLine = true;

		// Print top row (including length for blank game.battleship.grid starting spacer cell)
		out.println( printWholeRow( playableGrid + 1) ); // only at start and end

		// Print top labels + game.battleship.grid edge
		for( int letter = 0; letter < getPlayableGrid() + 1; letter++ )
		{
			out.print( printCell( (" " + horizontalLabels[letter] + " ") ) );
		}
		out.println( printGridEdge() );

		int rowLabel = 1;
		for( int i = 1; i <= getPlayableGrid()*2; i++ ) // the playableGrid is multiplied by 2 in order to account for the game.battleship.grid row lines
		{
			if( markerLine ) // Printing game.battleship.grid separator lines
			{ // Print contents as game.battleship.grid line markers
				out.print( printLineSeparatorRow( getPlayableGrid() + 1, gridLineMarkers ) );
				out.println( printGridEdge() );
			}
			else // Printing game.battleship.grid contents lines
			{
				// Print vertical labels
				if( rowLabel < 10 )
				{
					out.print( printCell( (" " + rowLabel + " ") ) );
				}
				else
				{
					out.print( printCell( (rowLabel + " ") ) );
				}
				rowLabel++;

				// Print cell contents
				for( int j = 1; j <= getPlayableGrid(); j++ )
				{
					// remember to divide the 'i' variable by 2 because we multipled it by 2 in the outer loop
					out.print( printCell( grid[i / 2][j].toString() ) );
				}
				out.println( printGridEdge() );
			}

			markerLine = !markerLine; // toggle
		}
		out.println( printWholeRow( playableGrid + 1) );
	}

	/**
	 * Get string representing the display of the board game.battleship.grid, including ships, empty spaces, misses, and hits.
	 *
	 * Horizontal labels are from the alphabet, corresponding to the X-axis.
	 * Vertical labels are from the row numbers, corresponding to the Y-axis.
	 *
	 * Grid limit size is 26, due to alphabet size.
	 */
	public String displayGridString()
	{
		String display = "";
		final String gridLineMarkers = "---";
		boolean markerLine = true;

		// Print top row (including length for blank game.battleship.grid starting spacer cell)
		display += printWholeRow( playableGrid + 1); // only at start and end
		display += System.getProperty("line.separator");

		// Print top labels + game.battleship.grid edge
		for( int letter = 0; letter < getPlayableGrid() + 1; letter++ )
		{
			display += ( printCell( (" " + horizontalLabels[letter] + " ") ) );
		}
		display += ( printGridEdge() );
		display += System.getProperty("line.separator");

		int rowLabel = 1;
		for( int i = 1; i <= getPlayableGrid()*2; i++ ) // the playableGrid is multiplied by 2 in order to account for the game.battleship.grid row lines
		{
			if( markerLine ) // Printing game.battleship.grid separator lines
			{ // Print contents as game.battleship.grid line markers
				display += ( printLineSeparatorRow( getPlayableGrid() + 1, gridLineMarkers ) );
				display += ( printGridEdge() );
				display += System.getProperty("line.separator");
			}
			else // Printing game.battleship.grid contents lines
			{
				// Print vertical labels
				if( rowLabel < 10 ) // Account for needing a leading space before the single digit output
				{
					display += ( printCell( (" " + rowLabel + " ") ) );
				}
				else // Do not place a leading space before digit output
				{
					display += ( printCell( (rowLabel + " ") ) );
				}
				rowLabel++;

				// Print cell contents
				for( int j = 1; j <= getPlayableGrid(); j++ )
				{
					// remember to divide the 'i' variable by 2 because we multipled it by 2 in the outer loop

					int half = i / 2;
					display += ( printCell( grid[half][j].toString() ) );
				}
				display += ( printGridEdge() );
				display += System.getProperty("line.separator");
			}

			markerLine = !markerLine; // toggle
		}
		display += ( printWholeRow( playableGrid + 1) );
		display += System.getProperty("line.separator");

		return display;
	}

	/**
	 * Get the playable grid board dimensions, which are from 1 to playableGrid inclusively.
	 */
	public Integer getPlayableGrid()
	{
		return playableGrid;
	}

	/**
	 * Get actual grid dimensions in memory, which are from 0 to gridSize inclusively.
	 */
	public Integer getGridSizeInMemory()
	{
		return gridSizeInMemory;
	}

	/**
	 * Note there is no position enforcement checking, so you can definitely go out of bounds.
	 *
	 * @param posHorizontal
	 * @param posVertical
	 * @return
	 */
	public GridCell getGridCell( int posVertical, int posHorizontal )
	{
//		return grid[posHorizontal][posVertical];
		return grid[posVertical][posHorizontal];
	}

	public GridCell getGridCellAlternate( int posVertical, int posHorizontal )
	{
		return grid[posVertical][posHorizontal];
	}

	private static String printGridEdge()
	{
		return "|";
	}

	/*
	Print '|   ' or '|---' or '| X ', etc.
	 */
	private static String printCell( String contents )
	{
		return "|" + contents;
	}

	private static String printLineSeparatorRow( int gridsize, String contents )
	{
		String str = "";
		for( int i = 0; i < gridsize; i++ )
		{
			str += "|";
			str += contents;
		}
		return str;
	}

	private static String printWholeRow( int gridsize )
	{
		String str = "+";
		for( int i = 0; i < gridsize - 1; i++ )
		{
			str += "----";
		}
		str += "---+";
		return str;
	}

	/**
	 * @param gridItem
	 * @param posVertical
	 * @param posHorizontal
	 */
	public boolean setGridCell( GridCell gridItem, int posVertical, int posHorizontal )
	{
		if( checkPlayableGridSingleCoordinate( posVertical ) && checkPlayableGridSingleCoordinate(posHorizontal ) )
		{
			this.grid[posHorizontal][posVertical] = gridItem;
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean setGridCellAlternative( GridCell gridItem, int posVertical, int posHorizontal )
	{
		if( checkPlayableGridSingleCoordinate( posVertical ) && checkPlayableGridSingleCoordinate(posHorizontal ) )
		{
			this.grid[posVertical][posHorizontal] = gridItem;
			return true;
		}
		else
		{
			return false;
		}
	}


}

