
import java.util.*;

public class ParenBalancingExercise {

    public static void main (String[] argv)
    {
	String s = "((()))";
	checkParens (s);

	s = "((())";
	checkParens (s);

	s = "())))";
	checkParens (s);

	s = "(()()(";
	checkParens (s);
    }


    static void checkParens (String inputStr)
    {
	//creates the array of chars and the stack to keep track of )'s
	char[] letters = inputStr.toCharArray();
	Stack<Character> letterStack = new Stack<Character>();
	//boolean to keep track of special case ())
	boolean unbalanced = false;
	//int for the position
	int pos=0;
	//runs through the array comparing to the ( and )
	//will place ( in the stack and remove it when it reaches a )
	for(int i=0; i<letters.length; i++)
	    {
		if(letters[i] == '('){
		    letterStack.push(letters[i]);
		    pos = i;
		}
		else if(letters[i]== ')'){
		    char ch = ')';
		    if(! letterStack.isEmpty()){
			ch=letterStack.pop();
		       
		    }
		    //allows for the special case of ())
		    if(ch != '('){
			pos=i;
			unbalanced = true;
			break;
		    }
		    pos--;
		}
	    }
	//code for creating the carrot that points to the problem parenthesis
	String location="";
	if(unbalanced || !letterStack.isEmpty())
	    {
		// places the carrot in the correct locations
		for(int j=0; j<pos+1; j++)
		    {
			if (j == pos)
			    {
				location +="^";
			    }
			else
			    location +=" ";
		       
		    }
		
	      }
	if((unbalanced) || (! letterStack.isEmpty())){
	    System.out.println("Unbalanced: "+inputStr);
	    System.out.println("            "+location);
	}
	else{
	    System.out.println("Balanced: "+ inputStr);
	}
    }

}
