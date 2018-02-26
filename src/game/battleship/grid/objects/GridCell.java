package game.battleship.grid.objects;

/**
 * GridCell's do not know their coordinates. They only know their display contents and occupied status.
 */
public class GridCell
{
	private String contents;
	boolean occupied = false;

	public GridCell( )
	{
		this.contents = "   "; // default
	}

	public GridCell( String contents )
	{
		this.contents = contents;
	}

	public String getContents()
	{
		return contents;
	}

	public boolean isOccupied()
	{
		return occupied;
	}

	public void setContents( String contents )
	{
		this.contents = contents;
	}

	public void setOccupied( boolean occupied )
	{
		this.occupied = occupied;
	}

	@Override
	public String toString()
	{
		return contents;
	}
}
