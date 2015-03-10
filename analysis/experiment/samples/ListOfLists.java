
import java.util.*;

public class ListOfLists {

    public static void main (String[] argv)
    {
        // The method makeListOfLists() will return the list of lists.
        LinkedList<LinkedList<String>> listOfLists = makeListOfLists ();

        // We'll print each list on a separate line.
        // Note the use of the new for-loop.
        int count = 0;
        for (LinkedList<String> strList: listOfLists) {

            // Each strList is a string-list, a list of strings.

            System.out.print ("Contents of list# " + count + ": ");

            // Print all the strings in strList.
            for (String s: strList) {
                System.out.print ("    " + s);
            }

            System.out.println ();
            count ++;

        }
    }


    static LinkedList<LinkedList<String>> makeListOfLists ()
    {
        // First, we'll make three linked lists containing String's.

        LinkedList<String> classics = new LinkedList<String>();
        classics.add ("Citizen Kane");
        classics.add ("Casablanca");
        classics.add ("The Godfather");
        
        LinkedList<String> kids = new LinkedList<String>();
        kids.add ("Bambi");
        kids.add ("Snow White");
        kids.add ("Lion King");
        kids.add ("Toy Story");

        LinkedList<String> sciFi = new LinkedList<String>();
        sciFi.add ("2001: A Space Odyssey");
        sciFi.add ("Star Wars");
        sciFi.add ("Jurassic Park");

        // Create an instance of the list that will contain the others.
        LinkedList<LinkedList<String>> listOfLists = new LinkedList<LinkedList<String>>();

        // Now add each list to this list. Since a list can store
        // any kind of object, and a list is an object, we can store lists in a list.
        listOfLists.add (classics);
        listOfLists.add (kids);
        listOfLists.add (sciFi);

        return listOfLists;
    }
    

}
