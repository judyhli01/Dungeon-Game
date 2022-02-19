/** Representation of Warrior, subclass of EnemyDecorator */
public class Warrior extends EnemyDecorator {
	/** An Enemy object */
	private Enemy enemy;
	
	/** Warrior Constructor */
	public Warrior(Enemy enemy) {
		super(enemy, (enemy.getName() + " Warrior"), enemy.getMaxHP() + 2);
		this.enemy = enemy;
	}
	
	/**
	 * Enemy attacks Entity e
	 * @param e An entity object
	 * @return A string representation of the attack
	 */
	@Override
	public String attack(Entity e) {
		int damage = (int) (Math.random() * 4 + 1);
		e.takeDamage(damage);
		return super.attack(e) + "\nThen," + " attacks " + e.getName() + " for " + damage + " damage.";
	}
	
	/**
	 * Gets name of this enemy
	 * @return The name of the enemy
	 */
	@Override
	public String getName() {
		String[] arr = enemy.getName().split(" ");
		if (arr.length == 1) {
			return arr[0] + " Warrior";
		}
		return arr[0] + " " + arr[1];
	}
}