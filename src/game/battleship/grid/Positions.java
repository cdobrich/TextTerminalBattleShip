package game.battleship.grid;

/**
 * Position and Coordinate Translator utilities. Allows them to be referenced by Players, Sessions, and Grid all in one place.
 */
public class Positions
{
	// The letters' positions with the array represent their numeric value in the grid across the horizon, going from left to right.
	// There may be other ways to do this, but this was simple enough for the demo and I did not want to rely on ASCII code values alone
	final static char[] horizontalLabels = { ' ','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z', };
	public static char[] getHorizontalLabels()
	{
		return horizontalLabels;
	}

	/**
	 * Translate an input integer value into a corresponding letter value within the letters array, based on it's position.
	 * @param positionHorizontal
	 * @return a letter represented by its numeric position in the letter's list.
	 */
	public static Character translateHorizontalNumberPositionToLetterLabel( Integer positionHorizontal )
	{
		return horizontalLabels[positionHorizontal];
	}

	/**
	 * Translate an input letter to a numeric value in the grid across the horizon, going from left to right.
	 * NOTE: In an ideal world we might use a faster search method, but sequential search is very quick given the small size of the data, plus allows for arbitrary non-sorted labels if necessary.
	 * @param positionHorizontal
	 * @return An integer value derived from input letter's position within the letter's list, or zero if not found.
	 */
	public static Integer translateLetterToHorizontal(Character positionHorizontal)
	{
		for( int i = 1; i < horizontalLabels.length; i++ )
		{
			if( horizontalLabels[i] == positionHorizontal )
			{
				return i;
			}
		}
		return 0;
	}

}
