package game.battleship.grid;

/**
 * Position and Coordinate Translator utilities. Allows them to be referenced by Players, Sessions, and Grid all in one place.
 */
public class Positions
{
	/**
	 * If we wanted at another time these labels could be modified.
	 */
	final static char[] horizontalLabels = { ' ','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z', };
	public static char[] getHorizontalLabels()
	{
		return horizontalLabels;
	}

	public static Character translateHorizontalNumberPositionToLetterLabel( Integer positionHorizontal )
	{
		return horizontalLabels[positionHorizontal];
	}

	/**
	 * Sequential search is very quick given the small size of the data, plus allows for arbitrary non-sorted labels if necessary.
	 * @param positionHorizontal
	 * @return
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
