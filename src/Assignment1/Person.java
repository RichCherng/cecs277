
package Assignment1;
/**
 * Person Class - represent a human, a person that extends Entity.
 * @author Pongsathorn Cherngchaosil
 *
 */
public abstract class Person extends Entity {
	/** The typ of weapon*/
	private String weapon;
	/** The catch-phrase*/
	private String quip;
	/** The Damage of light saber*/
	public int DAMAGE_SABER = 15;
	/** The Damage of blaster*/
	public int DAMAGE_BLASTER = 10;
	/** The Damage of force*/
	public int DAMAGE_FORCE = 25;
	/**
	 * Constructor - create a person
	 * @param n set name
	 * @param h set health point
	 * @param w set weapon
	 * @param q set catch-phrase
	 */
	public Person(String n, int h, String w, String q){
		super(n,h);
		weapon = w;
		quip = q;
	}
	/**
	 * say the catch-phrase of that person
	 */
	public void sayCatchPhrase(){
		System.out.println(getName()+" : "+quip);
	}
	/**
	 * generic method of attacking
	 * @param e the entity receiving the attack
	 */
	public abstract void attack(Entity e);

	/**
	 * return the type of weapon of the Person.
	 * @return the type of weapon.
	 */
	public String getWeapon(){
		return weapon;
	}
}
