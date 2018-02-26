package game.battleship.grid.objects;

import game.battleship.grid.Positions;
import java.util.ArrayList;

/**
 * Architecture Design comment(s):
 *
 * Ships do not know about the grid, only about themselves and their positions.
 * They leave the grid to determine if their position is vaid for play.
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

	private ArrayList<GridCell> subsections = new ArrayList<>(); // TODO: Do we really need this?

	/**
	 *
	 * @param positionVertical
	 * @param positionHorizontal
	 * @param length
	 * @param direction
	 */
	public Ship( Integer positionVertical, Integer positionHorizontal, Integer length, String direction )
	{
		this.direction = direction;
		this.length = length;
		this.positionHorizontal = positionHorizontal;
		this.positionVertical = positionVertical;

		for( int i = 0; i < length; i++ )
		{
			GridCell section = new GridCell( contents );
			subsections.add( section );
		}
	}

	/**
	 * Same as main constructor but allows for passing character label instead of numeric one.
	 * @param positionVertical
	 * @param positionHorizontal
	 * @param length
	 * @param direction
	 */
	public Ship( Integer positionVertical, Character positionHorizontal, Integer length, String direction )
	{
		this( positionVertical, Positions.translateLetterToHorizontal(positionHorizontal), length, direction);
	}

	public String getContents()
	{
		return contents;
	}

	public String getDirection()
	{
		return direction;
	}

	public Integer getLength()
	{
		return length;
	}

	public Integer getPositionHorizontal()
	{
		return positionHorizontal;
	}

	public Integer getPositionVertical()
	{
		return positionVertical;
	}

}
