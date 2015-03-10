
import java.io.*;
import java.util.*;

public class FindStrangeWords {
	//creates the boolean variable used to perform our tests
	public static boolean testing=false;
	
    public static void main (String[] argv)
    {
	
	myTests ();
	mainTest ();
    }

    static void myTests ()
    {
	// INSERT YOUR TEST CODE HERE.
	//creates a one element array for testing purposes, adds a correct 
	//word to the array and then tests the program to see if it passed
		String[] words=new String[1];
	//Test 1 for correct word
		System.out.println("Test word is adventitious");
		words[0]="adventitious";
		findConsecVowelWords(words);
		if(testing==true)
		{
			System.out.println("Passed correct word test");
		}
		else System.out.println("Failed correct word test");
	//Test 2 for incorrect word
		System.out.println("Test word is adventurous");
		words[0]="adventurous";
		findConsecVowelWords(words);
		if(testing==true)
		{
			System.out.println("Failed incorrect word test");
		}
		else System.out.println("Passed incorrect word test");
	//Test 3 for correct word with repeat vowels
			System.out.println("Test word is sacrilegious");
			words[0]="sacrilegious";
			findConsecVowelWords(words);
			if(testing==true)
			{
				System.out.println("Passed correct repeat vowel word test");
			}
			else System.out.println("Failed correct repeat vowel word test");
	
    }


    static void mainTest ()
    {
      String[] words = WordTool.getDictionary ();
      findConsecVowelWords (words);
    }

    static void findConsecVowelWords (String[] words)
    {
	// INSERT YOUR CODE HERE.
		
		//creates the booleans for each letter and defaults then to false.
		boolean aYes=false;
		boolean eYes=false;
		boolean iYes=false;
		boolean oYes=false;
		boolean uYes=false;
		testing=false;
		//runs a for loop to scan each word
		for(int i=0; i<words.length; i++)
		{
			//creates an array of characters from the string
			char[] letters = words[i].toCharArray();
			//runs a loop to scan each character
			for(int n=0; n<letters.length; n++)
			{
				//if the letter is an a and we have yet to encounter an a then it sets the boolean for a to true.
				if(letters[n]=='a' && aYes==false)
				{
					aYes=true;
				}
				//if the letter is an e and we have found a then it sets the boolean for e to true.
				else if (letters[n]=='e' && aYes==true) 
				{
					eYes=true;
				}	
				//if the letter is an i and we have found a and e then it sets the boolean for i to true
				else if (letters[n]=='i' && eYes==true) 
				{
					iYes=true;
					eYes=false;
				}
				//if the letter is an o and we have found a,e and i then it sets the boolean for o to true
				else if(letters[n]=='o' && iYes==true)
				{	
					oYes=true;
					iYes=false;
				}
				//if the letter is a u and we have found a,e,i, and o then it sets the boolean for u to true
				else if(letters[n]=='u' && oYes==true)
				{
					oYes=false;
					uYes=true;
				}
			}
			//if the boolean for u is true, meaning we have a word with aeiou in order then it prints out the word.
			if(uYes==true)
			{
				testing=true;
				System.out.println(words[i]);
			}
			//resets the booleans for the letters.
			aYes=false;
			eYes=false;
			iYes=false;
			oYes=false;
			uYes=false;
		}
    }



}
