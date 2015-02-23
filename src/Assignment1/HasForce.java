package Assignment1;

/**
 * HasForce is an interface for character that has the ability to use force.
 * @author Pongsathorn Cherngchaoisl
 *
 */
public interface HasForce {

	/**
	 * Character use force against the selected Entity.
	 * @param e The Entity that will attack by force.
	 */
	public void useForce(Entity e);
}
