package Assignment1;

/**
 * Computer Class - representing a computer that extends Entity.
 * @author riche_000
 *
 */
public class Computer extends Entity{
	/** The status of the computer*/
	private boolean isHacked;
	/**
	 * Constructor - create a computer.
	 * @param n Set the name of the computer
	 * @param h set the Hp of the computer
	 */
	public Computer(String n, int h){
		super(n,h);
		isHacked = false;
	}
	
	/**
	 * Can be access from outside allowing the status of the computer to be change.
	 */
	public void hackComputer(){
		System.out.println("Computer had been hacked!!!");
		isHacked = true;
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
