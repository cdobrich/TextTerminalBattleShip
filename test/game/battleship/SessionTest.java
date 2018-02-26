package game.battleship;

import game.battleship.grid.Grid;
import game.battleship.grid.GridTarget;
import game.battleship.grid.objects.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest
{
	@Test
	void generateShipAtRandomPosition( )
	{
		Integer playableGridSize = 10;
		Grid grid = new Grid( playableGridSize );
		Session session = new Session( playableGridSize );
		int length;
		Ship ship;

		// Check whether generated ship is within bounds
		length = 5;
		ship = session.generateShipAtRandomPosition( length );
		assertTrue( grid.checkPlayableGridSingleCoordinate( ship.getPositionVertical() ) );
		assertTrue( grid.checkPlayableGridSingleCoordinate( ship.getPositionHorizontal() ) );

		length = 4;
		ship = session.generateShipAtRandomPosition( length );
		assertTrue( grid.checkPlayableGridSingleCoordinate( ship.getPositionVertical() ) );
		assertTrue( grid.checkPlayableGridSingleCoordinate( ship.getPositionHorizontal() ) );

		length = 3;
		ship = session.generateShipAtRandomPosition( length );
		assertTrue( grid.checkPlayableGridSingleCoordinate( ship.getPositionVertical() ) );
		assertTrue( grid.checkPlayableGridSingleCoordinate( ship.getPositionHorizontal() ) );

		length = 2;
		ship = session.generateShipAtRandomPosition( length );
		assertTrue( grid.checkPlayableGridSingleCoordinate( ship.getPositionVertical() ) );
		assertTrue( grid.checkPlayableGridSingleCoordinate( ship.getPositionHorizontal() ) );

		length = 1;
		ship = session.generateShipAtRandomPosition( length );
		assertTrue( grid.checkPlayableGridSingleCoordinate( ship.getPositionVertical() ) );
		assertTrue( grid.checkPlayableGridSingleCoordinate( ship.getPositionHorizontal() ) );

	}

	@Test
	void generateRandomTargetPosition()
	{
		Integer playableGridSize = 10;
		Grid grid = new Grid( playableGridSize );
		Session session = new Session( playableGridSize );
		GridTarget gridTarget;

		gridTarget = session.generateRandomTargetPosition( );

		assertTrue( grid.checkPlayableGridSingleCoordinate( gridTarget.getVertical() ) );
		assertTrue( grid.checkPlayableGridSingleCoordinate( gridTarget.getHorizontal() ) );
		assertTrue( grid.checkPlayableGridPosition( gridTarget.getVertical(), gridTarget.getHorizontal() ) );
	}

	// Test would be more difficult due to randomized positions
	// Skipping implementation for now due to desire for quicker development demonstration
//	@Test
//	void putShipPiecesOntoBoard()
//	{
//	}


}