import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;


class WordInfo {

    String originalWord;               // Store the full word here. You must do this.
    int subWordLength;                 // What is the length of each subword here. Again, you need to set this correctly.
    LinkedList<String> subWords;       // The list of subwords of that length.

    LinkedList<String> foundSubWords;  // This is all the words currently found by the user.


    // This will print out all the subwords into a string.
    public String toString ()
    {
	String s = "Subwords of string \"" + originalWord + "\" of length " + subWordLength + ": \n";
	if ( (subWords == null) || (subWords.size() == 0) ) {
	    s += "  NONE";
	    return s;
	}
	for (int i=0; i<subWords.size(); i++) {
	    s += "  " + subWords.get(i) + "\n";
	}
	return s;
    }

} // end-WordInfo



class TextTwistGUI extends JFrame {

    /////////////////////////////////////////////////////////////////////////////////
    //
    // Variables

    JTextArea textArea;             // We'll write the results into this textbox.
    JTextField stringField;         // Input a word from the user.
    JScrollPane scrollPane;         // A GUI element.
    JLabel wordLabel;               // We'll write out the word here in scrambled form.
    JLabel topLabel;                // This is where messages are written to the user.

    static String[] dictionary;            // The dictionary.
    LinkedList<String> wordList;    // This is a list of words or puzzles, one per game.
    int wordListCount;              // Where we are in our list.

    String currentWord;             // The current word, unscrambled.
    WordInfo[] subWordInfo;         // For the current word, all the subword info.



    /////////////////////////////////////////////////////////////////////////////////
    //
    // Constructor. This has all the GUI building stuff.

    public TextTwistGUI ()
    {
        // Set some parameters of the frame (window) that's brought up.
        this.setSize (600, 600);
        this.setTitle ("TextTwist");
        this.setResizable (true);

        // This is how stuff is put into a frame.
	Container cPane = this.getContentPane();

	// Label at the top.
	topLabel = new JLabel("      ");
	cPane.add (topLabel, BorderLayout.NORTH);

        // Create an instance of the text area and put that in a scrollpane.
        textArea = new JTextArea ();
	scrollPane = new JScrollPane (textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        cPane.add (scrollPane, BorderLayout.CENTER);
        
        // Make the controls. This has two parts.
	JPanel bottomPanel = new JPanel ();
	bottomPanel.setLayout (new GridLayout(2,1));

	JPanel topPart = new JPanel ();
	wordLabel = new JLabel ("       ");
        wordLabel.setFont (new Font ("Serif", Font.BOLD, 30));
	topPart.add (wordLabel);
	topPart.add (new JLabel ("       "));
	bottomPanel.add (topPart);

        JPanel bottomPart = new JPanel ();
        JLabel label = new JLabel ("Enter sub-word: ");
        bottomPart.add (label);
        stringField = new JTextField (10);
        bottomPart.add (stringField);
        JButton button = new JButton ("Add");
	button.addActionListener (
	  new ActionListener () {
	      public void actionPerformed (ActionEvent a)
	      {
		  handleAddButtonClick();
	      }
	  }
        );
        bottomPart.add (button);

	bottomPart.add (new JLabel ("    "));
        JButton shuffleButton = new JButton ("Shuffle");
	shuffleButton.addActionListener (
	  new ActionListener () {
	      public void actionPerformed (ActionEvent a)
	      {
		  handleShuffleButtonClick();
	      }
	  }
        );
        bottomPart.add (shuffleButton);

	bottomPart.add (new JLabel ("    "));
        JButton nextButton = new JButton ("Next word");
	nextButton.addActionListener (
	  new ActionListener () {
	      public void actionPerformed (ActionEvent a)
              {
		  handleNextButtonClick();
	      }
	  }
        );
        bottomPart.add (nextButton);
	bottomPanel.add (bottomPart);

	bottomPart.add (new JLabel ("    "));
        JButton quitButton = new JButton ("Quit");
	quitButton.addActionListener (
	  new ActionListener () {
	      public void actionPerformed (ActionEvent a)
              {
		  System.exit(0);
	      }
	  }
        );
        bottomPart.add (quitButton);

	bottomPanel.add (bottomPart);
        
        // The GUI is now built.
        cPane.add (bottomPanel, BorderLayout.SOUTH);

        // Get the list of puzzles.
	wordList = getWords();
	wordListCount = 0;
	
        // Bring up the GUI.
        this.setVisible (true);
    }
    

    LinkedList<String> getWords()
    {
	// First, the dictionary.
	dictionary = WordTool.getDictionary ();

        // This is just a test for now, with just two puzzle words. 
        // What we should do is populate the list with "reasonable" 
        // words that don't have too many subwords. For example, 
        // "Conversation" is not a good word because it has too 
        // many subwords. One approach may be to fill this from
        // the dictionary with all words that are between 5 and 8
        // letters long.
	LinkedList<String> list = new LinkedList<String>();
	for(String word:dictionary){
	    if(word.length()>=5 && word.length()<=8){
		list.add(word);
	    }
	}
		
	return list;
    }


    /////////////////////////////////////////////////////////////////////////////////
    //
    // Handle input.


    // When the user clicks the add button, this method gets called.

    void handleAddButtonClick ()
    {
        // Extract the string from the textfield where the user typed the strings.
        String inputStr = stringField.getText ();

        // Some sanity checks.
	if (inputStr == null) {
	    writeOutput ();
	    return;
	}
	inputStr = inputStr.trim ();
	if (inputStr.length() == 0) {
	    writeOutput ();
	    return;
	}

	// Check word against those already put up.
	int len = inputStr.length();
	if (subWordInfo[len].subWords == null) {
	    writeOutput ();
	    return;
	}

        // If this is not a subword, ignore it.
	if (! subWordInfo[len].subWords.contains (inputStr) ) {
	    writeOutput ();
	    return;
	}

        // We've got a new subword, so add that to the list of "found" subwords.
	if (subWordInfo[len].foundSubWords == null) {
	    subWordInfo[len].foundSubWords = new LinkedList<String>();
	}
	subWordInfo[len].foundSubWords.add (inputStr);

        // Put the output string in the text box.
	writeOutput ();

        // Clear the textfield.
	stringField.setText ("");
    }

    

    // Build the string that goes into the text box.

    void writeOutput ()
    {
        // Sanity check.
	if (currentWord == null) {
	    return;
	}

        // This is the string we will build.
	String s = "";

	boolean finished = true;

        // Repeat for each possible subword size.
	for (int n=3; n<= currentWord.length(); n++) {

	    int numWords = subWordInfo[n].subWords.size();
	    int numWordsFound = 0;
	    if (subWordInfo[n].foundSubWords != null) {
		numWordsFound = subWordInfo[n].foundSubWords.size();
	    }
	    if (numWordsFound != numWords) {
		finished = false;
	    }
	    s += "Found " + numWordsFound + " words out of " + numWords + " words of length " + n + ":\n";
	    if (subWordInfo[n].foundSubWords != null) {
		for (int i=0; i<subWordInfo[n].foundSubWords.size(); i++) {
		    String w = subWordInfo[n].foundSubWords.get(i);
		    s += "  " + w + "\n";
		}
	    }	    
	    s += "\n";

	} //end-for

	if (finished) {
	    topLabel.setText ("Congratulations! You found all the words!");
	}
	textArea.setText (s);
    }


    // This is simple: every time the user hits the "shuffle" button,
    // we randomly permute the letters and write it back.

    void handleShuffleButtonClick ()
    {
	setPermutedWordLabel ();
    }


    void setPermutedWordLabel ()
    {
	String perm = permute (currentWord);
        // We may have to repeat a couple of times.
	while ( perm.equalsIgnoreCase (currentWord) ) {
	  perm = permute (currentWord);
	}
	wordLabel.setText (perm);
    }

    //figures out a new mixed up pattern
    String permute (String word)
    {
        // Random permutation.
	//creates the preconditions to pass to the permutation method
	char[] letters=word.toCharArray();
	char[] spots=new char[word.length()];
	LinkedList<String> subWor=new LinkedList<String>();
	//passes the arrays and linked lists to the method that generates permutations
	//and returns them as one whole linked list
	LinkedList<String>combinations = options(letters, word.length(),spots,subWor,0);
	//generates a random number that is less than the size of the linked list
	Random randomGenerator= new Random();
	int rand=randomGenerator.nextInt(combinations.size());
	//returns a random single permutation
	return combinations.get(rand);
    }
	
    
    void handleNextButtonClick ()
    {
	textArea.setText ("");
	topLabel.setText ("");

        // Get the next word.
        if (wordListCount >= wordList.size()) {
            // No more words left.
            topLabel.setText ("Game over. No more puzzles left");
            return;
        }
        
	currentWord = wordList.get (wordListCount);
        wordListCount ++;

        topLabel.setText ("Building subwords ... wait...");
        
	// Compute all sub-words.
	subWordInfo = findSubWords (currentWord);

	// Make a random perm and display it.
	setPermutedWordLabel ();
        topLabel.setText ("  ");
    }


    static boolean isDictionaryWord (String word)
    {
	for (int i=0; i<dictionary.length; i++) {
	    if ( dictionary[i].equalsIgnoreCase (word) ) {
		return true;
	    }
	}
	return false;
    }

    // INSERT YOUR CODE HERE
    //Modified version of the subwords method to find mixed up versions of the word
    public static LinkedList<String> options(char[]word, int length, char[] subword, LinkedList<String> subWordsList, int count)
    {
	String temp;
	//bottom out
	if(length==0){
	    temp="";
	    for(int k=0; k<subword.length; k++)
		{
		    temp=temp+subword[k];
		}
	    //will trim white space
	    temp=temp.trim();
	    //adds it to the list
	    subWordsList.add(temp);
	    return subWordsList;
	}
	for(int i=0; i<subword.length; i++)
	    {
		if(subword[i]=='\u0000')
		    {
			subword[i]=word[count];
			options(word,length-1,subword, subWordsList,count+1);
			subword[i]='\u0000';
		    }
	    }
        return subWordsList; 
    }
  

    public static  LinkedList<String> subwords(char[] word, int length,int n, char[] subword, LinkedList<String> subWordsList,int count)
    {
       
	LinkedList<String> subword2=new LinkedList<String>();
	String temp;
	//bottom out case
	if(length==0){
	    temp="";
	    for(int k=0; k<subword.length; k++)
		{
		    temp=temp+subword[k];
		  
		}
	    //trims white space off of string
	    temp=temp.trim();
	    //creates a substring based on the length of the required word
	    String temp2=temp.substring(0,n);
	    //checks if its in the dictionary, and if it is then add it to list
	    if(isDictionaryWord(temp2) && !subWordsList.contains(temp2))
		  {
		    subWordsList.add(temp2);
		  }
	    return subWordsList;
	    
	}
	//adds the letters to the array to form combinations recursivley
	for(int i=0; i<subword.length; i++)
	    {
		if(subword[i]=='\u0000')
		    {
			subword[i]=word[count];
			subwords(word,length-1,n, subword ,subWordsList,count+1);
			subword[i]='\u0000';	
	      
		    }
	    }
	return subWordsList; 
    }


    //creates the linkedlists to store the various size words.
    public static LinkedList<String> size3=new LinkedList<String>();
    public static LinkedList<String> size4=new LinkedList<String>();
    public static LinkedList<String> size5=new LinkedList<String>();
    public static LinkedList<String> size6=new LinkedList<String>();
    public static LinkedList<String> size7=new LinkedList<String>();
    public static LinkedList<String> size8=new LinkedList<String>();



    //method that creates a linkedlist containing all of the subwords 
    //of a given length and then returns the list
    public static LinkedList<String> divideSubwords(LinkedList<String> subwords, int length)
    {
	LinkedList<String> words=new LinkedList<String>();
	for(int i=0; i<subwords.size(); i++)
	    {
		String word=subwords.get(i);
		if(word.length()==length)
		    {
			words.add(word);
		    }
	    }
	return words;
    }

    //method that finds the subwords of a given word
   public static  WordInfo[] findSubWords(String word)
    {
	
	LinkedList<String> subWords2=new LinkedList<String>();
	LinkedList<String> tempList=new LinkedList<String>();
	//LinkedList<String> subWords5=new LinkedList<String>();
	char[] letters=word.toCharArray();
	char[] spots=new char[word.length()];
	
        WordInfo[] wordy= new WordInfo[word.length()+1];
	for(int i=3; i<=word.length(); i++)
	    {
		wordy[i]=new WordInfo();
		wordy[i].originalWord=word;
		wordy[i].subWordLength=i;
		tempList=subwords(letters, word.length(),i,spots,subWords2,0);
		wordy[i].subWords=divideSubwords(tempList,i);
	    }

	return wordy;
    }
}


    

public class TextTwist {

    public static void main (String[] argv)
    {
	TextTwistGUI t = new TextTwistGUI ();
    }

}
