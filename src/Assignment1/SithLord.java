package Assignment1;

import java.util.Random;
/**
 * SithLord Class - representing a Sith Lord that extends Person and implement HasForce
 * @author Pongsathorn Cherngchaosil
 *
 */
public class SithLord extends Person implements HasForce{
	/** The color of the lightsaber*/
	private String saberColor;
	/**
	 * Constructor - create a Sith Lord
	 * @param n set name
	 * @param q set catch-phrase
	 * @param c set the color of the light saber
	 */
	public SithLord(String n, String q, String c){
		super(n, 100, "Saber",q);
		saberColor = c;
	}
	
	/**
	 * Jedi use force to attack that Entity.
	 */
	public void useForce(Entity e){
		if(!e.getActive()){
			System.out.println(getName()+" try to attack "+e.getName()+" but "+e.getName()+" is already down");
		}else {
			System.out.print(getName()+" uses force on "+e.getName());
		e.getAttack("Force");
		}
	}


	@Override
	public void getAttack(String weapon) {
		//Random number generator generate chance of dodging.
		Random random = new Random();
		double DODGE_CHANCE = random.nextDouble();
		double CHANCE = .5;
		
		if(DODGE_CHANCE > CHANCE){
			//if Sith Lord is dodging, responds to each attack accordingly.
			if(weapon.equals("Saber")){
				System.out.println(" but " + getName() + " blocks with his "+saberColor + " lightsaber.");
				sayCatchPhrase();
			}else if(weapon.equals("Blaster")){
				System.out.println(" tried blasts "+getName()+ ", but "+getName()+" deflects the shot");
			}else if(weapon.equals("Force")){
				System.out.println(" but "+ getName()+" counter it with force.");
				sayCatchPhrase();
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


	@Override
	public void doTask(Entity e) {
		Random random = new Random();
		double ATTACK_CHOICE = random.nextDouble();
		double CHANCE = .5;
		
		if(ATTACK_CHOICE > CHANCE){
			attack(e);
		}else{
			useForce(e);
		}
		
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
