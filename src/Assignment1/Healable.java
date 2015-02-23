package Assignment1;

/**
 * Healable is an interface for character that is able to recive heal from Medical droid.
 * @author Pongsation Cherngchaosil
 *
 */
public interface Healable {
	/**
	 * heal or modify the health point
	 * @param hp how much health point that character will receive.
	 */
	public void heal(int hp);

}
