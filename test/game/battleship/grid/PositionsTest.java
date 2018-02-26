package game.battleship.grid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionsTest
{

	@Test
	void translateHorizontalNumberPositionToLetterLabel()
	{
		Integer positionHorizontal;
		Character result;

		positionHorizontal = 0;
		result = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, result.compareTo( ' ' ) );

		positionHorizontal = 1;
		result = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, result.compareTo( 'A' ) );

		positionHorizontal = 2;
		result = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, result.compareTo( 'B' ) );

		positionHorizontal = 3;
		result = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, result.compareTo( 'C' ) );

		positionHorizontal = 4;
		result = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, result.compareTo( 'D' ) );

		positionHorizontal = 5;
		result = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, result.compareTo( 'E' ) );

		positionHorizontal = 6;
		result = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, result.compareTo( 'F' ) );

		positionHorizontal = 7;
		result = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, result.compareTo( 'G' ) );

		positionHorizontal = 8;
		result = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, result.compareTo( 'H' ) );

		positionHorizontal = 9;
		result = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, result.compareTo( 'I' ) );

		positionHorizontal = 10;
		result = Positions.translateHorizontalNumberPositionToLetterLabel(positionHorizontal);
		assertEquals( 0, result.compareTo( 'J' ) );

	}

}