/** Entity is an abstract class that is a representation of a Entity */
public abstract class Entity {
	/** Name of the Entity */
	private String name;
	/** Maximum HP of Entity */
	private int maxHp;
	/** HP of Entity */
	private int hP;

	/**
	 * Entity Constructor, creates an Entity object with name, maxHp and Hp
	 * @param n   is the name of Entity
	 * @param mHp is the entity's maximum number of health points
	 */
	public Entity(String n, int mHp) {
		this.name = n;
		this.maxHp = mHp;
		this.hP = mHp;
	}

	/**
	 * Abstract attack method on an entity
	 * @param e is the creature getting attacked
	 */
	public abstract String attack(Entity e);

	/**
	 * Gets the name of Entity
	 * @return name of entity
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the health of Entity
	 * @return health of entity
	 */
	public int getHP() {
		return this.hP;
	}

	/**
	 * Gets the max health of Entity
	 * @return max health of Entity
	 */
	public int getMaxHP() {
		return this.maxHp;
	}

	/**
	 * Heals Entity
	 * @param h are the hit points to increase hP by
	 */
	public void heal(int h) {
		int health = hP + h;
		if (health > maxHp) {
			hP = maxHp;
		} 
		else {
			hP += h;
		}
	}

	/**
	 * Entity takes damage
	 * @param h are the hit points to decrease hP by
	 */
	public void takeDamage(int h) {
		this.hP -= h;
	}

	/**
	 * Prints the name and health of Entity
	 * @return String of name and health of Entity
	 */
	public String toString() {
		double hp = hP;
		double max = maxHp;
		double filled = Math.round(10 * (hp / max));
		double notFilled = 10 - filled;
		String hb = "[";
		for (int i = 0; i < filled; i++) {
			hb += "▧";
		}
		for (int i = 1; i <= notFilled; i++) {
			hb += " ";
		}
		hb += "]";
		return getName() + "\nHP: " + hb + " " + hP + "/" + maxHp;
	}
}
