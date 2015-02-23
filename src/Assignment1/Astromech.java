package Assignment1;

/**
 * Astromech Class - representing a Astromech droid.
 * @author Pongsathorn Cherngchaosil 012124071
 *
 */
public class Astromech extends Droid{
	
	/**
	 * Contructor - create an Astromech
	 * @param n set the name of the created Astromech
	 */
	public Astromech(String n){
		super(n,20);
	}

	
	/**
	 * Astromech can respond to entity door and computer.
	 */
	@Override
	public void doTask(Entity e) {

		if(e instanceof Door){
			((Door)e).openDoor();;
		}else if(e instanceof Computer){
			((Computer)e).hackComputer();
		}
	}

	@Override
	public void getAttack(String weapon) {
		// TODO Auto-generated method stub
		
	}
}
