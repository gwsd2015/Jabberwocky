
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SpellChecker extends JFrame {

    JTextArea textArea;
    JTextField stringField;
    JScrollPane scrollPane;

    String[] words;

    public SpellChecker ()
    {
        // Set some parameters of the frame (window) that's brought up.
        this.setSize (600, 600);
        this.setTitle ("Spell checker");
        this.setResizable (true);

        // This is how stuff is put into a frame.
	Container cPane = this.getContentPane();
        textArea = new JTextArea ();
	scrollPane = new JScrollPane (textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        cPane.add (scrollPane, BorderLayout.CENTER);
        
        // Make the controls.
        JPanel panel = new JPanel ();
        JLabel label = new JLabel ("Enter string: ");
        panel.add (label);
        stringField = new JTextField (30);
        panel.add (stringField);
        JButton button = new JButton ("Go");
	button.addActionListener (
	  new ActionListener () {
	      public void actionPerformed (ActionEvent a)
	      {
		  handleButtonClick();
	      }
	  }
        );
        panel.add (button);
        cPane.add (panel, BorderLayout.SOUTH);

	// Read in dictionary.
	words = WordTool.getDictionary ();
        
        this.setVisible (true);
    }
    

    String inputStr;


    // When the user clicks the button, this method gets called.
    // It's where we need to respond.

    void handleButtonClick ()
    {
        // Extract the string from the textfield where the user typed the strings.
        inputStr = stringField.getText ();

	String[] matchedWords = findClosestWords (inputStr, words);

	String outputStr = "";

	if (matchedWords ==  null) {
	    outputStr = "No matches found";
	}
	else {
	    for (int i=0; i<matchedWords.length; i++) {
		outputStr += matchedWords[i] + "\n";
	    }
	}

        // Put the output string in the text box.
	String text = textArea.getText ();
	text += outputStr + "\n";
	textArea.setText (text);
    }


    static String[] findClosestWords (String inputWord, String[] words)
    {
	// INSERT YOUR CODE HERE AND IN OTHER METHODS YOU ADD.
	//creates the boolean variables that tell whether a word matches given conditions
	boolean equals=false;
	boolean diff=false;
	boolean longer=false;
	//creates the arraylist that holds all of the words that match the given conditions
	ArrayList<String> closest= new ArrayList<String>();
	//loop used to compare the words in length
	for(int i=0; i<words.length; i++)
	    {
		//if the lengths of the input words are equal then it tests to see if the words are true and sets the boolean to true
		// if they are not equal then it runs the equalsDiffer method to test whether or not the words differ in one letter 
		if(inputWord.length()==words[i].length())
		    {
			if(inputWord.equals(words[i]))
			   {
			       equals=true;
			//equals=equals(inputWord,words[i]);
			   }
			 else diff=equalsDiffer(inputWord,words[i]);
		    }
		//if the input word length is one greater then a target word then it runs diffLength with the input word first
		else if(inputWord.length()==words[i].length()+1)
		    {
			longer=diffLength(inputWord,words[i]);
		    }
		//if the input word length is one less then a target word then it runs diffLength with the target word first
		else if(words[i].length()-1==inputWord.length())
		    {
			longer=diffLength(words[i],inputWord);
		    }
		// checks to see if any of the conditions are met, if they are then the word is added to the list
		if(equals||longer||diff)
		    {
			closest.add(words[i]);
		    }
		//resets the variables
		equals=false;
		longer=false;
		diff=false;
	    }
	// creates a string array in the size of the final list, adds the elements of the list to the array and returns the array
		String[] closestReturned=new String[closest.size()];
		closestReturned=closest.toArray(closestReturned);
		return closestReturned;
	    
    }

    static void test ()
    {
	String inputStr = "asdf";
	String[] words = {"asdf", "aadf", "bsdf", "asdd", "aadd", "asdff", "aasdf", "asd", "assdf"};
	String[] matched = findClosestWords (inputStr, words);
	System.out.println("Performing test one. ");
	print (inputStr, matched);

	
	//test one
	String inputStr2 = "hello";
	String[] words2 ={"hell","hellos","helloe","mello","shello"};
	String[] matched2  = findClosestWords(inputStr2,words2);
	System.out.println("Performing test two. ");
	print(inputStr2,matched2);

	//test two
	String inputStr3 = "late";
	String[] words3 = {"mates","crate","slated","la","maters","lasted","super","teals","blasted"};
	String[] matched3 = findClosestWords(inputStr3,words3);
	System.out.println("Performing test three. ");
	print(inputStr3,matched3);

	//test three
	String inputStr4 = "peal";
	String[] words4 = {"peal"};
	String[] matched4 = findClosestWords(inputStr4,words4);
	System.out.println("Performing test four. ");
	print(inputStr4,matched4);
    }

    static void print (String word, String[] matchedWords)
    {
	System.out.println ("Words that matched: " + word);
	for (int i=0; i<matchedWords.length; i++) {
	    System.out.println ("  " + matchedWords[i]);
	}
    }
    /* static boolean equals(String word1, String word2)
    {
     
	if(word1.length()==word2.length())
	    {
		boolean matches=true;
		for(int i=0; i<word1.length(); i++)
		    {
			if(word1.charAt(i)!=word2.charAt(i))
			    {
				matches=false;
			    }
			else matches=true;
		    }
		return matches;
	    }
	 return false;
	 }*/
    static boolean equalsDiffer(String word1, String word2)
    {
	//creates a conter to keep track of the number of differences
	int diffCount=0;
	//for loop to scan through and compare the letters of the words if they do not match it increases the counter
	for(int i=0; i<word1.length(); i++)
	    {
			if(word1.charAt(i)!=word2.charAt(i))
			    {
				diffCount++;
			    }   
	    }
	//if there is more then one difference then false is returned  
	 if(diffCount!=1)
     	    {
		return false;
	    }
	 // if only one difference is found then true is returned 
	return true;
    }
    static boolean diffLength(String word1, String word2)
    {
	//creates two arrays made up of the chars of both words, creates a boolean to return, and
	//creates a substring of the longest word that excludes the last letter
	char[] input1=word1.toCharArray();
	char[] input2=word2.toCharArray();
	boolean results=false;
	String lastLetter=word1.substring(0,word1.length()-1);
	//System.out.println("lastLetter "+lastLetter);
	//if the substring is equal to the input word then true is returned
	if(lastLetter.equals(word2))
	    {
		return true;
	    }
	for(int i=0; i<word2.length(); i++)
	    {
		//if the char at the specified index of both strings do not match then it begins a test to see if that only happens once
			if(word2.charAt(i)!=word1.charAt(i))
			    {
				//System.out.println("before "+word1);
				//sets the char at the mismatched index to a blank space in the character array and creates an empty string 
				input1[i]=' ';
				String replaced="";
				//runs a loop that converts the char array into a string without including the space
				for(int k=0; k<input1.length; k++)
				    {
					if(input1[k]==' ')
					    {

					    }
					else replaced=replaced+input1[k];
					
				    }
				//trims the string just in case
				replaced.trim();
				//System.out.println("replaced "+replaced);
				//if the new string matches word2 then sets the boolean to true
				if(word2.equals(replaced))
				    {
					results=true;
				    }
				//breaks out of the loop
				break;
			    }
	    }
	//returns the boolean
	return results;
    }
    public static void main (String[] argv)
    {
	test ();
	SpellChecker checker = new SpellChecker ();

    }

}
