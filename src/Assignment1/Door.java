package Assignment1;

/**
 * Door Class - representing a door that extends Entity.
 * @author Pongsathorn Cherngchaosil
 *
 */
public class Door extends Entity{
	/** The status of the door.*/
	private boolean isOpen;
	/**
	 * Constructor - create a door.
	 * @param n set he name of the door.
	 * @param h set the Hp of the door.
	 */
	public Door(String n, int h)
	{
		super(n,h);
		isOpen = false;
	}
	/**
	 * Allow outside to change the status of the door.
	 */
	public void openDoor(){
		System.out.println("The Door is open");
		isOpen = true;
	}

	@Override
	public void doTask(Entity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAttack(String weapon) {
		// TODO Auto-generated method stub
		
	}
	
}
