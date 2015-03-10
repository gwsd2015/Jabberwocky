
class ListItem {

    String data;
    ListItem next;

}


class CircularList {

    // The usual list variables.
    ListItem front = null;
    ListItem rear = null;

    // To keep track of the size.
    int numItems = 0;

    //adds item to the list
    public void add (String s)
    {
	if(front==null){
	    front=new ListItem();
	    front.data=s;
	    rear=front;
	    rear.next=front;
	    front.next = front;
	}
	else{
	    ListItem nextOne=new ListItem();
	    nextOne.data=s;
	    rear.next=nextOne;
	    nextOne.next=front;
	    rear=nextOne;
	   
	    
	}
	numItems ++;
    }

    //returns size of list
    public int size ()
    {
	return numItems++;
    }

    //puts the list in a string
    public String toString ()
    {
	if(front == null){
	    return "empty";
	}

	String s = "[";
	ListItem listPtr = front;
	int counter=0;
	while(counter!=numItems)
	    {
		s += " \"" + listPtr.data + "\"";
		listPtr = listPtr.next;
		counter++;
	    }
	return s + "]";
	
    }


    public String fire (String assassin)
    {
	ListItem listPtr = front;
	int counter=0;
	//checks if the killer is there
	if(listPtr==null)
	    {
		return "no killers";
	    }
	//locates where the killer is 
	while(! listPtr.data.equals(assassin) && counter<numItems){
	    listPtr=listPtr.next;
	    counter++;
	}
	//returns no assassin if not found
	if(listPtr==null || (!listPtr.data.equals(assassin)))
	    {
		return "no such assassin";
	    }
	//creates a string to store victim name
	String victim=listPtr.next.data;
	//checks if there are victims left
	if(numItems==1){
	    return "No victims";
	}
	//if the victim is in the front it readjusts pointers
	if(listPtr.next==front)
	    {
		front=front.next;
		rear.next = front;
     
	    }
	//if the victim is in the back they readjust the pointer
	else if(listPtr.next==rear)
	    {
		rear=listPtr;
		listPtr.next=front;
	       
	    }
	//otherwise it readjusts pointers in the middle
	else
	    {
		listPtr.next=listPtr.next.next;
	    }
	numItems --;
	return victim;
    }

}


public class AssassinGame {

    public static void main (String[] argv)
    {
        CircularList assassins = new CircularList ();
        assassins.add ("Jackal");
        assassins.add ("Mata Hari");
        assassins.add ("John Wilkes Booth");
        assassins.add ("Lee Harvey Oswald");
        assassins.add ("Gavrilo Princip");
        assassins.add ("James Earl Ray");
        assassins.add ("Jack Ruby");
	//System.out.println("Pass add");

        System.out.println (assassins);
	//System.out.println("Pass toString");
		
        String victim = assassins.fire ("Gavrilo Princip");
        System.out.println ("\nGavrilo's victim: " + victim + "\n  Remaining: " + assassins);
        // Gavrilo's victim: James Earl Ray
		
        victim = assassins.fire ("Jack Ruby");
        System.out.println ("\nJack's victim: " + victim + "\n  Remaining: " + assassins);
        // Jack's victim: Jackal
	
        victim = assassins.fire ("Mata Hari");
        System.out.println ("\nMata's victim: " + victim + "\n  Remaining: " + assassins);
        // Mata's victim: John Wilkes Booth

        victim = assassins.fire ("Jackal");
        System.out.println ("\nJackal's victim: " + victim + "\n  Remaining: " + assassins);
        // Victim: Error: no such assassin.

        victim = assassins.fire ("Jack Ruby");
        System.out.println ("\nJack's victim: " + victim + "\n  Remaining: " + assassins);
        // Jack's second victim: Mata Hari
	
 }
    

}
