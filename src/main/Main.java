package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import parser.Parser;
import command.*;
import entity.*;
import room.DirectorOffice;
import room.Room;
import room.RoomGold;
import room.RoomPass;
import room.Wall;
import timer.Timer;
import item.*;
import item.Map;

public class Main {
	// If the player is hidden
	private int hidden = 0;
	// the map
	private Room[][] bankMap;

	// the player
	private Player hero;
	// the current coordinate of the character : table with x and y
	private int[] coordinate;
	// the current room where the character is
	private Room currentRoom;
	private int[] finishRoom;
	Timer time;
	// the list of guards
	private List<Guards> listGuards;
	// the parser
	private Parser parser;

	// ======================================================================

	/**
	 * Constructor - creates a Main object with a specified length and width
	 * 
	 * @param length
	 * @param width
	 */
	public Main(int length, int width) {
		time = new Timer(900);
		// initialize the parser
		parser = new Parser();

		coordinate = new int[2];
		// the map has the same size that the user want
		bankMap = new Room[length][width];
		Map map = new Map(bankMap);
		Clock clock = new Clock(time);
		listGuards = new ArrayList<Guards>();
		// the hero has a weight limit of 100
		iniMap();
		for (int i = 0; i < bankMap.length; i++) {
			for (int j = 0; j < bankMap[0].length; j++) {
				if (bankMap[i][j] == null) {

					throw new RuntimeException("error");
				}
			}
		}
		initExit();
		// he begins in (x,y)
		hero = new Player(currentRoom, 100, "hero");
		// currentRoom.addEntity(hero);
		hero.init(map);
		hero.init(clock);

	}

	/*
	 * the Win method
	 */
	public boolean win() {
		if ((bankMap[finishRoom[0]][finishRoom[1]].containsEntity(hero))
				&& (hero.getGold() > 0))
			return true;
		return false;
	}

	/**
	 * introduction
	 */

	public void introduction() {
		System.out.println("You are a thief in a bank");
		System.out
				.println("You have some time to go to the treasure room and return to the starting point.");
		System.out
				.println("Be careful, there are guards, if you are seen, it's game over.");
		System.out.println("You will also need keys to move, find them.");
		System.out.println("Let's go!");
	}

	/**
	 * Initialize the map It adds rooms to each box of the table
	 */
	private void iniMap() {
		int maxColumn = bankMap.length;
		int maxLine = bankMap[0].length;
		// the borders are walls
		// borders north and south are walls
		for (int i = 0; i < maxColumn; i++) {
			bankMap[i][0] = new Wall();
			bankMap[i][0].setX(i);
			bankMap[i][0].setY(0);
			bankMap[i][maxLine - 1] = new Wall();
			bankMap[i][maxLine - 1].setX(i);
			bankMap[i][maxLine - 1].setY(maxColumn - 1);
		}
		// borders east and west are walls
		for (int j = 0; j < maxLine; j++) {
			bankMap[0][j] = new Wall();
			bankMap[0][j].setX(0);
			bankMap[0][j].setY(j);
			bankMap[maxColumn - 1][j] = new Wall();
			bankMap[maxColumn - 1][j].setX(maxLine - 1);
			bankMap[maxColumn - 1][j].setY(j);
		}
		List<Character> correctChara = new ArrayList<Character>();
		correctChara.add('P');
		correctChara.add('K');
		correctChara.add('B');
		correctChara.add('I');
		correctChara.add('R');
		correctChara.add('W');
		correctChara.add('G');
		correctChara.add('C');
		correctChara.add('E');
		correctChara.add('M');
		BufferedReader buff;
		int x = 1;
		int y = 1;
		try {
			// we open the text file in reading mode
			String path = System.getProperty("user.dir");
			buff = new BufferedReader(new FileReader(path + "/Map.txt"));
			try {
				char charac = (char) buff.read();
				while (true) {

					addRoomToMap(charac, x, y, buff);
					x++;
					if (x > maxLine - 2) {
						x = 1;
						y++;

						if (y > maxColumn - 2) {
							break;
						}
					}
					charac = (char) buff.read();
					while (!correctChara.contains(charac)) {

						charac = (char) buff.read();
					}
				}
			} finally {
				// we close the text file after using it
				buff.close();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void initExit() {
		Room wall = new Wall();
		for (int i = 1; i < bankMap.length - 1; i++) {
			for (int j = 1; j < bankMap[0].length - 1; j++) {
				if (!bankMap[i][j + 1].equals(wall)) {
					bankMap[i][j].setExit("south", bankMap[i][j + 1]);
				}
				if (!bankMap[i + 1][j].equals(wall)) {
					bankMap[i][j].setExit("east", bankMap[i + 1][j]);
				}
				if (!bankMap[i - 1][j].equals(wall)) {
					bankMap[i][j].setExit("west", bankMap[i - 1][j]);
				}
				if (!bankMap[i][j - 1].equals(wall)) {
					bankMap[i][j].setExit("north", bankMap[i][j - 1]);
				}
			}
		}
	}

	/**
	 * Add a room to the map thanks to a character
	 * 
	 * @param charac
	 *            a char which represents a room
	 * @param x
	 *            x of the map
	 * @param y
	 *            y of the map
	 * @param buff
	 *            the buffer of the file
	 */
	private void addRoomToMap(char charac, int x, int y, BufferedReader buff)
			throws IOException {
		switch (charac) {
		case 'W':
			// wall
			bankMap[x][y] = new Wall();
			break;
		case 'R':
			// room
			bankMap[x][y] = new Room("room" + x + "-" + y, "Awesome room");
			break;
		case 'G':
			// gold room
			bankMap[x][y] = new RoomGold("RoomGold" + x + "-" + y, "pass-G");
			break;
		case 'E':
			bankMap[x][y] = new Room("entry" + x + "-" + y, "Awesome Entry");
			// the hero will be here
			finishRoom = new int[2];
			finishRoom[0] = x;
			finishRoom[1] = y;
			coordinate[0] = x;
			coordinate[1] = y;
			currentRoom = bankMap[x][y];
			break;
		case 'C':
			// room with a box
			bankMap[x][y] = new Room("room" + x + "-" + y,
					"Awesome room with a box in it");
			bankMap[x][y].addItem(new Box());
			break;
		case 'B':
			// director office
			bankMap[x][y] = new DirectorOffice("directoroffice" + x + "-" + y,
					"pass-D");
			break;
		case 'I':
			// room with a stationary guard
			bankMap[x][y] = new Room("room" + x + "-" + y,
					"Awesome room with a stationary guard");
			bankMap[x][y].setX(x);
			bankMap[x][y].setY(y);
			Guards guard = new Guards(bankMap[x][y], false, true, "Guard " + x
					+ "-" + y);
			listGuards.add(guard);
			bankMap[x][y].addEntity(guard);
			break;
		case 'M':
			bankMap[x][y] = new Room("room" + x + "-" + y,
					"Awesome room with a mobile guard at the beginning");
			bankMap[x][y].setX(x);
			bankMap[x][y].setY(y);
			Guards guardMobile = new MovingGuards(bankMap[x][y], false, true,
					"Guard " + x + "-" + y);
			listGuards.add(guardMobile);
			bankMap[x][y].addEntity(guardMobile);
			break;
		case 'P':
			bankMap[x][y] = new RoomPass(
					"awesome room which needs a pass to enter", "pass-"
							+ (char) buff.read());
			break;
		case 'K':
			bankMap[x][y] = new Room("room" + x + "-" + y,
					"Awesome room with a pass in it");
			bankMap[x][y].addItem(new Pass("pass-" + (char) buff.read()));
			break;
		default:
			return;
		}
		bankMap[x][y].setX(x);
		bankMap[x][y].setY(y);
		// TODO le faire en moins sale
	}

	// =====================================================================

	/**
	 * Launch the game
	 */
	public void play() {
		if (hidden != 0) {
			// while finished is false, the game continues
			// if it's the end, finished is true
			boolean finished = false;
			String b = "";
			int score = 0;
			while (!finished) {
				CommandLine cmd = parser.getCommand();
				finished = processCommand(cmd);
				String a = "";
				a = endTurn();

				b = a;
				// condition for game over
				if (a.equals("Game over") || a.equals("Win")) {

					score = hero.getGold();
					finished = true;
				} else {
					System.out.println("Fin du tour");
				}
			}
			if (b.equals("Game over")) {
				score = score / 10;
			} else {
				score += time.getTimer();
			}
			System.out.println("\n" + b + "\n" + "Score final : " + score);

		} else {
			endTurn();
		}
	}

	/**
	 * Process the command entered by the user
	 * 
	 * @param cmd
	 *            a CommandLine which contains all the line that the user has
	 *            entered
	 */
	private boolean processCommand(CommandLine cmd) {
		Command command = cmd.getCommandWord().getCommand();
		if (command.equals(new End())) {
			return true;
		}
		String action = command.act(cmd.getSecondWord(), this);
		printer(action);
		return false;
	}

	/**
	 * Print the String which is pass in parameter
	 * 
	 * @param str
	 *            the String we want to print
	 */
	private void printer(String str) {
		System.out.println(str);
	}

	/**
	 * Process the end of a turn
	 */
	private String endTurn() {
		if (time.decrementTime(5) == false) {
			return "Game over";
		}
		for (int i = 0; i < listGuards.size(); i++) {
			if (listGuards.get(i).act(hero, bankMap)) {
				return "Game over";
			}
		}
		if (win()) {
			return "Win";
		}

		return "Fin du tour";
	}

	// =================================================================
	/**
	 * Get the bankMap
	 * 
	 * @return the bankMap
	 */
	public Room[][] getBankMap() {
		return bankMap;
	}

	/**
	 * Set the bankMap as we want
	 * 
	 * @param bankMap
	 * @throws IllegalArgumentException
	 *             if the new map hasn't the same size than the actual map
	 */
	public void setBankMap(Room[][] bankMap) {
		if (bankMap[0].length != this.bankMap[0].length
				|| bankMap.length != this.bankMap.length) {
			throw new IllegalArgumentException(
					"bankMap passed in setBankMap is incorrect. You can't modify the size of the map");
		}
		this.bankMap = bankMap;
	}

	/**
	 * Get the hero
	 * 
	 * @return the Player hero
	 */
	public Player getHero() {
		return hero;
	}

	/**
	 * Set the hero as we want
	 * 
	 * @param hero
	 */
	public void setHero(Player hero) {
		this.hero = hero;
	}

	/**
	 * get the X coordinate of the hero
	 * 
	 * @return coordinate
	 */
	public int getCoordinateX() {
		return coordinate[0];
	}

	public void setHidden(int val) {
		hidden = val;
	}

	public int getCoordinateY() {
		return coordinate[1];
	}

	/**
	 * set the coordinates as we want
	 * 
	 * @param coordinate
	 * @throws IllegalArgumentException
	 *             if coordinate isn't a table of 2 elements if the new
	 *             coordinates are not correct
	 */
	public void setCoordinate(int[] coordinate) {
		int COORDINATELENGTH = 2;
		// if coordinate is not a table of 2 elements
		if (!(coordinate.length == COORDINATELENGTH)) {
			throw new IllegalArgumentException(
					"coordinate passed in setCoordinate is incorrect. Correct format : int[2] which contains the x and the y");
		}
		// if the x and the y of the new coordinates are not correct (they are
		// too high or too low)
		if (coordinate[0] > bankMap.length || coordinate[1] > bankMap[0].length
				|| coordinate[0] < 0 || coordinate[1] < 0) {
			throw new IllegalArgumentException(
					"coordinate passed in setCoordinate is incorrect. The coordinates are too high or too low.");
		}
		this.coordinate = coordinate;
		currentRoom = bankMap[coordinate[0]][coordinate[1]];
	}

	/**
	 * get the current room
	 * 
	 * @return the current room
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

	// =================================================================

	public static void main(String[] args) {
		Main main = new Main(32, 32);
		main.introduction();
		main.play();
	}

	// For the guards
	public void actGuard() {
		for (Guards g : listGuards) {
			g.act(hero, bankMap);
		}
	}
}
