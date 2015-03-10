
public class WordSearchPuzzle {

    public static void main (String[] argv)
    {
        char[][] puzzle = {
            {'v', 'h', 'n', 'b', 'u', 'b', 'q', 's', 'b', 'r'},
            {'p', 'k', 'j', 'w', 's', 'y', 'a', 'd', 'd', 'o'},
	    {'y', 'c', 'e', 's', 'd', 'r', 'n', 'c', 'e', 'k'},
	    {'d', 'd', 'a', 'e', 't', 'w', 'r', 'z', 'v', 'x'},
	    {'g', 'l', 'g', 'a', 'l', 'a', 'u', 'b', 'r', 't'},
	    {'c', 'n', 'c', 'f', 'z', 's', 't', 'd', 'n', 'l'},
	    {'w', 'o', 'w', 'h', 'i', 'l', 'e', 'i', 'g', 'b'},
	    {'h', 'y', 'm', 'j', 'h', 'k', 'r', 'o', 'c', 'e'},
	    {'n', 'n', 's', 'j', 'k', 'm', 'g', 'v', 'u', 'm'},
	    {'v', 'v', 'j', 'y', 'y', 'c', 'u', 'e', 'v', 'z'}
        };
        String[] words = {"class", "else", "int", "return", "static", "void", "while"};
        findWords (puzzle, words);
    }



    static void findWords (char[][] puzzle, String[] words)
    {
        // INSERT YOUR CODE HERE AND IN OTHER METHODS.
	//creates arrays to store the pos of the word
	int[]backPos=new int[2];
	int[]upwardsPos=new int[2];
	int[]downwardsPos=new int[2];
	int[]diagonalPos=new int[2];
	//runs the method checks for each word in the given array
	for(int i=0; i<words.length; i++)
	    {
		//looks to see if word is found forwards
		forwards(puzzle,words[i]);
		//looks to see if word is found backwards
		backwards(puzzle,words[i]);
		//looks to see if word is found upwards
		upwards(puzzle,words[i]);
		//looks to see if word is found downwards
		downwards(puzzle, words[i]);
		//looks to see if word is found diagonally
		diagonal(puzzle, words[i]);
	    }
	
    }
    //method to check for forward words
    static void forwards(char[][]puzzle,String word)
    {

	//converts word to an array of chars
	char[]letters=word.toCharArray();
	for(int i=0; i<puzzle.length; i++)
	    {
		for(int j=0; j<puzzle[i].length; j++)
		    {
			boolean found=true;
			for(int k=0; k<letters.length; k++)
			    {
				if((j+k>= puzzle[i].length)|| (letters[k]!=puzzle[i][j+k]))
				    {
					found=false;
					break;
				    }
			    }
			if(found)
			    {
				int temp=i+1;
				System.out.println(word+" found at ["+temp+","+j+"] going left to right");
				
			    }
		    }
	    }
    }
    //method to check for backwards words
    static void backwards(char[][]puzzle, String word)
    {
	//converts word to an array of chars
	char[] letters=word.toCharArray();
	for(int i=0; i<puzzle.length; i++)
	    {
		for(int j=0; j<puzzle[i].length; j++)
		    {
			boolean found=true;
			for(int k=0; k<letters.length; k++)
			    {
				if((j-k<=0) || (letters[k]!=puzzle[i][j-k]))
				   {
				       found=false;
				       break;
				   }
			    }
			if(found)
			    {
		       		int temp=i+1;
	       			System.out.println(word+" found at ["+temp+","+j+"] going right to left");
       				
			    }	
		    }
	    } 
    }
    //method to check for upwards words
    static void upwards(char[][]puzzle, String word)
    {
	//converts word to an array of chars
	char[]letters=word.toCharArray();
	for(int i=0; i<puzzle.length; i++)
	    {
		for(int j=0; j<puzzle[i].length; j++)
		    {
			boolean found = true;
			for(int k=0; k<letters.length; k++)
			    {
				if((i-k<0)||(letters[k]!=puzzle[i-k][j]))
				    {
					found=false;
					break;
				    }
			    }
			if(found)
			    {
				int temp=i+1;
				System.out.println(word+" found at ["+temp+","+j+"] going upwards.");
				
			    }
		    }
	    }
	
    }
    //method that checks for downward words
    static void downwards(char[][]puzzle, String word)
    {
	//converts word to an array of chars
	char[]letters=word.toCharArray();
	for(int i=0; i<puzzle.length; i++)
	    {
		for(int j=0; j<puzzle[i].length; j++)
		    {
			boolean found = true;
			for (int k=0; k<letters.length; k++)
		       	   {
			       if((i+k>=puzzle.length)||(letters[k]!=puzzle[i+k][j]))
				  {
				      found=false;
				      break;
				  }       
		       	   }
	       	       if(found==true)
       			   {
			       int temp=i+1;
			       System.out.println(word+" found at ["+temp+","+j+"] going downwards.");
			       
	       		   }      
		    }
	    }
    }
    //method to check if a word is found diagonally
    static void diagonal(char[][]puzzle, String word)
    {
	String direction1;
	String direction2;
	//int downRight=0;
	//converts word to an array of chars
	char[]letters=word.toCharArray();
	for(int i=0; i<puzzle.length; i++)
	    {
		for(int j=0; j<puzzle[i].length; j++)
		    {
			boolean downRight=true;
			boolean upRight=true;
			boolean upLeft=true;
			boolean downLeft=true;
			//checks for words that go down and right
			for(int k=0; k<letters.length; k++)
			    {
				if((j+k>=puzzle[i].length)||(i+k>=puzzle.length) ||(letters[k]!=puzzle[i+k][j+k]))
				    {
					downRight=false;
					break;
				    }
			    }
			//checks for words that go down and left
			for(int k=0; k<letters.length; k++)
			    {
				if((j-k<0)||(i+k>=puzzle.length) ||(letters[k]!=puzzle[i+k][j-k]))
				    {
					downLeft=false;
					break;
				    }
			    }
			//checks for words that go up and left
			for(int k=0; k<letters.length; k++)
			    {
				if(((j-k<0)||(i-k<0))||(letters[k]!=puzzle[i-k][j-k]))
				   {
				       upLeft=false;
				       break;
				   }
			    }
			//checks for words that go up and right
			for(int k=0; k<letters.length; k++)
			    {
				if(((j+k>=puzzle.length)||(i-k<=0))||(letters[k]!=puzzle[i-k][j+k]))
				    {
					upRight=false;
					break;
				    }
			    }
			       
			//evaluates the boolean operators to see which way a word goes diagonally.
			int temp=i+1;
			if(downRight==true)
			    {
				System.out.println(word+" found at ["+temp+","+j+"] going diagonally right and down.");
			    }
			else if(downLeft==true)
			    {
				System.out.println(word+" found at ["+temp+","+j+"] going diagonally left and down.");
			    }
			else if(upLeft==true)
			    {
				System.out.println(word+" found at ["+temp+","+j+"] going diagonally left and up.");
			    }
			else if(upRight==true)
			    {
				System.out.println(word+" found at ["+temp+","+j+"] going diagonally right and up");
			    }
			
		    }
	    }
	
    }
}
