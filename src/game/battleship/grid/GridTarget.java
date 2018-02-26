
package game.battleship.grid;

/**
 * Simple class to pair the coordinates of the Vertical and Horizontal coordinates neatly.
 */
public class GridTarget
{
	Integer horizontal;
	Integer vertical;

	public GridTarget( Integer vertical, Integer horizontal )
	{
		this.vertical = vertical;
		this.horizontal = horizontal;
	}

	public Integer getHorizontal()
	{
		return horizontal;
	}

	public Integer getVertical()
	{
		return vertical;
	}

	@Override
	/**
	 * Display output is translated, but value stored in memory is not.
	 */
	public String toString() {
		System.out.println("translated from " + horizontal);
		return " " + Positions.translateHorizontalNumberPositionToLetterLabel(horizontal) + vertical;
	}
}
