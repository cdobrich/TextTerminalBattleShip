package game.battleship.grid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionsTest
{

	@Test
	void translateHorizontalNumberPositionToLetterLabel()
	{
		Integer positionHorizontal;
		Character actual;

		positionHorizontal = 0;
		actual = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, actual.compareTo( ' ' ) );

		positionHorizontal = 1;
		actual = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, actual.compareTo( 'A' ) );

		positionHorizontal = 2;
		actual = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, actual.compareTo( 'B' ) );

		positionHorizontal = 3;
		actual = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, actual.compareTo( 'C' ) );

		positionHorizontal = 4;
		actual = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, actual.compareTo( 'D' ) );

		positionHorizontal = 5;
		actual = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, actual.compareTo( 'E' ) );

		positionHorizontal = 6;
		actual = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, actual.compareTo( 'F' ) );

		positionHorizontal = 7;
		actual = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, actual.compareTo( 'G' ) );

		positionHorizontal = 8;
		actual = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, actual.compareTo( 'H' ) );

		positionHorizontal = 9;
		actual = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, actual.compareTo( 'I' ) );

		positionHorizontal = 10;
		actual = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, actual.compareTo( 'J' ) );

	}

}