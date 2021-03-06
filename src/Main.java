import game.battleship.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * DEVELOPER'S NOTES
 *
 * COMPUTER BEHAVIOR COMMENTS:
 *    When I limit the computer to only selecting areas not previously targeted, it usually wins. This behavior makes sense.
 *    If I allow one computer to play without striking previous targets, and the other be allowed to hit previous targets, the one allowed to strike previous targets usually loses. This behavior also makes sense.
 *    This does not matter if the player or the computer goes first. (I have randomized who starts first.)
 *    I have also confirmed each player takes the same number of turns as the winner, plus or minus 1.
 *
 * ABOUT THE GRAPHICS:
 *    While these are not part of the requirements, I created/added basic graphics because they were useful in diagnosing and confirming correct code behavior during development.
 *    Also I figured it wouldn't hurt to impress a little with the effort.
 *
 */
public class Main
{
	public static void main( String[] args ) throws ClassNotFoundException
	{
		Integer boardSize = 10;

		System.out.println("Welcome to Battle Ship");
		System.out.println();
		System.out.println("Select your preferred play options:");
		System.out.println();
		System.out.println("1: Player vs Computer manual play");
		System.out.println("2: Computer vs computer quick play with limited output. This is the requested 'debug mode' feature.");
		System.out.println("3: Computer vs computer quick play with full output");
		System.out.println();

		try
		{
			System.out.println();
			System.out.print("  Choice: ");

			BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
			String inputline = is.readLine();

			if( inputline.contentEquals( "1" ))
			{ //  Player vs Computer manual play
				Session session = new Session(boardSize);
				session.startPlayerVsComputer( true, true, false );
			}
			else if( inputline.contentEquals( "2" ))
			{ // Computer vs computer quick play with limited output
				System.out.println("Ships are represented by '@' lines, hits are represented by 'X' marks, and misses are represented by '.' marks");
				Session session = new Session(boardSize);
				session.startComputerVsComputer( true, false );
			}
			else if( inputline.contentEquals( "3" ))
			{ // Computer vs computer quick play with full output
				System.out.println("Ships are represented by '@' lines, hits are represented by 'X' marks, and misses are represented by '.' marks");
				Session session = new Session(boardSize);
				session.startComputerVsComputer( true, true );
			}
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}

	}
}
