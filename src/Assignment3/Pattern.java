package Assignment3;

public class Pattern {
	
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
