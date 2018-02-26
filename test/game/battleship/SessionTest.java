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


//	@Test
//	void declareWinner()
//	{
//		Integer playableGridSize;
//		playableGridSize = 10;
//		Player player1 = new Player( playableGridSize, "Player1" );
//		for( int i = player1.getGamePoints(); i > 0; i-- )
//		{
//			player1.decrementGamePoints();
//		}
//		assertEquals( 0, player1.getGamePoints() );
//
//
//		Player player2 = new Player( playableGridSize, "Player2" );
//		for( int i = player2.getGamePoints(); i > 0; i-- )
//		{
//			player2.decrementGamePoints();
//		}
//		assertEquals( 0, player2.getGamePoints() );
//	}



}