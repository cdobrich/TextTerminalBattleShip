import game.battleship.Player;
import game.battleship.Session;

public class Main
{
	public static void main( String[] args ) throws ClassNotFoundException
	{
		Integer boardSize = 10;

		// Test the grid display
		Session session = new Session(boardSize);
		session.start2();
	}

}
