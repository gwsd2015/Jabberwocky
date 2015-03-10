
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class WordTool {

    public static String[] getDictionary ()
    {
	return getDictionary ("words");
    }

    public static String[] getDictionary (String fileName)
    {
	String[] words = readDictionary (fileName, null);
        String[] scrubbedWords = scrub (words);
	return scrubbedWords;  
    }

    static String[] readDictionary (String fileName, Pattern pattern)
    {
	String[] words = null;
	try {
	    // Since we don't know in advance how many words there 
	    // are, we'll use a list instead of an array.
	    LinkedList<String> stringList = new LinkedList<String>();

	    // Scanner knows how to skip whitespace.
	    Scanner scanner = new Scanner (new FileReader (fileName));
            if (pattern != null) {
                scanner = scanner.useDelimiter (pattern);
            }
            
	    while (scanner.hasNext()) {
		// At each step, get the next word and place in list.
		String s = scanner.next();
		stringList.addLast (s);
	    }

	    // Now that we know the size, we'll make an array.
	    words = new String [stringList.size()];
	    Iterator<String> iter = stringList.iterator();
	    int i = 0;
	    while (iter.hasNext()) {
		words[i] = iter.next();
		i ++;
	    }

	    // Done.
	    return words;
	}
	catch (IOException e) {
	    System.out.println (e);
	    System.exit (0);
	    return null;
	}
    }


    static String[] readWords (String fileName)
    {
        Pattern p = Pattern.compile ("\\W");
	String[] words = readDictionary (fileName, p);
        String[] scrubbedWords = scrub (words);
	return scrubbedWords;  
    }
    

    static String[] scrub (String[] words)
    {
        // Remove words with caps, and single-letter words
        int badWords = 0;
        for (int i=0; i<words.length; i++) {
            if (words[i].length() <= 1) {
                badWords ++;
                words[i] = null;
            }
            else if ( Character.isUpperCase (words[i].charAt(0)) ) {
                badWords ++;
                words[i] = null;
            }
        }

	// Make space for the good words.
        String[] realWords = new String [words.length - badWords];
        int j = 0;
        for (int i=0; i<realWords.length; i++) {
            while (words[j] == null) {
                j ++;
            }
            realWords[i] = words[j];
            j ++;
        }

        return realWords;
    }


    public static char[] computeRemainder (char[] dividend, char[] quotient)
    {
        // First, copy.
        char[] dividendCopy = copy (dividend);

        // Now check off.
        for (int i=0; i<quotient.length; i++) {
            // Look for quotient[i] in dividendCopy.
            boolean found = false;
            for (int j=0; j<dividendCopy.length; j++) {
                if (dividendCopy[j] == quotient[i]) {
                    found = true;
                    dividendCopy[j] = '#';
                    break;
                }
            }
            if (! found) {
                return copy(dividend);
            }
        }
        
        // If we reached here, all letters were found.
        if (dividend.length == quotient.length) {
            return null;
        }
        
        char[] remainder = new char [dividend.length - quotient.length];
        int j = 0;
        for (int i=0; i<remainder.length; i++) {
            while (dividendCopy[j] == '#') {
                j++;
            }
            remainder[i] = dividendCopy[j];
            j ++;
        }
        return remainder;
    }
    

    static char[] copy (char[] A)
    {
        char[] B = new char [A.length];
        for (int i=0; i<B.length; i++) {
            B[i] = A[i];
        }
        return B;
    }
    

}
