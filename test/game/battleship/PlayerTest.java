package game.battleship;

import game.battleship.grid.objects.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest
{

	@Test
	void decrementGamePoints()
	{
		// Start with full points and decrease them to nothing
		Integer playableGridSize;
		playableGridSize = 10;
		Player player1 = new Player( playableGridSize, "Player1" );
		for( int i = player1.getGamePoints(); i > 0; i-- )
		{
			player1.decrementGamePoints();
		}
		assertEquals( 0, player1.getGamePoints() );
	}

	@Test
	void putShip()
	{
		Integer boardSize = 10;
		String direction;
		int posHoriztonal;
		int posVertical;
		Player player = new Player( boardSize, "Player" );
		Ship ship;


		// Invalid coordinates
		direction = "S";

		posHoriztonal = 0;
		posVertical = 1;
		int length = 5;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertFalse( player.putShip( ship ) );

		posHoriztonal = 0;
		posVertical = 0;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertFalse( player.putShip( ship ) );

		posHoriztonal = 1;
		posVertical = 0;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertFalse( player.putShip( ship ) );


		// Valid direction for length with nothing occupying the space
		direction = "N";

		posHoriztonal = 1;
		posVertical = 1;
		length = 1;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertTrue( player.putShip( ship ) );

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

		// Clear grid by rebuilding player
		player = new Player( boardSize, "Player" );

		// Valid positions with reversed directions
		direction = "S";

		posHoriztonal = 1;
		posVertical = 1;
		length = 1;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertTrue( player.putShip( ship ) );

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





		// Clear grid by rebuilding player
		player = new Player( boardSize, "Player" );


		// Invalid direction for length
		direction = "N";

		posHoriztonal = 1;
		posVertical = 1;
		length = 5;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertFalse( player.putShip( ship ) );

		direction = "E";
		posHoriztonal = boardSize;
		posVertical = boardSize;
		length = 5;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertFalse( player.putShip( ship ) );

		direction = "E";
		posHoriztonal = boardSize;
		posVertical = boardSize;
		length = 5;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertFalse( player.putShip( ship ) );

		direction = "S";
		posHoriztonal = boardSize;
		posVertical = boardSize;
		length = 5;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertFalse( player.putShip( ship ) );

		direction = "W";
		posHoriztonal = 1;
		posVertical = 1;
		length = 5;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertFalse( player.putShip( ship ) );




		// Clear grid by rebuilding player
		player = new Player( boardSize, "Player" );


		// COLLISION TESTING: Valid positions but collide with other boat
		direction = "W";

		posHoriztonal = 1;
		posVertical = 2;
		length = 1;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertTrue( player.putShip( ship ) );
		posHoriztonal = 2;
		posVertical = 2;
		length = 2;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertFalse( player.putShip( ship ) );

		player = new Player( boardSize, "Player" ); // Clear grid by rebuilding player

		direction = "N";
		posHoriztonal = 5;
		posVertical = 5;
		length = 5;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertTrue( player.putShip( ship ) );
		posHoriztonal = 5;
		posVertical = 6;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertFalse( player.putShip( ship ) );


		// Valid but adjacent to other boats

		// Clear grid by rebuilding player
		player = new Player( boardSize, "Player" );

		posHoriztonal = 5;
		posVertical = 5;
		length = 5;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		player.putShip( ship );
		posHoriztonal = 5;
		posVertical = 10;
		length = 5;
		ship = new Ship(posVertical, posHoriztonal, length, direction);
		assertTrue( player.putShip( ship ) );



	}

}