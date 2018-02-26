package game.battleship.grid;

import game.battleship.grid.objects.GridCell;
import game.battleship.grid.objects.Ship;

import static java.lang.System.out;

public class Grid
{
	private GridCell[][] grid;
	final char[] horizontalLabels = Positions.getHorizontalLabels();
	private Integer gridsize = 0;

	// This allows players to choose more natural number values for human players, like A1 instead of A0.
	private final Integer COORDINATE_TRANSLATION = Positions.getCoordinateTranslationConstant();
	private final Integer REVERSE_COORDINATE_TRANSLATION = Positions.getReverseCoordinateTranslationConstant();

	public Grid( Integer gridsize )
	{
		if( gridsize <= 0 )
		{
			out.println( "Grid size must be a positive number. Defaulting to size 10." );
			this.gridsize = 10;
		}
		else if( gridsize > 26 && gridsize > 0 )
		{
			this.gridsize = 26;
			out.println( "Maximum allowed game.battleship.grid size is 26." );
		}
		else
		{
			this.gridsize = gridsize;
		}

		// initialize the array(s) because Java does not by default fill them out
		grid = new GridCell[gridsize][gridsize];
		for( int i = 0; i < getGridsize(); i++ )
		{
			for( int j = 0; j < getGridsize(); j++ )
			{
				grid[i][j] = new GridCell( );
			}
		}

	}

	/**
	 * Tries to add ship to the game.battleship.grid. Succeeds if the ship's full length is within the game.battleship.grid boundary coordinates and does not conflict with any other ship objects.
	 *
	 * @param ship A ship with length, contents, position (vertical-horizontal coordinates), and direction defined.
	 * @return true if successfully added the ship. false if not.
	 */
	public boolean addShip(Ship ship)
	{
		boolean acceptablePosition = true;
		Integer positionHorizontal = ship.getPositionHorizontal() + COORDINATE_TRANSLATION;
		Integer positionVertical = ship.getPositionVertical() + COORDINATE_TRANSLATION;

		if( positionHorizontal < 0 || positionVertical < 0 )
		{
			out.println( "Acceptable row values are from 1 to " + gridsize + "." );
			out.println( "Row values like A0, B0, C0, etc. are invalid." );
			out.println( "Minimal possible values are A1, B1, C1, etc...." );
			return false;
		}

		String direction = ship.getDirection();

		// Check all positions are okay before adding ship
		for( int i = 0; i < ship.getLength(); i++ )
		{
			if( direction.contentEquals( "N" ) )
			{
				// subtracting in the y-coords
//				out.println( "Checking " + positionHorizontal + "," + (positionVertical - i) );
				if( !checkGridPosition( positionVertical - i, positionHorizontal ) )
				{
					acceptablePosition = false;
					break;
				}
			}
			else if( direction.contentEquals( "S" ) )
			{
				// adding in the y-coords
//				out.println( "Checking " + positionHorizontal + "," + (positionVertical + i) );
				if( ! checkGridPosition( positionVertical + i, positionHorizontal ) )
				{
					acceptablePosition = false;
					break;
				}
			}
			else if( direction.contentEquals( "E" ) )
			{
				// adding in the x-coords
//				out.println( "Checking " + (positionHorizontal+i) + "," + positionVertical );
				if( ! checkGridPosition( positionVertical, positionHorizontal + i ) )
				{
					acceptablePosition = false;
					break;
				}
			}
			else if( direction.contentEquals( "W" ) )
			{
				// subtracting in the x-coords
//				out.println( "Checking " + (positionHorizontal-i) + "," + positionVertical );
				if( ! checkGridPosition( positionVertical, positionHorizontal - i ) )
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
	 * Checking if game.battleship.grid position is acceptable, meaning within boundaries and not occupied by a ship section already.
	 *
	 * @return true if acceptable, false if not.
	 */
	private boolean checkGridPosition( Integer posVertical, Integer posHorizontal )
	{
		// Horizontal-coord within bounds
		if( ! ( posHorizontal >= 0 && posHorizontal < getGridsize() ) )
		{
//			out.println( "Horizontal position NOT OK" );
			return false;
		}

		// Vertical-coord within bounds
		if( ! (posVertical >= 0 && posVertical < getGridsize() ) )
		{
//			out.println( "Vertical position NOT OK" );
			return false;
		}

		// Check for no pre-existing item at coords
		GridCell targetGridCell = getGridCell( posVertical, posHorizontal );
		if( targetGridCell.isOccupied() )
		{
//			out.println( "Position [" + Positions.translateHorizontalToLetterLabel(posHorizontal) + (posVertical + REVERSE_COORDINATE_TRANSLATION) + "]: Space is already occupied" );
			return false;
		}

		// Everything seems okay
		return true;
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
		out.println( printWholeRow( gridsize + 1) ); // only at start and end

		// Print top labels + game.battleship.grid edge
		for( int letter = 0; letter < getGridsize() + 1; letter++ )
		{
			out.print( printCell( (" " + horizontalLabels[letter] + " ") ) );
		}
		out.println( printGridEdge() );

		int rowLabel = 1;
		for( int i = 0; i < getGridsize()*2; i++ ) // the gridsize is multiplied by 2 in order to account for the game.battleship.grid row lines
		{
			if( markerLine ) // Printing game.battleship.grid separator lines
			{ // Print contents as game.battleship.grid line markers
				out.print( printLineSeparatorRow( getGridsize() + 1, gridLineMarkers ) );
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
				for( int j = 0; j < getGridsize(); j++ )
				{
					// remember to divide the 'i' variable by 2 because we multipled it by 2 in the outer loop
					out.print( printCell( grid[i / 2][j].toString() ) );
				}
				out.println( printGridEdge() );
			}

			markerLine = !markerLine; // toggle
		}
		out.println( printWholeRow( gridsize + 1) );
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
		display += printWholeRow( gridsize + 1); // only at start and end
		display += System.getProperty("line.separator");

		// Print top labels + game.battleship.grid edge
		for( int letter = 0; letter < getGridsize() + 1; letter++ )
		{
			display += ( printCell( (" " + horizontalLabels[letter] + " ") ) );
		}
		display += ( printGridEdge() );
		display += System.getProperty("line.separator");

		int rowLabel = 1;
		for( int i = 0; i < getGridsize()*2; i++ ) // the gridsize is multiplied by 2 in order to account for the game.battleship.grid row lines
		{
			if( markerLine ) // Printing game.battleship.grid separator lines
			{ // Print contents as game.battleship.grid line markers
				display += ( printLineSeparatorRow( getGridsize() + 1, gridLineMarkers ) );
				display += ( printGridEdge() );
				display += System.getProperty("line.separator");
			}
			else // Printing game.battleship.grid contents lines
			{
				// Print vertical labels
				if( rowLabel < 10 )
				{
					display += ( printCell( (" " + rowLabel + " ") ) );
				}
				else
				{
					display += ( printCell( (rowLabel + " ") ) );
				}
				rowLabel++;

				// Print cell contents
				for( int j = 0; j < getGridsize(); j++ )
				{
					// remember to divide the 'i' variable by 2 because we multipled it by 2 in the outer loop
					display += ( printCell( grid[i / 2][j].toString() ) );
				}
				display += ( printGridEdge() );
				display += System.getProperty("line.separator");
			}

			markerLine = !markerLine; // toggle
		}
		display += ( printWholeRow( gridsize + 1) );
		display += System.getProperty("line.separator");

		return display;
	}

	public Integer getGridsize()
	{
		return gridsize;
	}

	public GridCell getGridCell( Integer posVertical, Integer posHorizontal )
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
	 *
	 * @param gridItem
	 * @param posVertical
	 * @param posHorizontal
	 */
	public void setGridCell( GridCell gridItem, Integer posVertical, Integer posHorizontal )
	{
		this.grid[posHorizontal][posVertical] = gridItem;
	}


}

