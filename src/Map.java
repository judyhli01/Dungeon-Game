import java.awt.Point;
import java.io.*;
import java.util.Scanner;
import java.io.File;

/** Map represents the map */
public class Map {
	/** Map that stores the characters in each room */
	private char[][] map = new char[5][5];
	/** Map that stored boolean values */
	private boolean[][] revealed = new boolean[5][5];
	/** An instance of Map */
	private static Map instance = null;

	/**
	 * Default Constructor creates map as a 5x5 matrix with 'x' values and creates
	 * revealed as a 5x5 matrix with all false values
	 */
	private Map() {
		for (int i = 0; i < map.length; i++) {
			map[i] = new char[] { 'x', 'x', 'x', 'x', 'x' };
		}
		for (int i = 0; i < map.length; i++) {
			revealed[i] = new boolean[] { false, false, false, false, false };
		}
	}

	/**
	 * Gets the Map instance (singleton)
	 * @return the instance of Map
	 */
	public static Map getInstance() {
		instance = new Map();
		return instance;
	}

	/**
	 * loads each char in the .txt file (Map1.txt, Map2.txt, Map3.txt) into map
	 */
	public void loadMap(int mapNum) {
		Scanner read;
		try {
			read = new Scanner(new File("Map" + mapNum + ".txt"));
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					String str = read.next();
					char c = str.charAt(0);
					map[i][j] = c;
				}
			}
		} catch (FileNotFoundException fnf) {
			System.out.println("File was not found");
		}
	}

	/**
	 * Gets the hidden character at the location of the user
	 * @return 's' for start, 'f' for finish, 'm' for monster, 'i' for item, 'n' for
	 *         nothing
	 */
	public char getCharAtLoc(Point p) {
		int x = (int) p.getX();
		int y = (int) p.getY();
		if (x > 5 || y > 5)
			return 'x';
		return map[5 - y - 1][x];
	}

	/**
	 * displays the map with revealed and concealed characters and user location.
	 * @param p provides the location of the user
	 */
	public void displayMap(Point p) {
		int x = (int) p.getX();
		int y = (int) p.getY();
		System.out.println("━━━━━━━━━━━━━");
		for (int i = 0; i != map.length; i++) {
			System.out.print("| ");
			for (int j = 0; j != map[i].length; j++) {
				if (map.length - i - 1 == y && j == x) {
					System.out.print("*" + " ");
				} else if (revealed[i][j] == true) {
					System.out.print(map[i][j] + " ");
				} else {
					System.out.print("x" + " ");
				}
			}
			System.out.println("|");
		}
		System.out.println("━━━━━━━━━━━━━");
	}

	/**
	 * finds the starting point of the map
	 * @return the Point at the start of each map
	 */
	public Point findStart() {
		Point startPoint = new Point();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 's') {
					startPoint.setLocation(j, 5 - i - 1);
				}
			}
		}
		return startPoint;
	}

	/**
	 * At point p, value is true
	 * @param p provides the location of the user
	 */
	public void reveal(Point p) {
		int x = (int) p.getX();
		int y = (int) p.getY();
		revealed[5 - y - 1][x] = true;
	}

	/**
	 * turns 's','f','m', and 'i' into 'n' for nothing
	 * @param p provides the location of the user
	 */
	public void removeCharAtLoc(Point p) {
		int x = (int) p.getX();
		int y = (int) p.getY();
		map[5 - y - 1][x] = 'n';
	}
}