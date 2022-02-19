/** Enemy is a representation of a Enemy, subclass of Entity */
public abstract class Enemy extends Entity {
	/** An Item object */
	private Item item;

	/**
	 * Creates an Enemy object
	 * @param n   is the name of Enemy
	 * @param mHp is the maximum health an entity can have
	 * @param I   is an Item object
	 */
	public Enemy(String n, int mHp, Item I) {
		super(n, mHp);
		item = I;
	}

	/**
	 * Gets item from enemy
	 * @return item object
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Attack method
	 * @param e is an Entity object
	 * @return a String representation of the enemy's attack on e
	 */
	@Override
	public abstract String attack(Entity e);
}