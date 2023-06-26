package exercise2part2.controller;

import java.util.List;
import java.util.Set;

/**
 * Lab Week 14 Part 2 Exercise 2
 * 
 * @author muhammadizzat
 */

public class SentenceCounter {

	private int size = 65535;
	
	private String sentence;
	
	
	public SentenceCounter(byte[] byteData) {
		
		this.sentence = new String(byteData);
	}
	
	public String getSentence() {
		
		return sentence;
	}
	
	/**
	 * convert value into an array of byte
	 * @param length
	 * @return
	 */
	public byte[] convertToByteArray(int value) {
		
		byte[] outData = new byte[size];
		String stringValue = Integer.valueOf(value).toString();
        outData = stringValue.getBytes();
        return outData;
	}
	
	
	/**
	 *returns number of vowel
	 * 
	 * @return
	 */
	public int CountVowel()
	{
		String text = sentence.toLowerCase();
		int vowelNo = 0;
		
		for(int i = 0; i < text.length(); i++)
		{
			if(text.charAt(i)== 'a' 
					|| text.charAt(i)== 'e'
					|| text.charAt(i)== 'i' 
					|| text.charAt(i)== 'o'
					|| text.charAt(i)== 'u')
				
			{
				vowelNo++;
			}
		 
		}
		return vowelNo;
	}
	
	/**
	 *returns number of consonants
	 * 
	 * @return
	 */
	public int CountConso() {
	    // Create a set of vowels.
	    Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');

	    // Convert the sentence to lowercase.
	    String lowercaseSentence = sentence.toLowerCase();

	    // Initialize a counter to store the number of consonants.
	    int consoNo = 0;

	    // Iterate over the characters in the sentence.
	    for (char character : lowercaseSentence.toCharArray()) {
	        // Check if the character is a consonant.
	        if (!vowels.contains(character) && Character.isLetter(character)) {
	            // If the character is not a vowel and is a letter, increment the counter.
	        	consoNo++;
	        }
	    }

	    // Return the number of consonants.
	    return consoNo;
	}

	/**
	 * returns number of punctuations
	 * 
	 * @return
	 */
	public int CountPunct()
	{
		String text = sentence.toLowerCase();
		int punctNo = 0;
		
		for(int i = 0; i < text.length(); i++)
		{
			if(text.charAt(i)== ' ' 
					|| text.charAt(i)== '.'
					|| text.charAt(i)== ',' 
					|| text.charAt(i)== '!'
					|| text.charAt(i)== '"'
					|| text.charAt(i)== '('
					|| text.charAt(i)== ')')
				
			{
				punctNo++;
			}
		 
		}
		return punctNo;
	}
	
}
