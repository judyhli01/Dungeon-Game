import java.lang.Math;
import java.util.ArrayList;
import java.awt.Point;

/** Representation of Hero, subclass of Entity */
public class Hero extends Entity implements Magical {
	/** A list of Item objects that's in hero's inventory */
	private ArrayList<Item> items;
	/** Map object */
	private Map map;
	/** The location of the hero */
	private Point location;
	/** The hero's gold */
	private int gold;

	/**
	 * Default Constructor - initializes a Hero with given it's name, a map object
	 * and sets the Heros location to the starting point of the map
	 */
	public Hero(String n, Map m) {
		super(n, 25);
		items = new ArrayList<Item>();
		map = m;
		location = new Point(map.findStart());
		gold = 200;
	}

	/**
	 * Shows the amount of gold the hero has
	 * @return The number of gold the hero has
	 */
	public int getGold() {
		return gold;
	}

	/**
	 * Adds gold to heros overall gold
	 * @param g The amount of gold collected
	 */
	public void collectGold(int g) {
		gold += g;
	}

	/**
	 * Subtracts the an amount of gold from the total
	 * @param g The amount of gold collected
	 */
	public void spendGold(int g) {
		if (g <= gold) {
			gold -= g;
		}
	}

	/**
	 * Checks inventory for any armor items
	 * @return The index of the first armor item, or -1 if not found
	 */
	public int hasArmorItem() {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getType() == 'a') {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Checks if a key is in hero's inventory
	 * @return True if hero has a key
	 */
	public boolean hasKey() {
		for (Item i : items) {
			if (i.getName().equals("Key")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Discards key from heros inventory
	 */
	public void useKey() {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getName().equals("Key")) {
				dropItem(i);
				return;
			}
		}
	}

	/**
	 * Gets Heros name, HP and items (formatted)
	 * @return A String with Heros name, HP, and items in inventory
	 */
	@Override
	public String toString() {
		return super.toString() + "\n" + "Gold: " + getGold() + " gold\n" + "Inventory: \n" + itemsToString();
	}

	/**
	 * Hero attacks entity passed in
	 * @param e The enemy hero is fighting
	 * @return A String with Heros name, HP, and items in inventory
	 */
	@Override
	public String attack(Entity e) {
		int damage;
		damage = (int) (Math.random() * e.getHP() + 1);
		e.takeDamage(damage);
		return this.getName() + " attacks " + e.getName() + " for " + damage + " damage.";
	}

	/**
	 * The heros magic attack, Magic Missile
	 * @return The string representation of the attack, Magic Missile
	 */
	@Override
	public String magicMissile(Entity e) {
		int damage = (int) (Math.random() * e.getHP() + 1);
		e.takeDamage(damage);
		return this.getName() + " hits " + e.getName() + " with Magic Missile for " + damage + " damage.";
	}

	/**
	 * The heros magic attack, Fireball
	 * @return The string representation of the attack, Fireball
	 */
	@Override
	public String fireball(Entity e) {
		int damage = (int) (Math.random() * e.getHP() + 1);
		e.takeDamage(damage);
		return this.getName() + " hits " + e.getName() + " with a Fireball for " + damage + " damage.";
	}

	/**
	 * The heros magic attack, Thunder Clap
	 * @return The string representation of the attack, Thunder Clap
	 */
	@Override
	public String thunderclap(Entity e) {
		int damage = (int) (Math.random() * e.getHP() + 1);
		e.takeDamage(damage);
		return this.getName() + " hits " + e.getName() + " with Thunderclap for " + damage + " damage.";
	}

	/**
	 * Changes all elements in the items to a string representation (formatted)
	 * @return The string representation of items formated
	 */
	public String itemsToString() {
		String numberedItems = "";
		int counter = 1;
		for (Item i : items) {
			numberedItems += counter + ". " + i.getName() + "\n";
			counter++;
		}
		return numberedItems;
	}

	/**
	 * The number of items the hero has
	 * @return The number of items
	 */
	public int getNumItems() {
		return items.size();
	}

	/**
	 * Adds the item picked up to the heros bag (ArrayList of items)
	 * @param i is the item picked up
	 * @return True when item is picked up
	 */
	public boolean pickUpItem(Item i) {
		if (items.size() == 5) {
			return false;
		}
		items.add(i);
		return true;
	}

	/**
	 * A health potion is discarded
	 */
	public void drinkPotion() {
		for (Item i : items) {
			if (i.getName().equals("Health Potion")) {
				items.remove(i);
				return;
			}
		}
	}

	/**
	 * Discards a chosen item from the heros bag (ArrayList of items)
	 * @param index The index of the item in the ArrayList starting at 1
	 * @return The item at index
	 */
	public Item dropItem(int index) {
		Item i = items.get(index);
		items.remove(index);
		return i;
	}

	/**
	 * Shows if health potion is in heros bag (ArrayList of items)
	 * @return True if potion is in bag, False otherwise
	 */
	public boolean hasPotion() {
		for (Item i : items) {
			if (i.getName().equals("Health Potion")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Finds the current location of the hero
	 * @return The point (x,y) the hero is currently on
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * Moves the hero one step up on the map, room is revealed and char is returned
	 * @return The char ('s' for start, 'f' for finish, 'm' for monster, 'i' for
	 *         item, 'n' for nothing) at the point the hero lands on
	 */
	public char goNorth() {
		Point p = getLocation();
		int x = (int) p.getX();
		int y = (int) p.getY();
		if (y + 1 > 4) {
			throw new IndexOutOfBoundsException();
		}
		p.setLocation(new Point(x, y + 1));
		location = p.getLocation();
		// reveals char at location p in map
		map.reveal(p);
		return map.getCharAtLoc(p);
	}

	/**
	 * moves the hero one step down on the map
	 * @return the char ('s' for start, 'f' for finish, 'm' for monster, 'i' for
	 *         item, 'n' for nothing) at the point the hero lands on
	 */
	public char goSouth() {
		Point p = getLocation();
		int x = (int) p.getX();
		int y = (int) p.getY();
		if (y - 1 < 0) {
			throw new IndexOutOfBoundsException();
		}
		p.setLocation(new Point(x, y - 1));
		location = p.getLocation();
		// reveals char at location p in map
		map.reveal(p);
		return map.getCharAtLoc(p);
	}

	/**
	 * Moves the hero one step right on the map
	 * @return The char ('s' for start, 'f' for finish, 'm' for monster, 'i' for
	 *         item, 'n' for nothing) at the point the hero lands on
	 */
	public char goEast() {
		Point p = getLocation();
		int x = (int) p.getX();
		int y = (int) p.getY();
		if (x + 1 > 4) {
			throw new IndexOutOfBoundsException();
		}
		p.setLocation(new Point(x + 1, y));
		location = p.getLocation();
		// reveals char at location p in map
		map.reveal(p);
		return map.getCharAtLoc(p);
	}

	/**
	 * Moves the hero one step up left the map
	 * @return The char ('s' for start, 'f' for finish, 'm' for monster, 'i' for
	 *         item, 'n' for nothing) at the point the hero lands on
	 */
	public char goWest() {
		Point p = getLocation();
		int x = (int) p.getX();
		int y = (int) p.getY();
		if (x - 1 < 0) {
			throw new IndexOutOfBoundsException();
		}
		p.setLocation(new Point(x - 1, y));
		location = p.getLocation();
		// reveals char at location p in map
		map.reveal(p);
		return map.getCharAtLoc(p);
	}

}
