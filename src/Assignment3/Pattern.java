package Assignment3;

import java.io.Serializable;

public class Pattern implements Serializable {
	
	private String pattern;
	public Pattern(String p){
		pattern = p;
	}
	
	public String getPattern(){
		return pattern;
	}
	
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(!(o instanceof Pattern))
			return false;
		Pattern p = (Pattern) o;
		return p.pattern.equals(pattern);
		
	}
	
	public int hashCode(){
		return pattern.hashCode();
	}
	
}
