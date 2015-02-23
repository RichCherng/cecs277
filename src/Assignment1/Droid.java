package Assignment1;
/**
 * Representation of a generic type of Droid that extends Entity
 * @author Pongsathorn Cherngchaosil
 *
 */
public abstract class Droid extends Entity {
	/**
	 * Contructor - the construc of generic type Droid.
	 * @param n set the name
	 * @param h se the Hp
	 */
	public Droid(String n, int h) {
		super(n, h);
	}
	/**
	 * Number of task that can be perform
	 * @return the number of task.
	 */
	public int getNumTask(){
		return 0;
	}
	/**
	 * use task.
	 */
	public void useTask(){
		
	}

}
