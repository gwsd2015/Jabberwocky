
public class OurHashMap {

    int numBuckets = 100;              // Initial number of buckets.
    OurLinkedListMap[] table;          // The hashtable.
    int numItems;                      // Keep track of number of items added.
    

    // Constructor.

    public OurHashMap (int numBuckets)
    {
        this.numBuckets = numBuckets;
        table = new OurLinkedListMap [numBuckets];
        numItems = 0;
    }


    public void add (KeyValuePair kvp)
    {
        if ( contains (kvp.key) ) {
            return;
        }
        
        // Compute hashcode and therefore, which table entry (list).
        int entry = Math.abs(kvp.key.hashCode()) % numBuckets;

        // If there's no list there, make one.
        if (table[entry] == null) {
            table[entry] = new OurLinkedListMap ();
        }

        // Add to list.
        table[entry].add (kvp);

        numItems ++;
    }
    

    public boolean contains (String key)
    {
        // Compute table entry using hash function.
        int entry = Math.abs(key.hashCode()) % numBuckets;

        if (table[entry] == null) {
            return false;
        }

        // Use the contains() method of the list.
        return table[entry].contains (key);
    }
    

    public KeyValuePair getKeyValuePair (String key)
    {
        // Similar to contains.
        int entry = Math.abs(key.hashCode()) % numBuckets;
        if (table[entry] == null) {
            return null;
        }
        return table[entry].getKeyValuePair (key);
    }


    public KeyValuePair[] getAllKeyValuePairs ()
    {
        if (numItems == 0) {
            return null;
        }

        KeyValuePair[] allPairs = new KeyValuePair [numItems];

        int count = 0;
        for (int entry=0; entry<numBuckets; entry++) {
            if (table[entry] == null) {
                continue;
            }
            
            KeyValuePair[] bucketPairs = table[entry].getAllKeyValuePairs();
            for (int i=0; i<bucketPairs.length; i++) {
                allPairs[count] = bucketPairs[i];
                count ++;
            }
        }

        return allPairs;
    }


    public void printBucketDistribution ()
    {
        System.out.println ("Bucket distribution: ");

        // INSERT YOUR CODE HERE.
		for(int i=0; i<table.length; i++)
		{
			if(table[i]!=null)
			{
				System.out.println("List "+i+" has size "+table[i].size());
			}
			else 
			{
				System.out.println("List "+i+" has size "+0);
				
			}
		}
    }
    
}
