
import java.util.*;

public class WordMorph {

    static String[] words;                 // English dictionary (lowercase).

    static LinkedList<LinkedList<String>> results;     // The result.


    static LinkedList<LinkedList<String>> findLinks (String startWord, String endWord, int numSteps)
    {
        // Read in the dictionary.
        words = WordTool.getDictionary();
        
        results = new LinkedList<LinkedList<String>>();

        // INSERT PART OF YOUR CODE HERE to call the recursive method
		LinkedList<String> list1=new LinkedList<String>();
		list1.add(startWord);
		recursive(startWord,endWord,numSteps,list1);
        return results;
    }


    // INSERT YOUR CODE HERE (for the recursive method)
	static void recursive(String startWord, String endWord, int numSteps, LinkedList<String>list1)
	{
		//break out case: done with all steps
		if(numSteps==0)
		{ 
			if(startWord.equals(endWord))
			{
				//makes a copy of the list and then adds it to results
				results.add(copy(list1));
			}
			return;
			
		}
		//scans through dictionary
		for(int i=0; i<words.length; i++)
		{
			//tests if they are off by one and the list doesnt contain it yet
			if(offByOne(startWord,words[i]) && !list1.contains(words[i]))
			{
				//adds to the list
				list1.add(words[i]);
				//recursively searches for the next word
				recursive(words[i],endWord,numSteps-1,list1);
				//undoes previous ones
				list1.removeLast();
			}
		}
		
	}

    static boolean offByOne (String word1, String word2) 
    {
        // INSERT YOUR CODE HERE.
		//checks to see if they are not equal to the same length
		if(word1.length()!=word2.length())
		{
			return false;
        }
		//creates a counter to keep track of number of differences in the letter.
		int letterCount=0;
        // Return true if the two words are of the same length
        // and differ by exactly one letter.
		//creates two char arrays to compare the letters
		char[] wordOne=word1.toCharArray();
		char[] wordTwo=word2.toCharArray();
		//scans through and compares the two words to eachother
		for(int i=0; i<wordOne.length; i++)
			{
				if(wordOne[i]!=wordTwo[i])
				{
					letterCount++;
				}
			}
		//returns true if count differs by one and false if count differs by anything else
		return (letterCount==1);
    }
    
    

    // You might or might not find this method useful.

    static LinkedList<String> copy (LinkedList<String> list)
    {
        LinkedList<String> copyList = new LinkedList<String>();
        for (String s: list){
            copyList.add (s);
        }
        return copyList;
    }
    

    public static void main (String[] argv)
    {
		boolean test=false;
        // Test 
        LinkedList<LinkedList<String>> results = findLinks ("east", "west", 4);
        print (results);

        // Test 2:
        results = findLinks ("lead", "gold", 4);
        print (results);

        // Test 3:
        results = findLinks ("cat", "dog", 4);
        print (results);
		
		//test 4: tests for mismatched sizes
		test=offByOne("cast","dogged");
		System.out.println("Should be false: "+test);
		
		//test 4: tests for mismatched sizes
		test=offByOne("cast","cart");
		System.out.println("Should be true: "+test);
		
    }

    
    static void print (LinkedList<LinkedList<String>> listOfLists)
    {
        int count = 0;
        for (LinkedList<String> list: listOfLists) {
            for (String s: list) {
                System.out.print ("  " + s);
            }
            System.out.println ();
            count ++;
        }
        System.out.println (" => " + count + " different morphs");
    }
    

}
