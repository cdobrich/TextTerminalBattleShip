package game.battleship.grid.objects;

import game.battleship.grid.Positions;

/**
 * Architecture Design comment(s):
 *
 * Ships do not know about the grid, only about themselves and their positions.
 * Determination of valid positioning is left to the Grid object.
 * Determination of valid sizes for the ship (between 2 and 5) are left to the Player. You can create a ship of any size greater than zero.
 *
 *
 * Acceptable direction values are N, S, E, W, which help determine the layout of the ship on the game.battleship.grid.
 *
 */
public class Ship
{
	private String contents = " @ "; //
	private String direction; //
	private Integer length;
	private Integer positionHorizontal;
	private Integer positionVertical;

	/**
	 *  @param positionVertical
	 * @param positionHorizontal
	 * @param length
	 * @param direction
	 */
	public Ship( int positionVertical, int positionHorizontal, int length, String direction )
	{
		this.direction = direction;
		this.length = length;
		this.positionHorizontal = positionHorizontal;
		this.positionVertical = positionVertical;
	}

	/**
	 * Same as main constructor but allows for passing character label instead of numeric one.
	 * @param positionVertical
	 * @param positionHorizontal
	 * @param length
	 * @param direction
	 */
	public Ship( int positionVertical, Character positionHorizontal, int length, String direction )
	{
		this( positionVertical, Positions.translateLetterToHorizontal(positionHorizontal), length, direction);
	}

	public String getContents() {	return contents; }

	public String getDirection()
	{
		return direction;
	}

	public Integer getLength() { return length; }

	public Integer getPositionHorizontal() {	return positionHorizontal;	}

	public Integer getPositionVertical() { return positionVertical;	}

	@Override
	public String toString()
	{
		return "Ship{ " +
			"contents='" + contents + '\'' +
			", dir.='" + direction + '\'' +
			", length=" + length +
			", location Horz=" + positionHorizontal +
			", Vert=" + positionVertical +
			" }";
	}
}
