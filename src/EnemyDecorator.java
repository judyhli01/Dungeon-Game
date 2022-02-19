/** Representation of EnemyDecorator, subclass of Enemy */
public abstract class EnemyDecorator extends Enemy {
	/** An Enemy object */
	protected Enemy enemy;
	
	/**
	 * EnemyDecorator Constructor
	 * @param enemy An enemy object
	 * @param name The name of the enemy
	 * @param maxHP The maxHP of enemy
	 */
	public EnemyDecorator(Enemy enemy, String name, int maxHP) {
		super(name, maxHP, enemy.getItem());
		this.enemy = enemy;
	}
	
	/**
	 * Gets the name of enemy
	 * @return A string of the enemy's name
	 */
	public abstract String getName();
	
	/**
	 * EnemyDecorator Constructor
	 * @param e An entity object
	 * @return A string detailing the enemy's attack on Entity e
	 */
	public String attack(Entity e) {
		return enemy.attack(e);
	}
}