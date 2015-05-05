package Assignment6;


import java.io.Serializable;
/**
 * Pattern Class - Represent a pattern of character choices
 * @author Pongsathorn Cherngchaosil 012124071
 *
 */
public class Pattern implements Serializable {
	//String of pattern of player's choices
	private String pattern;
	/**
	 * Constructor - create a pattern
	 * @param p pattern of player's move
	 */
	public Pattern(String p){
		pattern = p;
	}
	/**
	 * Return the pattern
	 * @return pattern of player's move
	 */
	public String getPattern(){
		return pattern;
	}
	/**
	 * Check if 2 objects are equals 
	 */
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(!(o instanceof Pattern))
			return false;
		Pattern p = (Pattern) o;
		return p.pattern.equals(pattern);
		
	}
	/**
	 * Create a hashcode for object Pattern
	 * Using the pattern store within the class.
	 */
	public int hashCode(){
		return pattern.hashCode();
	}
	
}
