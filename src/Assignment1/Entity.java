package Assignment1;
/**
 * Representation of a generic type of a Entity.
 * @author Pongsathorn Cherngchaosil
 *
 */
public abstract class Entity {
	/** The name of the Entity.*/
	private String name;
	/** The health point of the Entity.*/
	private int hp;
	/** The status of an Entity*/
	private boolean active = true;
	/**The Task of an Entity*/
	private String task;
	/**
	 * The generic function of generic type of Entity.
	 * @param e The Entity that will be interacting with.
	 */
	public abstract void doTask(Entity e);
	/**
	 * Constuctor - creating an Entity.
	 * @param n set name
	 * @param h set Health point.
	 */
	public Entity(String n, int h){
		name = n;
		hp = h;
	}
	/**
	 * The method that return the name of the Entity
	 * @return The name of the Entity.
	 */
	public String getName(){
		return name;
	}
	/**
	 * The method that return the Health point of the Entity.
	 * @return The health point of the Entity.
	 */
	public int getHp(){
		return hp;
	}
	/**
	 * The method that return the status of the Entity.
	 * @return the status (active/inactive) of the entity.
	 */
	boolean getActive(){
		return active;
	}
	/**
	 * Allow the health point to be modify.
	 * @param d The health point that will be set.
	 */
	public void modifyHp(int d){
		hp = d;
		if(hp <= 0){
			active = false;
			hp = 0;
		}else{
			active = true;
		}
	}
	/**
	 * The method that return the task of the Entity.
	 * @return the task of Entity.
	 */
	public String getTask(){
		return task;
	}
	/**
	 * The method that set the task for the Entity.
	 * @param t the name of the task.
	 */
	public void setTask(String t){
		task = t;
	}
	
	/**
	 * The generic method that responds to the attack to the Entity..
	 * @param weapon The weapon that use to attack the Entity.
	 */
	public abstract void getAttack(String weapon);
}
