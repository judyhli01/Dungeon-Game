import java.util.*;

/** EnemyGenerator generates random Enemies */
public class EnemyGenerator {
	/** An EnemyGenerator instance */
	private static EnemyGenerator instance;

	/** EnemyGenerator Constructor */
	private EnemyGenerator() {}

	public static EnemyGenerator getInstance() {
		if (instance == null) {
			instance = new EnemyGenerator();
		}
		return instance;
	}

	/**
	 * Retrieves a enemy from enemyList
	 * @param level is the position of the enemy on the list
	 * @return genem is an Enemy object
	 */
	public Enemy generateEnemy(int level) {
		Enemy enemy = null;
		Random rand = new Random();
		int r = rand.nextInt(4);
		if (r == 0) {
			enemy = new Troll();
		}
		if (r == 1) {
			enemy = new Orc();
		}
		if (r == 2) {
			enemy = new Froglok();
		}
		if (r == 3) {
			enemy = new Goblin();
		}
		if (level > 1) {
			int w = rand.nextInt(2);
			if (w == 0) {
				for (int i = 1; i < level; i++) {
					enemy = new Warrior(enemy);
				}
			} else {
				for (int i = 1; i < level; i++) {
					enemy = new Warlock(enemy);
				}
			}
		}
		return enemy;
	}
}