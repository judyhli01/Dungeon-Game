import java.util.*;
import java.io.*;

/** ItemGenerator is a representation of a Item Generator */
public class ItemGenerator {
	/** Array list of all items */
	private ArrayList<Item> itemList;
	/** An instance of ItemGenerator */
	private static ItemGenerator instance = null;

	/** ItemGenerator Constructor */
	private ItemGenerator() {
		try {
			this.itemList = new ArrayList<Item>();
			File inputFile = new File("ItemList.txt");
			Scanner inFile = new Scanner(inputFile);
			while (inFile.hasNext()) {
				String line = inFile.nextLine();
				String[] lineArray = line.split(",");
				int value = Integer.parseInt(lineArray[1]);
				Item item = new Item(lineArray[0], value, lineArray[2].charAt(0));
				itemList.add(item);
			}
			inFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}

	/**
	 * Gets a Potion
	 * @return A potion item
	 */
	public Item getPotion() {
		Item i = itemList.get(0);
		return i;
	}

	/**
	 * Gets a Key
	 * @return A Key item
	 */
	public Item getKey() {
		Item i = itemList.get(1);
		return i;
	}

	/**
	 * Gets the ItemGenerator instance (singleton)
	 * @return A instance of ItemGenerator
	 */
	public static ItemGenerator getInstance() {
		if (instance == null) {
			instance = new ItemGenerator();
		}
		return instance;
	}

	/**
	 * Generates an item
	 * @return A item object
	 */
	public Item generateItem() {
		Random rand = new Random();
		int randint = rand.nextInt(itemList.size());
		Item item = itemList.get(randint);
		Item genitem = item.clone();
		return genitem;
	}
}
