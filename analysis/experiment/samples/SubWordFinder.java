
import java.util.*;


// Use an instance of this for all subwords of a certain length.
// Thus, one instance of this for subwords of length 3, one instance
// for subwords of length 4 etc.

class WordInfo {

    String originalWord;            // Store the full word here. You must do this.
    int subWordLength;              // What is the length of each subword here. Again, you need to set this correctly.
    LinkedList<String> subWords;    // The list of subwords of that length.
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



public class SubWordFinder {

    static String[] dictionary;

    public static void main (String[] argv)
    {
	// Read in the dictionary. Should be used later to 
	// check whether a given letter combination is a real word.
	dictionary = WordTool.getDictionary();

	// The method findSubWords returns an array of WordInfo
	// instances. Thus, subWordInfo[3] is an instance of 
	// WordInfo that has all the info about subwords of length 3.

	WordInfo[] subWordInfo = findSubWords ("house");
	
	for (int i=3; i<=5; i++) {
	    System.out.println (subWordInfo[i]);
	}
    }


    // Check if a given string is in the dictionary.
    // You will find this useful to check whether a given
    // arrangement of letters is in the dictionary.
    
    static boolean isDictionaryWord (String word)
    {
	for (int i=0; i<dictionary.length; i++) {
	    if ( dictionary[i].equalsIgnoreCase (word) ) {
		return true;
	    }
	}
	return false;
    }
    

    // A variation of the above method that might be more useful.
    /* 
    static boolean isDictionaryWord (char[] letters)
    {
	String s = new String (letters);
	return isDictionaryWord (s);
    }
    */
    
    // INSERT YOUR CODE HERE
    public static int n=0;
    public static  LinkedList<String> subwords(char[] word, int length, char[] subword, LinkedList<String> subWordsList,int count)
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
	    String temp2=temp.substring(0,temp.length()-n);
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
			subwords(word,length-1, subword ,subWordsList,count+1);
			subword[i]='\u0000';	
	      
		    }
	    }
	return subWordsList; 
    }

   public static  WordInfo[] findSubWords(String word)
    {
	LinkedList<String> subWords2=new LinkedList<String>();
	//LinkedList<String> subWords4=new LinkedList<String>();
	//LinkedList<String> subWords5=new LinkedList<String>();
	char[] letters=word.toCharArray();
	char[] spots=new char[5];
	
        WordInfo[] wordy= new WordInfo[word.length()+1];
     
	//declares a new object for 3 letter words
	WordInfo wi=new WordInfo();
	wi.subWords=new LinkedList<String>();
	wi.originalWord=word;
       	wi.subWordLength=3;
       	n=2;
       	wi.subWords=subwords(letters,5,spots,subWords2,0);
       	wordy[3]=wi;
	
	//declares a new object for 4 letter words
	WordInfo wordinfo2=new WordInfo();
	wordinfo2.subWords=new LinkedList<String>();
	wordinfo2.originalWord=word;
       	wordinfo2.subWordLength=4;
       	n=1;
       	wordinfo2.subWords=subwords(letters,5,spots,subWords2,0);
       	wordy[4]=wordinfo2;
	
	//declares a new object for 5 letter words
	WordInfo wi3=new WordInfo();
	wi3.subWords=new LinkedList<String>();
	wi3.originalWord=word;
       	wi3.subWordLength=5;
       	n=0;
       	wi3.subWords=subwords(letters,5,spots,subWords2,0);
       	wordy[5]=wi3;
		
	
	return wordy;
    }

} // end-SubWordFinder class



