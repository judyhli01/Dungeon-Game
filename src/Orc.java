/** Representation of Orc, subclass of Enemy */
public class Orc extends Enemy {
	/** Orc Constructor */
	public Orc() {
		super("Orc", 4, ItemGenerator.getInstance().generateItem());
	}

	/**
	 * Enemy attacks Entity e
	 * @param e An entity object
	 * @return A string representation of the attack
	 */
	public String attack(Entity e) {
		int damage = (int) (Math.random() * 4 + 1);
		e.takeDamage(damage);
		return "Attacks " + e.getName() + " for " + damage + " damage.";
	}
}