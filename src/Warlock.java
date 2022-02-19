import java.util.*;

/** Representation of Warlock, subclass of EnemyDecorator, implements Magical */
public class Warlock extends EnemyDecorator implements Magical {
	/** An Enemy object */
	private Enemy enemy;

	/** Warlock Constructor */
	public Warlock(Enemy enemy) {
		super(enemy, (enemy.getName() + " Warlock"), enemy.getMaxHP() + 1);
		this.enemy = enemy;
	}

	/**
	 * Enemy attacks Entity e
	 * @param e An entity object
	 * @return A string representation of the attack
	 */
	@Override
	public String attack(Entity e) {
		Random rand = new Random();
		int r = rand.nextInt(3);
		if (r == 0) {
			return super.attack(e) + "\nThen," + magicMissile(e);
		}
		if (r == 1) {
			return super.attack(e) + "\nThen," + fireball(e);
		}
		if (r == 2) {
			return super.attack(e) + "\nThen," + thunderclap(e);
		}
		return null;
	}

	/**
	 * Magic Missile attack
	 * @param e is the entity Magic Missile is being performed on
	 * @return A string that represents the attack
	 */
	@Override
	public String magicMissile(Entity e) {
		int damage = (int) (Math.random() * 4 + 1);
		e.takeDamage(damage);
		return " pokes " + e.getName() + " with Magic Missile for " + damage + " damage.";
	}

	/**
	 * Fireball attack
	 * @param e is the entity Fireball is being performed on
	 * @return A string that represents the attack
	 */
	@Override
	public String fireball(Entity e) {
		int damage = (int) (Math.random() * 3 + 1);
		e.takeDamage(damage);
		return " blasts " + e.getName() + " with Fireball for " + damage + " damage.";
	}

	/**
	 * Magic Missile attack
	 * @param e is the entity Magic Missile is being performed on
	 * @return A string that represents the attack
	 */
	@Override
	public String thunderclap(Entity e) {
		int damage = (int) (Math.random() * 3 + 1);
		e.takeDamage(damage);
		return " zaps " + e.getName() + " with Thunder Clap for " + damage + " damage.";
	}
	
	/**
	 * Gets name of this enemy
	 * @return The name of the enemy
	 */
	@Override
	public String getName() {
		String[] arr = enemy.getName().split(" ");
		if (arr.length == 1) {
			return arr[0] + " Warlock";
		}
		return arr[0] + " " + arr[1];
	}
}