package Assignment3;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

public class Computer implements Serializable {

	private HashMap<Pattern,Integer> hash;
	public Computer(){
		hash = new HashMap<Pattern,Integer>();
	}
	
	public void add(Pattern p){
		if(hash.containsKey(p))
			hash.put(p, hash.get(p) + 1);
		else
			hash.put(p, 1);
		
		for(Pattern i: hash.keySet()){
			System.out.println(i.getPattern());
		}
	}
	
	public char prediction(String pattern){
		String p = pattern.substring(1, pattern.length()), predict = null;
		int max = 0;
		char[] m = {'r','p','s'};
		for(int i = 0; i < m.length;i++){
			if(hash.containsKey(new Pattern(p + m[i]))){
				if (max < hash.get(new Pattern(p + m[i]))){
					max = hash.get(new Pattern(p + m[i]));
					predict = p + m[i];
				}
			}
		}
		if(predict == null){
			Random rd = new Random();
			System.out.println("test");
			return m[rd.nextInt(m.length)];
		}
		else
		{
			return predict.charAt(predict.length() - 1);
		}
	}
}
