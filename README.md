# README #

A very simple console version of Battleship, written by Clifton Dobrich. All source code is copyright Clifton Dobrich. Please do not copy or modify this code without permission and also please do give notice and credit.

### What is this repository for? ###

* This is my own implementation of the game Battleship, with simple limitations and very simple custom console text graphics. I made this as a demonstration of my coding abilities for a job interview over the course of several hours.
* Version 1.0: Complete
* Limitations: The board game size may range between 10x10 and 26x26, inclusively. This is set as a hardcoded variable in the 'src/Main.java' file, but could be made configurable. Changing the value to any desired range with limits and recompiling will demonstrate its ability to adjust.

### Build Software Version Compatibility ###

In theory there is nothing special about this code base, but it was built using Java 8. So if all else fails use that.

### How do I build the program? ###

Assuming you have a compatible java compiler on your system, download the file, change into the 'TextTerminalBattleShip/src' directory, and enter the following command in your terminal console of choice:

	javac Main.java

### How do I run the application? ###

If you built the program successfully, change into the 'TextTerminalBattleShip/src' directory where there should now be a Main.class file. Enter the following command in your terminal console of choice:

	java Main.class

Then select how you want to play.

### What do the 3-Play modes mean? ###

Upon starting the program, the user is prompted to select a play-mode, from 1 of 3 choices.

Choice 1: User manually plays against the computer.
Choice 2: The program will set two computers to play against each other, showing the beginning board (with all ship pieces visible for the user's benefit) and then two final boards with the outcome of the game.
Choice 3: Same as Choice 2 but shows more detailed play-by-play description of each step taken in the program with two computers against each other.

### How do I setup the development environment? ###

* Set up: There is an intellij project file preset with this program. But in general all you need to do is try to compile the 'src/Main.java' file and the rest will follow. So load the folder into your editor of choice and get coding.
* Configuration: This program uses simple pure Java to run. It should be compatible with Java versions 6, 7, 8 and 9. But it was built using version 8. The main program uses only pure Java.
* Dependencies: The Unit tests are built with JUnit and that is the only dependency if you want to run them. They should be included in your system path.
* How to run the tests: The 'tests' folder has JUnit tests for each class and its various methods. They can be invoked independently to test functionality.

### Contribution guidelines ###

I'm not generally looking for code contributions but I am willing to listen. The original purpose of this project was simply to demonstrate my ability to quickly create a fully working project with a neat and organized manner.

### Who do I talk to? ###

* Repo owner: This project and all its source code files and accompanying files are owned by Clifton Dobrich. 
