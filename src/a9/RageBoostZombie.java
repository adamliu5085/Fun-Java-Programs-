package a9;

public class RageBoostZombie extends Zombie {

	// Hardcoded RageBoostZombie properties
	private static final int SPEED_CHANGE = -1;
	private static final int ATTACK_DAMAGE_CHANGE = 10;

	public RageBoostZombie(int xPosition, int yPosition, int size, String imgPath, int health, int coolDown, int speed,
			int attackDamage) {
		super(xPosition, yPosition, size, imgPath, health, coolDown, speed, attackDamage);
	}

	/**
	 * A RageBoostZombie will increase the speed and attack damage of any normal zombies it comes into contact with.
	 * This occurs when two hitboxes are overlapping and the RageBoostZombie is ready to attack again (based on its cooldown).
	 * 
	 * RageBoostZombies only attack Plants.
	 */
	@Override
	public void actOn(Zombie other) {
		// Checks if our RageBoostZombie is overlapping with a regular zombie.
		// Ensures the regular Zombie speed and attack damage are within reasonable range.
		if (isOverLapping(this, other) && !(other instanceof RageBoostZombie) && (other.speed > Zombie.MAXIMUM_NEGATIVE_SPEED)
				&& (other.attackDamage < Zombie.MAXIMUM_ATTACK_DAMAGE)) { 
			// Increases the speed and attack damage of the overlapping Zombie type.
			if (isReadyForAction()) {
				other.changeSpeed(SPEED_CHANGE);
				other.changeAttackDamage(ATTACK_DAMAGE_CHANGE);
			}
		}
	}

	/**
	 * Overrides the sprite logic to allow RageBoostZombies to overlap with other RageBoostZombies and regular Zombies.
	 */
	@Override
	public boolean isColliding(Sprite other) {
		return !(other instanceof RageBoostZombie) && !(other instanceof Zombie) && isOverLapping(this, other);
	}

}