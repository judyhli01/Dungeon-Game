/** Representation of Froglok, subclass of Enemy */
public class Froglok extends Enemy {
	/** Entity is an abstract class that is a representation of a Entity */
	public Froglok() {
		super("Froglok", 2, ItemGenerator.getInstance().generateItem());
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