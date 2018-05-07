package bll.logic;

import java.util.Random;

/**
 * Class used to create a random activation key.
 *
 * @author Héléna Line Reymond
 * @version 1.0
 */
public class KeyGenerator {

	private final static String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final static String lowerCase = "abcdefghijklmnopqrstuvwxyz";
	private final static String number = "0123456789";
	
	private final static String[] strings = {upperCase, lowerCase, number};
	
	/**
	 * Generate a random activation key.
	 *
	 * @param length    the size of the key wanted.
	 */
	public static String generateKey(int length) {
		
		Random random = new Random();
		String key = "";
		
		// Create each character needed for the random key
		for(int i = 0; i < length; i++) {
			
			// Get the string used to create a random character
			String stringChosen = strings[random.nextInt(strings.length)];
			
			// Get the random character to add in the key
			char newChar = stringChosen.charAt(random.nextInt(stringChosen.length()));
			
			// Add the character in the key
			key += newChar;
		}
		
		return key;
	}
}
