package a9;

public class WeakPlant extends Plant {

	/**
	 * Declares the cost of the weakPlank actor to 5 elixir, Declares and sets its
	 * attack damage to 1 (Half of a regular plant).
	 */
	protected static final int ACTORCOST = 5;
	private static final int ATTACKDAMAGE = 1;

	public WeakPlant(int xPosition, int yPosition, int size, String imgPath, int health, int coolDown,
			int attackDamage) {
		super(xPosition, yPosition, size, imgPath, health, coolDown, attackDamage);
	}

	/**
	 * An attack only happens when two hitboxes are overlapping and the Plant is
	 * ready to attack again (based on its cooldown).
	 * 
	 * Plants only attack Zombies.
	 */
	@Override
	public void actOn(Zombie other) {
		if (isColliding(other)) {
			if (isReadyForAction()) {
				other.changeHealth(-ATTACKDAMAGE);
				resetCoolDown();
			}
		}
	}

	/**
	 * Overrides the changeHealth Method to change the health by double the input
	 * parameter. This means WeakPlants lose health twice as fast as regular plants.
	 */
	@Override
	protected void changeHealth(int change) {
		health += change * 2;
	}

}
