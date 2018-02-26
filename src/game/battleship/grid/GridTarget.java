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
}
