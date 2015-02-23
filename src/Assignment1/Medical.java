package Assignment1;
/**
 * Medical Class - representing medical droid that extends Droid.
 * @author Pongsathorn Cherngchaosil
 *
 */
public class Medical extends Droid {
	/** Value of health point that each type of character will be receive.*/
	int HEAL_JEDI = 20;
	int HEAL_REBEL = 10;
	/**
	 * Constructor - create a medical droi
	 * @param n set name
	 */
	public Medical(String n){
		super(n,20);
	}

	@Override
	public void doTask(Entity e) {

		if(e instanceof Rebel){
			((Rebel)e).heal(HEAL_REBEL);
		}else if(e instanceof Jedi){
			((Jedi)e).heal(HEAL_JEDI);
		}
	}

	@Override
	public void getAttack(String weapon) {
		// TODO Auto-generated method stub
		
	}
}
