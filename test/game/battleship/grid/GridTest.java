package game.battleship.grid;

import game.battleship.Player;
import game.battleship.grid.objects.GridCell;
import game.battleship.grid.objects.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest
{

	@Test
	void addShip()
	{
		Integer playableGridSize = 10;
		int posVertical, posHorizontal, length;
		Grid grid;
		GridCell gridCell;
		Ship ship;

		// Valid coords put the ship on the grid
		length = 5;
		posVertical = 1;
		posHorizontal = 1;
		grid = new Grid( playableGridSize ); // rebuild the board grid
		ship = new Ship(posVertical, posHorizontal, length, "S" );
		assertTrue( grid.addShip( ship ) );

		// Check the Ship was placed at the coordinates
		gridCell = grid.getGridCell( posVertical + 0, posHorizontal );
		assertEquals( gridCell.getContents(), " @ " );
		gridCell = grid.getGridCell( posVertical + 1, posHorizontal );
		assertEquals( gridCell.getContents(), " @ " );
		gridCell = grid.getGridCell( posVertical + 2, posHorizontal );
		assertEquals( gridCell.getContents(), " @ " );
		gridCell = grid.getGridCell( posVertical + 3, posHorizontal );
		assertEquals( gridCell.getContents(), " @ " );
		gridCell = grid.getGridCell( posVertical + 4, posHorizontal );
		assertEquals( gridCell.getContents(), " @ " );


		grid = new Grid( playableGridSize ); // rebuild the board grid
		ship = new Ship(posVertical, posHorizontal, length, "E" );
		assertTrue( grid.addShip( ship ) );

		// Check the Ship was placed at the coordinates
		gridCell = grid.getGridCell( posVertical, posHorizontal + 0);
		assertEquals( gridCell.getContents(), " @ " );
		gridCell = grid.getGridCell( posVertical, posHorizontal + 1);
		assertEquals( gridCell.getContents(), " @ " );
		gridCell = grid.getGridCell( posVertical, posHorizontal + 2);
		assertEquals( gridCell.getContents(), " @ " );
		gridCell = grid.getGridCell( posVertical, posHorizontal + 3);
		assertEquals( gridCell.getContents(), " @ " );
		gridCell = grid.getGridCell( posVertical, posHorizontal + 4);
		assertEquals( gridCell.getContents(), " @ " );





		// Invalid coords
		grid = new Grid( playableGridSize ); // rebuild the board grid
		ship = new Ship(1, 1, 5, "N" );
		assertFalse( grid.addShip( ship ) );

		grid = new Grid( playableGridSize ); // rebuild the board grid
		ship = new Ship(1, 1, 5, "W" );
		assertFalse( grid.addShip( ship ) );

		grid = new Grid( playableGridSize ); // rebuild the board grid
		ship = new Ship(0, 0, 5, "S" );
		assertFalse( grid.addShip( ship ) );

		grid = new Grid( playableGridSize ); // rebuild the board grid
		ship = new Ship(-1, -1, 5, "S" );
		assertFalse( grid.addShip( ship ) );

		grid = new Grid( playableGridSize ); // rebuild the board grid
		ship = new Ship(11, 11, 5, "S" );
		assertFalse( grid.addShip( ship ) );

		//=========================================================================
		// TEST WITH PLACING SHIPS ON TOP OF EACH OTHER

		length = 5;
		posVertical = 1;
		posHorizontal = 1;
		grid = new Grid( playableGridSize ); // rebuild the board grid
		ship = new Ship(posVertical, posHorizontal, length, "S" );
		assertTrue( grid.addShip( ship ) );

		length = 1;
		posVertical = 3;
		posHorizontal = 1;
		ship = new Ship(posVertical, posHorizontal, length, "S" );
		assertFalse( grid.addShip( ship ) );

	}


	@Test
	void checkPlayableGridPosition()
	{
		Integer playableGridSize;
		playableGridSize = 10;
		Grid grid;
		grid = new Grid( playableGridSize ); // rebuild the board grid

		assertFalse( grid.checkPlayableGridPosition( -1,-1 ) );
		assertFalse( grid.checkPlayableGridPosition( 0,0 ) );
		assertTrue( grid.checkPlayableGridPosition( 1,1 ) );
		assertTrue( grid.checkPlayableGridPosition( 1,playableGridSize ) );
		assertTrue( grid.checkPlayableGridPosition( playableGridSize, playableGridSize ) );
		assertFalse( grid.checkPlayableGridPosition( playableGridSize + 1,playableGridSize ) );
		assertFalse( grid.checkPlayableGridPosition( playableGridSize + 1,playableGridSize +1 ) );
		assertFalse( grid.checkPlayableGridPosition( playableGridSize,playableGridSize +1 ) );
	}

	@Test
	void checkPlayableGridSingleCoordinate()
	{
		Integer playableGridSize = 10;
		Grid grid = new Grid( playableGridSize );

		assertFalse( grid.checkPlayableGridSingleCoordinate( -1 ) );
		assertFalse( grid.checkPlayableGridSingleCoordinate( 0 ) );
		assertTrue( grid.checkPlayableGridSingleCoordinate( 1 ) );
		assertTrue( grid.checkPlayableGridSingleCoordinate( 2 ) );
		assertTrue( grid.checkPlayableGridSingleCoordinate( playableGridSize ) );
		assertFalse( grid.checkPlayableGridSingleCoordinate( playableGridSize + 1 ) );
	}

	@Test
	/**
	 * Test with a blank board with size different sizes
	 */
	void displayGridString()
	{
		Integer playableGridSize;
		Player player;
		String actual, expected = "";


		// Test with below  minimum allowed size, which should default to grid size 10.
		playableGridSize = 2;
		player = new Player( playableGridSize, "Player" );

		actual = player.displayGridString();
		expected =
			"+-------------------------------------------+\n" +
				"|   | A | B | C | D | E | F | G | H | I | J |\n" +
				"|---|---|---|---|---|---|---|---|---|---|---|\n" +
				"| 1 |   |   |   |   |   |   |   |   |   |   |\n" +
				"|---|---|---|---|---|---|---|---|---|---|---|\n" +
				"| 2 |   |   |   |   |   |   |   |   |   |   |\n" +
				"|---|---|---|---|---|---|---|---|---|---|---|\n" +
				"| 3 |   |   |   |   |   |   |   |   |   |   |\n" +
				"|---|---|---|---|---|---|---|---|---|---|---|\n" +
				"| 4 |   |   |   |   |   |   |   |   |   |   |\n" +
				"|---|---|---|---|---|---|---|---|---|---|---|\n" +
				"| 5 |   |   |   |   |   |   |   |   |   |   |\n" +
				"|---|---|---|---|---|---|---|---|---|---|---|\n" +
				"| 6 |   |   |   |   |   |   |   |   |   |   |\n" +
				"|---|---|---|---|---|---|---|---|---|---|---|\n" +
				"| 7 |   |   |   |   |   |   |   |   |   |   |\n" +
				"|---|---|---|---|---|---|---|---|---|---|---|\n" +
				"| 8 |   |   |   |   |   |   |   |   |   |   |\n" +
				"|---|---|---|---|---|---|---|---|---|---|---|\n" +
				"| 9 |   |   |   |   |   |   |   |   |   |   |\n" +
				"|---|---|---|---|---|---|---|---|---|---|---|\n" +
				"|10 |   |   |   |   |   |   |   |   |   |   |\n" +
				"+-------------------------------------------+\n";
		assertEquals( expected, actual );


		// Test the minimum allowed size
		playableGridSize = 10;
		player = new Player( playableGridSize, "Player" );

		actual = player.displayGridString();
		expected =
			"+-------------------------------------------+\n" +
			"|   | A | B | C | D | E | F | G | H | I | J |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 1 |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 2 |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 3 |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 4 |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 5 |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 6 |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 7 |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 8 |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 9 |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|10 |   |   |   |   |   |   |   |   |   |   |\n" +
			"+-------------------------------------------+\n";
		assertEquals( expected, actual );


		// Test maximum board size
		playableGridSize = 26;
		player = new Player( playableGridSize, "Player" );

		actual = player.displayGridString();
		expected = "+-----------------------------------------------------------------------------------------------------------+\n" +
			"|   | A | B | C | D | E | F | G | H | I | J | K | L | M | N | O | P | Q | R | S | T | U | V | W | X | Y | Z |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 1 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 2 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 3 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 4 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 5 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 6 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 7 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 8 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 9 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|10 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|11 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|12 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|13 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|14 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|15 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|16 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|17 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|18 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|19 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|20 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|21 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|22 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|23 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|24 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|25 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|26 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"+-----------------------------------------------------------------------------------------------------------+\n";
		assertEquals( expected, actual );


		// Test maximum board size
		playableGridSize = 48;
		player = new Player( playableGridSize, "Player" );

		actual = player.displayGridString();
		expected = "+-----------------------------------------------------------------------------------------------------------+\n" +
			"|   | A | B | C | D | E | F | G | H | I | J | K | L | M | N | O | P | Q | R | S | T | U | V | W | X | Y | Z |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 1 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 2 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 3 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 4 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 5 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 6 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 7 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 8 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 9 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|10 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|11 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|12 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|13 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|14 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|15 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|16 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|17 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|18 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|19 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|20 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|21 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|22 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|23 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|24 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|25 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|26 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
			"+-----------------------------------------------------------------------------------------------------------+\n";
		assertEquals( expected, actual );


		// TEST WITH SHIPS ON THE BOARD

		// Test with five ships starting in upper left going from smallest to largest directed south
		playableGridSize = 10;
		player = new Player( playableGridSize, "Player" );
		Ship ship;
		int length, posHoriztonal, posVertical;
		String direction;


		direction = "N";

		posHoriztonal = 2;
		posVertical = 2;
		length = 2;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertTrue( player.putShip( ship ) );

		posHoriztonal = 3;
		posVertical = 3;
		length = 3;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertTrue( player.putShip( ship ) );

		posHoriztonal = 4;
		posVertical = 4;
		length = 4;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertTrue( player.putShip( ship ) );

		posHoriztonal = 5;
		posVertical = 5;
		length = 5;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertTrue( player.putShip( ship ) );


		actual = player.displayGridString();
		expected =
			"+-------------------------------------------+\n" +
			"|   | A | B | C | D | E | F | G | H | I | J |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 1 |   | @ | @ | @ | @ |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 2 |   | @ | @ | @ | @ |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 3 |   |   | @ | @ | @ |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 4 |   |   |   | @ | @ |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 5 |   |   |   |   | @ |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 6 |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 7 |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 8 |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 9 |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|10 |   |   |   |   |   |   |   |   |   |   |\n" +
			"+-------------------------------------------+\n";
		assertEquals( expected, actual );

		// ========================================================================

		player = new Player( playableGridSize, "Player" );

		direction = "S";

		posHoriztonal = Positions.translateLetterToHorizontal( 'C' );
		posVertical = 3;
		length = 5;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertTrue( player.putShip( ship ) );


		actual = player.displayGridString();
		expected =
			"+-------------------------------------------+\n" +
			"|   | A | B | C | D | E | F | G | H | I | J |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 1 |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 2 |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 3 |   |   | @ |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 4 |   |   | @ |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 5 |   |   | @ |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 6 |   |   | @ |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 7 |   |   | @ |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 8 |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"| 9 |   |   |   |   |   |   |   |   |   |   |\n" +
			"|---|---|---|---|---|---|---|---|---|---|---|\n" +
			"|10 |   |   |   |   |   |   |   |   |   |   |\n" +
			"+-------------------------------------------+\n";
		assertEquals( expected, actual );

	}


	@Test
	void getGridCell()
	{
		Integer playableGridSize = 10;
		Grid grid;
		GridCell actual;
		Ship ship;
		String expected = "   ";


		// Test on a new blank grid
		grid = new Grid( playableGridSize ); // rebuild the board grid

		// Far upper left position of the board
		actual =  grid.getGridCell( 1,1 );
		assertEquals( expected, actual.getContents() );

		// Little bit inward position of the board
		actual =  grid.getGridCell( 2,2 );
		assertEquals( expected, actual.getContents() );

		// Far lower right position of the board
		actual =  grid.getGridCell( playableGridSize, playableGridSize );
		assertEquals( expected, actual.getContents() );




		// Test on getting an occupied space

		// Far upper left position of the board
		grid = new Grid( playableGridSize ); // rebuild the board grid
		ship = new Ship(1, 1, 1, "N" );
		grid.addShip( ship );
		actual =  grid.getGridCell( 1, 1 );
		assertEquals( ship.getContents(), actual.getContents() );

		grid = new Grid( playableGridSize ); // rebuild the board grid
		ship = new Ship(1, 1, 5, "S" );
		grid.addShip( ship );
		actual =  grid.getGridCell( 1, 1 );
		assertEquals( ship.getContents(), actual.getContents() );

		// Far lower right position of the board
		grid = new Grid( playableGridSize ); // rebuild the board grid
		ship = new Ship(playableGridSize, playableGridSize, 5, "N" );
		grid.addShip( ship );
		actual =  grid.getGridCell( playableGridSize, playableGridSize );
		assertEquals( ship.getContents(), actual.getContents() );

	}


	@Test
	void setGridCell()
	{
		Integer playableGridSize = 10;
		Grid grid;
		GridCell result = new GridCell( "   " );


		// Test on a new blank grid
		grid = new Grid( playableGridSize ); // rebuild the board grid

		assertFalse( grid.setGridCell( result, -1, -1 ) );
		assertFalse( grid.setGridCell( result, 0, -1 ) );
		assertFalse( grid.setGridCell( result, -1, 0 ) );
		assertFalse( grid.setGridCell( result, 0, 0 ) );
		assertFalse( grid.setGridCell( result, 0, 1 ) );
		assertFalse( grid.setGridCell( result, 1, 0 ) );
		assertTrue( grid.setGridCell( result, 1, 1 ) );
		assertTrue( grid.setGridCell( result, playableGridSize, playableGridSize ) );
		assertFalse( grid.setGridCell( result, playableGridSize, playableGridSize + 1 ) );
	}

	@Test
	void setAndGetGridCell()
	{
		Integer playableGridSize = 10;
		int posHoriztonal = 1;
		int posVertical = 1;
		Grid grid;
		GridCell actual;
		String expected;

		// Test on a new blank grid
		grid = new Grid( playableGridSize ); // rebuild the board grid


		// Valid coordinates with various string contents
		expected = "   ";
		actual = new GridCell( expected );
		grid.setGridCell( actual, posHoriztonal, posVertical );
		assertEquals( expected, grid.getGridCell( posHoriztonal, posVertical ).getContents() );

		expected = " @ ";
		actual = new GridCell( expected );
		grid.setGridCell( actual, posHoriztonal, posVertical );
		assertEquals( expected, grid.getGridCell( posHoriztonal, posVertical ).getContents() );

//		System.out.println("Grid contents '" + grid.getGridCell( posHoriztonal, posHoriztonal ).getContents() + "'");

		expected = " X ";
		actual = new GridCell( expected );
		grid.setGridCell( actual, posHoriztonal, posVertical );
		assertEquals( expected, grid.getGridCell( posHoriztonal, posVertical ).getContents() );

		expected = " . ";
		actual = new GridCell( expected );
		grid.setGridCell( actual, posHoriztonal, posVertical );
		assertEquals( expected, grid.getGridCell( posHoriztonal, posVertical ).getContents() );

		posHoriztonal = 2;
		posVertical = 2;

		expected = "   ";
		actual = new GridCell( expected );
		grid.setGridCell( actual, posHoriztonal, posVertical );
		assertEquals( expected, grid.getGridCell( posHoriztonal, posVertical ).getContents() );

		expected = " @ ";
		actual = new GridCell( expected );
		grid.setGridCell( actual, posHoriztonal, posVertical );
		assertEquals( expected, grid.getGridCell( posHoriztonal, posVertical ).getContents() );

//		System.out.println("Grid contents '" + grid.getGridCell( posHoriztonal, posHoriztonal ).getContents() + "'");

		expected = " X ";
		actual = new GridCell( expected );
		grid.setGridCell( actual, posHoriztonal, posVertical );
		assertEquals( expected, grid.getGridCell( posHoriztonal, posVertical ).getContents() );

		expected = " . ";
		actual = new GridCell( expected );
		grid.setGridCell( actual, posHoriztonal, posVertical );
		assertEquals( expected, grid.getGridCell( posHoriztonal, posVertical ).getContents() );


		posHoriztonal = playableGridSize;
		posVertical = playableGridSize;

		expected = "   ";
		actual = new GridCell( expected );
		grid.setGridCell( actual, posHoriztonal, posVertical );
		assertEquals( expected, grid.getGridCell( posHoriztonal, posVertical ).getContents() );

		expected = " @ ";
		actual = new GridCell( expected );
		grid.setGridCell( actual, posHoriztonal, posVertical );
		assertEquals( expected, grid.getGridCell( posHoriztonal, posVertical ).getContents() );

//		System.out.println("Grid contents '" + grid.getGridCell( posHoriztonal, posHoriztonal ).getContents() + "'");

		expected = " X ";
		actual = new GridCell( expected );
		grid.setGridCell( actual, posHoriztonal, posVertical );
		assertEquals( expected, grid.getGridCell( posHoriztonal, posVertical ).getContents() );

		expected = " . ";
		actual = new GridCell( expected );
		grid.setGridCell( actual, posHoriztonal, posVertical );
		assertEquals( expected, grid.getGridCell( posHoriztonal, posVertical ).getContents() );


	}

}