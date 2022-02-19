import java.lang.*;

class Main {
	/**
	 * Hero visits the store; Store sells Health Potions, and Keys; Store buys
	 * anything in the hero's inventory
	 * @param h The hero
	 */
	static void store(Hero h) {
		// Asks if hero wants to buy or sells
		boolean another = true;
		ItemGenerator ig = ItemGenerator.getInstance();
		while (another) {
			System.out.print("1. Buy Items\n" + "2. Sell Items\n" + "3. Exit Shop\n");
			int choice = CheckInput.getIntRange(1, 3);
			// hero buys health and Keys,
			if (choice == 1) {
				boolean a = true;
				while (a) {
					System.out.format("+------------------+-----------+%n");
					System.out.format("| Item             | Price     |%n");
					System.out.format("+------------------+-----------+%n");
					System.out.format("| 1. Health Potion | 25 Gold   |%n");
					System.out.format("| 2. Key           | 50 Gold   |%n");
					System.out.format("+------------------+-----------+%n");
					System.out.print("What do you want to buy: (0 to return to Menu) ");
					choice = CheckInput.getIntRange(0, 2);
					if (choice == 1) {
						if (h.getGold() >= 25) {
							h.pickUpItem(ig.getPotion());
							h.spendGold(25);
							System.out.println("A Health Potion has been added to your inventory.");
							System.out.println("Total Gold: " + h.getGold());
						} else {
							System.out.println("You don't have enough gold to buy this item.");
						}
					} else if (choice == 2) {
						if (h.getGold() >= 50) {
							h.pickUpItem(ig.getKey());
							h.spendGold(50);
							System.out.println("A Key has been added to your inventory.");
						} else {
							System.out.println("You don't have enough gold to buy this item.");
						}
					} else {
						a = false;
					}
					if (a != false) {
						System.out.print("Buy Another Item? (Y/N) ");
						if (!CheckInput.getYesNo())
							a = false;
					}
				}
			}
			// hero sells stuff
			else if (choice == 2) {
				boolean a = true;
				System.out.format("+------------------+-----------+%n");
				System.out.format("| Item             | Price     |%n");
				System.out.format("+------------------+-----------+%n");
				System.out.format("| Key              | 50 Gold   |%n");
				System.out.format("| Health Potion    | 25 Gold   |%n");
				System.out.format("| Bag o' Gold      | 15 Gold   |%n");
				System.out.format("| Gem              | 10 Gold   |%n");
				System.out.format("| Ringmail         | 9 Gold    |%n");
				System.out.format("| Ring             | 8 Gold    |%n");
				System.out.format("| Helm             | 7 Gold    |%n");
				System.out.format("| Shield           | 5 Gold    |%n");
				System.out.format("| Gloves           | 4 Gold    |%n");
				System.out.format("| Boots            | 4 Gold    |%n");
				System.out.format("| Belt             | 3 Gold    |%n");
				System.out.format("+------------------+-----------+%n");
				while (a) {
					System.out.println("What do you want to sell: (0 to return to Menu)");
					System.out.print(h.itemsToString());
					choice = CheckInput.getIntRange(0, h.getNumItems());
					if (choice == 0) {
						a = false;
					} else {
						Item i = h.dropItem(choice - 1);
						h.collectGold(i.getValue());
						System.out.println("You sold " + i.getName() + " for " + i.getValue() + " gold.");
						System.out.println("Total Gold: " + h.getGold());
						System.out.print("Sell another Item? (Y/N) ");
						if (!CheckInput.getYesNo()) {
							a = false;
						}
					}
				}
			}
			// exit store
			else if (choice == 3) {
				return;
			}
		}
	}

	/**
	 * Hero fights monster
	 * @param h     is the hero object
	 * @param m     is map object being used
	 * @param eg    is the random enemy generated in the room
	 * @param level is the level of the monster
	 * @return True if monster is defeated, False if user runs away or dies
	 */
	static boolean monsterRoom(Hero h, Map m, EnemyGenerator eg, int level) {
		Enemy em = eg.generateEnemy(level);

		System.out.println("You've encountered a " + em.getName() + ".");
		System.out.println(em);

		int choice = 0;
		while (choice != 2) {
			if (h.hasPotion()) {
				System.out.println("1. Fight\n" + "2. Run Away\n" + "3. Use Health Potion");
				choice = CheckInput.getIntRange(1, 3);
			} else {
				System.out.println("1. Fight\n" + "2. Run Away");
				choice = CheckInput.getIntRange(1, 2);
			}

			if (choice == 1) {
				// if hero dies return false, if monster dies return true
				if (!fight(h, em)) {
					if (h.getHP() <= 0) {
						return false;
					}
					if (em.getHP() <= 0) {
						System.out.println("You've defeated " + em.getName() + "!");
						if (h.getNumItems() < 5) {
							h.pickUpItem(em.getItem());
							System.out.println("You recieved a " + em.getItem().getName() + " from its corpse.");
						} else {
							System.out.println(em.getName() + " dropped " + em.getItem().getName());
							System.out.println("Inventory is full");
							System.out.println("1. Drop item from Inventory\n" + "2. Drop new item");
							choice = CheckInput.getIntRange(1, 2);
							if (choice == 1) {
								System.out.println("Choose which item to drop:");
								System.out.print(h.itemsToString());
								choice = CheckInput.getInt();
								h.dropItem(choice - 1);
								h.pickUpItem(em.getItem());
							}
						}
						return true;
					}
				}
			} else if (choice == 3 && h.hasPotion()) {
				h.heal(h.getMaxHP());
				System.out.println("Your health has been restored: " + h.getHP() + "/" + h.getMaxHP());
				String[] lines = h.itemsToString().split("\r\n|\r|\n");
				int index = 0;
				for (int i = 0; i < lines.length; i++) {
					if (lines[i].contains("Health Potion")) {
						index = i;
					}
				}
				h.dropItem(index);
			} else if (choice > 3 || choice < 1) {
				System.out.println("Please choose Fight, Run Away, or Use Health Potion.");
			}
		}
		// runs away
		return false;
	}

	/**
	 * A fight (single round)
	 * @param h     The hero object
	 * @param e     The enemy generated
	 * @param level The level of the map
	 * @return True if enemy and hero are both are still alive, false if one is dead
	 */
	static boolean fight(Hero h, Enemy e) {
		System.out.println("1. Physical Attack\n" + "2. Magic Attack");
		int attack = CheckInput.getIntRange(1, 2);
		// Physical attack
		if (attack == 1) {
			System.out.println(h.attack(e));
		}
		// Magic Attack
		else if (attack == 2) {
			System.out.println("1. Magic Missile\n2. Fireball\n3. Thunderclap");
			int attackNum = CheckInput.getIntRange(1, 3);
			if (attackNum == 1) {
				System.out.println(h.magicMissile(e));
			} else if (attackNum == 2) {
				System.out.println(h.fireball(e));
			} else {
				System.out.println(h.thunderclap(e));
			}
		} else {
			System.out.println("Please select Physical Attack or Magical Attack.");
		}
		if (e.getHP() > 0) {
			int index = h.hasArmorItem();
			if (index > -1) {
				Item i = h.dropItem(index);
				System.out.println(e);
				System.out.println(i.getName() + " blocked " + e.getName() + "'s attack!");
			} else {
				System.out.println(e);
				System.out.println(e.attack(h));
			}
		}

		if (e.getHP() > 0 && h.getHP() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Allows hero to pick up item if bag is not full, gives the choice of keeping
	 * items or replacing with new item when inventory is full
	 * @param h  is the hero object
	 * @param m  is the map
	 * @param ig is the item generator
	 */
	static void itemRoom(Hero h, Map m, ItemGenerator ig) {
		Item i = ig.generateItem();

		if (h.pickUpItem(i)) {
			System.out.println("You found a " + i.getName() + ".");
			m.removeCharAtLoc(h.getLocation());
		} else {
			// hero can choose to drop old item for new item or just drop new item
			System.out.println("You found a " + i.getName() + ".");
			System.out.println("Inventory is full.");
			System.out.println("1. Drop item from Inventory\n" + "2. Drop new item");
			int choice = CheckInput.getIntRange(1, 2);

			if (choice == 1) {
				System.out.println("Choose which item to drop:");
				System.out.print(h.itemsToString());
				choice = CheckInput.getIntRange(1, h.getNumItems());
				h.dropItem(choice - 1);
				h.pickUpItem(i);
				m.removeCharAtLoc(h.getLocation());
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("What is your name, traveler? ");
		String name = CheckInput.getString();

		int mapNum = 1;
		Map map = Map.getInstance();
		map.loadMap(mapNum);
		Hero hero = new Hero(name, map);
		boolean visited = false;
		while (hero.getHP() > 0) {
			System.out.print(hero);
			map.reveal(hero.getLocation());
			map.displayMap(hero.getLocation());
			boolean run = true;
			char character = 'n';

			if (map.getCharAtLoc(hero.getLocation()) == 's' && !visited) {
				System.out.print("Do you want to visit the Store? (Y/N) ");
				if (CheckInput.getYesNo()) {
					store(hero);
				}
				visited = true;
				System.out.print(hero);
				map.displayMap(hero.getLocation());
			}

			while (run) {
				try {
					System.out.print("1. Go North\n" + "2. Go South\n" + "3. Go East\n" + "4. Go West\n" + "5. Quit\n");
					int choice = CheckInput.getIntRange(1, 5);
					switch (choice) {
					case 1:
						hero.goNorth();
						character = map.getCharAtLoc(hero.getLocation());
						run = false;
						break;
					case 2:
						hero.goSouth();
						character = map.getCharAtLoc(hero.getLocation());
						run = false;
						break;
					case 3:
						hero.goEast();
						character = map.getCharAtLoc(hero.getLocation());
						run = false;
						break;
					case 4:
						hero.goWest();
						character = map.getCharAtLoc(hero.getLocation());
						run = false;
						break;
					case 5:
						System.out.println("Game Over");
						System.exit(0);
					}
				} catch (IndexOutOfBoundsException e) {
					System.out.println("You ran into a wall. Please proceed in another direction.");
				}
			}
			// monster room
			if (character == 'm') {
				EnemyGenerator eg = EnemyGenerator.getInstance();
				boolean mr = monsterRoom(hero, map, eg, mapNum);
				if (mr == true) {
					map.removeCharAtLoc(hero.getLocation());
				}
				// hero runs away or dies
				if (mr == false) {
					if (hero.getHP() > 0) {
						run = true;
						while (run) {
							try {
								int randRoom = (int) (Math.random() * 4 + 1);
								if (randRoom == 1)
									hero.goNorth();
								character = map.getCharAtLoc(hero.getLocation());
								run = false;
								map.reveal(hero.getLocation());
								if (randRoom == 2)
									hero.goSouth();
								character = map.getCharAtLoc(hero.getLocation());
								run = false;
								map.reveal(hero.getLocation());
								if (randRoom == 3)
									hero.goEast();
								character = map.getCharAtLoc(hero.getLocation());
								run = false;
								map.reveal(hero.getLocation());
								if (randRoom == 4)
									hero.goWest();
								character = map.getCharAtLoc(hero.getLocation());
								run = false;
								map.reveal(hero.getLocation());
								break;
							} catch (IndexOutOfBoundsException e) {
								run = true;
							}
						}
					} else {
						System.out.println("You Died.");
						System.exit(0);
					}
				}
			}
			// store and start
			if (character == 's') {
				System.out.print("Do you want to visit the Store? (Y/N) ");
				if (CheckInput.getYesNo()) {
					store(hero);
				}
			}
			// item room
			if (character == 'i') {
				ItemGenerator ig = ItemGenerator.getInstance();
				itemRoom(hero, map, ig);
			}
			// finished with level
			if (character == 'f') {
				// if hero does not have the Key
				if (!hero.hasKey()) {
					System.out.println("The door requires a key to unlock.");
				}
				// hero has key
				else {
					System.out.print("Use key to unlock door? (Y/N) ");
					boolean ans = CheckInput.getYesNo();
					if (ans) {
						hero.useKey();
						System.out.println("Your key has unlocked the door.");
						System.out.println("\n~~~~ Level " + mapNum + " cleared! ~~~~\n");
						System.out.println(hero.getName() + " health has been restored to 25/25!");
						mapNum += 1;
						map = Map.getInstance();
						if (mapNum % 3 == 0)
							map.loadMap(3);
						else
							map.loadMap(mapNum % 3);
						hero.heal(hero.getMaxHP());
						visited = false;
					}
				}
			}
			if (character == 'n') {
				System.out.println("There is nothing here.");
			}
		}
	}
}
