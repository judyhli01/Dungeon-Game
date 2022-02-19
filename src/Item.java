/** Representation of an Item */
public class Item {
	/** Item name */
	private String name;
	/** The amount of gold the item is worth */
	private int value;
	/** The type of item it is */
	private char type;

	/**
	 * Item constructor creates an Item object
	 * @param n Name of the item object
	 * @param v Gold value
	 * @param t The type of item
	 */
	public Item(String n, int v, char t) {
		name = n;
		value = v;
		type = t;
	}

	/**
	 * Gets of name of item
	 * @return Name of item
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the Type of the item
	 * @return A char that represents the type of the Item
	 */
	public char getType() {
		return type;
	}

	/**
	 * Gets the Value of the item
	 * @return A char that represents the Value of the Item
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Clones a copy of Item
	 * @return A item object
	 */
	public Item clone() {
		return new Item(name, value, type);
	}

}