package Assignment6;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;
/**
 * Computer Class - Represent a AI of the game.
 * @author Pongsathorn Cherngchaosil 012124071
 *
 */
public class Computer implements Serializable {
	//Initialize hashmap tha will store the pattern and number of occurrences.
	private HashMap<Pattern, Integer> hash;
	/**
	 * Constructor - create a computer and initialize hashmap
	 */
	public Computer() {
		hash = new HashMap<Pattern, Integer>();
	}
	/**
	 * Store Pattern into hashmap
	 * @param pattern string of pattern from user
	 */
	public void storePattern(String pattern) {
		Pattern p = new Pattern(pattern);
		if (hash.containsKey(p))
			hash.put(p, hash.get(p) + 1);
		else
			hash.put(p, 1);
	}
	/**
	 * Compare choice of player and choice of computer 
	 * and determine who will win
	 * @param player player's choice
	 * @param computer computer's choice
	 * @return the result of comparison, player win, lose ,tie
	 */
	public int compare(char player, char computer) {
		if (player == 'r') {
			if (computer == 's')
				return 1;
			else if (computer == 'r')
				return 0;
			else
				return -1;
		}
		if (player == 's') {
			if (computer == 's')
				return 0;
			else if (computer == 'r')
				return -1;
			else
				return 1;
		}

		if (player == 'p') {
			if (computer == 's')
				return -1;
			else if (computer == 'r')
				return 1;
			else
				return 0;
		}
		
		return 0;
	}
	/**
	 * Make prediction and also add previous user's pattern
	 * @param pattern pattern of user's move.
	 * @return the prediction.
	 */
	public char makePrediction(String pattern) {
		//Check if the pattern is valid
		if (pattern.length() != 4) {
			//if not return random move;
			return randPick();

		} else { 
			//Initialize player last 3 moves, prediction variable
			String p = pattern.substring(1, pattern.length()), predict = null;
			//Maximum occurrence of the pattern
			int max = 0;
			//Possible choices
			char[] m = { 'r', 'p', 's' };
			//go through all possible move/pattern
			for (int i = 0; i < m.length; i++) {
				//check if pattern is already exist in hashmap
				if (hash.containsKey(new Pattern(p + m[i]))) {
					//check which pattern occurs more
					if (max < hash.get(new Pattern(p + m[i]))) {
						max = hash.get(new Pattern(p + m[i]));
						predict = p + m[i];
					}
				}
			}
			//put a counter move according to the player's patterns
			if (predict != null) {
				char cPredict = predict.charAt(predict.length() - 1);
				if (cPredict == 'r')
					return 'p';
				else if (cPredict == 'p')
					return 's';
				else if (cPredict == 's')
					return 'r';
			}

		}
		return randPick();

	}
	/**
	 * Randomly pick a move 
	 * @return random move from computer
	 */
	public char randPick() {
		char[] m = { 'r', 'p', 's' };
		Random rd = new Random();
		// System.out.println("test");
		return m[rd.nextInt(m.length)];
	}
}
