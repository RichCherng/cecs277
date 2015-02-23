package Assignment1;

import java.util.Random;
/**
 * Rebel Class - Representing a Rebel troop that extends person
 * @author Pongsathorn Cherngchaosil
 */
public class Rebel extends Person implements Healable{
	/**
	 * Constructor - create a rebel soldier
	 * @param n set name
	 * @param q set catch-phrase
	 */
	public Rebel(String n, String q){
		super(n, 50, "Blaster", q);
	}
	/**
	 * heal Jedi for certain amount of hp
	 */
	public void heal(int hp) {
		
		System.out.printf(" for %d points.%n",hp);
		if((hp + getHp()) >= 50){
			modifyHp(50);
		}else{
			modifyHp(getHp() + hp);
		}
	}

	@Override
	public void doTask(Entity e) {
		sayCatchPhrase();
		attack(e);
	}
	/**
	 * The attacking of the rebel soldier.
	 */
	public void attack(Entity e){
		//check if target is active.
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

	@Override
	public void getAttack(String weapon) {
		//Random number generator generate chance of dodging.
		Random random = new Random();
		double DODGE_CHANCE = random.nextDouble();
		double CHANCE = .3;
		
		if( (DODGE_CHANCE < CHANCE) && (!weapon.equals("Force"))){
			//if Sith Lord is dodging, responds to each attack accordingly.
			if(weapon.equals("Saber")){
				System.out.println(" but " + getName() + " successfully dodge lightsaber.");
			}else if(weapon.equals("Blaster")){
				System.out.println(" tried to blasts "+getName()+ " but missed.");
			}
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
	
}
