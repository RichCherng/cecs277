package Assignment1;

import java.util.Random;
import java.util.Scanner;
/**
 * Jedi Class - representing a Jedi character that extends Person and implement HasForce and Healable
 * @author Pongsathorn Cherngchaosil
 */
public class Jedi extends Person implements HasForce,Healable{
	/** The color of the lightsaber*/
	private String saberColor;
	/**
	 * Constructor - create a Jedi
	 * @param n set name
	 * @param q set catch-phrase
	 * @param c set the color of the light saber
	 */
	public Jedi(String n, String q, String c){
		super(n, 100, "Saber",q);
		saberColor = c;
	}
	
	/**
	 * Jedi use force to attack that Entity.
	 */
	public void useForce(Entity e){
		//if the entity is inactive then nothing happen
		if(!e.getActive()){
			System.out.println(getName()+" try to attack "+e.getName()+" but "+e.getName()+" is already down");
		}else {
			System.out.print(getName()+" uses force on "+e.getName());
		e.getAttack("Force");
		}
	}
	/**
	 * heal Jedi for certain amount of hp
	 */
	public void heal(int hp) {
		System.out.printf(" for %d points.%n",hp);
		//if the healing received cause the health to be greater than 100, set the health to be 100
		if((hp + getHp()) >= 100){
			modifyHp(100);
		}else{
			modifyHp(getHp() + hp);
		}
	}



	@Override
	public void getAttack(String weapon) {
		//Random number generator generate chance of dodging.
		Random random = new Random();
		double DODGE_CHANCE = random.nextDouble();
		double CHANCE = .5;
		//if Jedi is dodging, responds to each attack accordingly.
		if(DODGE_CHANCE > CHANCE){
			if(weapon.equals("Saber")){
				System.out.println(" but " + getName() + " blocks with his "+saberColor + " lightsaber.");
			}else if(weapon.equals("Blaster")){
				System.out.println(" tried blasts "+getName()+ ", but "+getName()+" deflects the shot");
			}else if(weapon.equals("Force")){
				System.out.println(" but "+ getName()+" counter it with force");
			}
			sayCatchPhrase();
		}else
		{
			//Take damage according to the type of attack.
			int damage = 0;
			if(weapon.equals("Saber")){
				damage = random.nextInt((DAMAGE_SABER - 1) + 1) + 1;
			}else if(weapon.equals("Blaster")){
				System.out.printf(" blasts at %s",getName());
				damage = random.nextInt((DAMAGE_BLASTER - 1) + 1) + 1;

			}else if(weapon.equals("Force")){
				damage = random.nextInt((DAMAGE_FORCE - 1) + 1) + 1;
				
			}
			modifyHp(getHp() - damage);
			System.out.printf(" for %d points of damage.\n",damage);
		}
		
	}


	@Override
	public void doTask(Entity e) {
		System.out.println("1. Attack with Lightsaber.\n2. Attack using Force.");
		int PLAYER_DECISION = checkInt(1,2);
		
		switch(PLAYER_DECISION){
		case 1:
			attack(e);
			break;
		case 2:
			useForce(e);
			break;
		}
		
	}
	
	/**
	 * Check for invalid input.
	 * @param low The the lowest value possible.
	 * @param high The highest value possible
	 * @return The valid value.
	 */
	@SuppressWarnings("resource")
	public static int checkInt( int low, int high){
		Scanner in = new Scanner(System.in);
		boolean valid = false;
		int validNum = 0;
		
		while(!valid){
			
			if(in.hasNextInt()){
				validNum = in.nextInt();
				if( validNum >= low && validNum <= high){
					valid = true;
				}else{
					System.out.print("Invalid- Retry: ");
				}
			}else{
				in.next();
				System.out.print("Invalid input- Retry: ");
			}
		}
		
		return validNum;
	}


	@Override
	public void attack(Entity e) {
		if(!e.getActive()){
			System.out.println(getName()+" tried to attack "+e.getName()+" but "+e.getName()+" is already down");
		}else{
			if(getWeapon().equals("Saber")){
				System.out.print("\n"+getName()+" slashes at "+e.getName());
			}else if(getWeapon().equals("Blaster")){
				System.out.print(getName());
			}
		e.getAttack(getWeapon());
		}
		
	}
}
