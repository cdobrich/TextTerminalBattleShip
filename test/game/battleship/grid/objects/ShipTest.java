package game.battleship.grid.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest
{
	@Test
	void getPositionHorizontal()
	{
		Ship ship = new Ship(1, 1, 5, "S" );
		assertEquals( new Integer(1), ship.getPositionHorizontal() );
		ship = new Ship(10, 10, 5, "S" );
		assertEquals( new Integer(10), ship.getPositionHorizontal() );
		ship = new Ship(0, 0, 5, "S" );
		assertEquals( new Integer(0), ship.getPositionHorizontal() );
	}

	@Test
	void getPositionVertical()
	{
		Ship ship = new Ship(1, 1, 5, "S" );
		assertEquals( new Integer(1), ship.getPositionVertical() );
		ship = new Ship(10, 10, 5, "S" );
		assertEquals( new Integer(10), ship.getPositionVertical() );
		ship = new Ship(0, 0, 5, "S" );
		assertEquals( new Integer(0), ship.getPositionHorizontal() );
	}
}