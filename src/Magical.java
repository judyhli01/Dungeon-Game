/** Interface for special magic attacks of an object */
public interface Magical {
	/** Menu to select magic attack from */
	static final String MAGIC_MENU = "1. Magic Missile\n2. Fireball\n3. Thunderclap";

	/**
	 * Magic Missile attack
	 * @param e is the entity Magic Missile is being performed on
	 * @return A string that represents the attack
	 */
	public String magicMissile(Entity e);

	/**
	 * Fireball attack
	 * @param e is the entity Fireball is being performed on
	 * @return A string that represents the attack
	 */
	public String fireball(Entity e);

	/**
	 * Magic Missile attack
	 * @param e is the entity Magic Missile is being performed on
	 * @return A string that represents the attack
	 */
	public String thunderclap(Entity e);
}
