
class ListItem {

    KeyValuePair kvp;     // Instead of our usual String or int, we store key-value pairs.
    ListItem next;        // The usual next pointer.

}


public class OurLinkedListMap {

    ListItem front = null;
    ListItem rear = null;

    int numItems = 0;


    public void add (KeyValuePair kvp)
    {
        if ( contains (kvp.key) ) {
            return;
        }

	if (front == null) {
	    front = new ListItem ();
	    front.kvp = kvp;
	    rear = front;
	    rear.next = null;
	}
	else {
            ListItem nextOne = new ListItem ();
	    nextOne.kvp = kvp;
	    rear.next = nextOne;
	    rear = nextOne;
	}    

	numItems ++;
    }


    public int size ()
    {
	return numItems;
    }

    

    public boolean contains (String key)
    {
	if (front == null) {
	    return false;
	}

        // Start from the front and walk down the list. If it's there,
        // we'll be able to return true from inside the loop.
	ListItem listPtr = front;
	while (listPtr != null) {
	    if ( listPtr.kvp.key.equals(key) ) {
		return true;
	    }
	    listPtr = listPtr.next;
	}
	return false;
    }


    public KeyValuePair getKeyValuePair (String key)
    {
        if (numItems == 0) {
            return null;
        }
        
	ListItem listPtr = front;
	while (listPtr != null) {
	    if ( listPtr.kvp.key.equals(key) ) {
		return listPtr.kvp;
	    }
	    listPtr = listPtr.next;
	}
	return null;
    }


    public KeyValuePair[] getAllKeyValuePairs ()
    {
        if (numItems == 0) {
            return null;
        }

        KeyValuePair[] allPairs = new KeyValuePair [numItems];
	ListItem listPtr = front;
        int count = 0;
	while (listPtr != null) {
            allPairs[count] = listPtr.kvp;
	    listPtr = listPtr.next;
            count ++;
	}

        return allPairs;
    }
    

}

