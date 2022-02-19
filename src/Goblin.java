/** Representation of Goblin, subclass of Enemy */
public class Goblin extends Enemy {
	/** Goblin Constructor */
	public Goblin() {
		super("Goblin", 2, ItemGenerator.getInstance().generateItem());
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