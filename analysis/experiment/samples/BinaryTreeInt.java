
import java.util.*;

class TreeNode {

    int data;        
    TreeNode left;    // Pointer to the left child.
    TreeNode right;   // Pointer to the right child.


    // Useful in debugging:

    public String toString ()
    {
        String str = "[data=" + data;
        String leftData = "null";
        if (left != null) {
            leftData = "" + left.data;
        }
        String rightData = null;
        if (right != null) {
            rightData = "" + right.data;
        }
        str += " left=" + leftData + " right=" + rightData + "]";
        return str;
    }

} //end-TreeNode



public class BinaryTreeInt {

    TreeNode root = null;    // Root of the tree.
    int numItems = 0;        // We'll keep track of how many elements we've added so far.


    public void add (int k)
    {
        // If empty, create new root.
        if (root == null) {
            root = new TreeNode ();
            root.data = k;
            numItems ++;
            return;
        }
        
        // Search to see if it's already there.
        if ( contains (k) ) {
            // Handle duplicates.
            return;
        }
        
        // If this is a new piece of data, insert into tree.
        recursiveInsert (root, k);
        
        numItems ++;
    }

    
    void recursiveInsert (TreeNode node, int k)
    {
        // Compare input data with data in current node.
        if (k < node.data) {
            // It's less. Go left if possible, otherwise we've found the correct place to insert.
            if (node.left != null) {
                recursiveInsert (node.left, k);
            }
            else {
                node.left = new TreeNode ();
                node.left.data = k;
            }
            
        }
        // Otherwise, go right.
        else {
            // It's greater. Go right if possible, otherwise we've found the correct place to insert.
            if (node.right != null) {
                recursiveInsert (node.right, k);
            }
            else {
                node.right = new TreeNode ();
                node.right.data = k;
            }
        }
        
    }
    

    public int size ()
    {
        return numItems;
    }
    

    public boolean contains (int k)
    {
        if (numItems == 0) {
            return false;
        }
        
        return recursiveSearch (root, k);
    }
    

    boolean recursiveSearch (TreeNode node, int k)
    {
        // If input string is at current node, it's in the tree.
        if (k == node.data) {
            // Found.
            return true;
        }

        // Otherwise, navigate further.
        if (k < node.data) {
            // Go left if possible, otherwise it's not in the tree.
            if (node.left == null) {
                return false;
            }
            else {
                return recursiveSearch (node.left, k);
            }
        }
        else {
            // Go right if possible, otherwise it's not in the tree.
            if (node.right == null) {
                return false;
            }
            else {
                return recursiveSearch (node.right, k);
            }
        }

    }


    public void print ()
    {
        if (root == null) {
            System.out.println ("Empty tree");
            return;
        }
        
        System.out.println ("Tree: " + numItems + " nodes");
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add (root);
        while ( ! queue.isEmpty() ) {
            // Extract node.
            TreeNode node = queue.removeFirst ();
            // Add its children to queue.
            if (node.left != null) {
                queue.add (node.left);
            }
            if (node.right != null) {
                queue.add (node.right);
            }
            // Print.
            System.out.println (node);
        }
        
    }
    
    
    public void printRecursively ()
    {
        printTree (root);
    }
    
    
    void printTree (TreeNode node)
    {
        // INSERT YOUR CODE HERE
        // Write this recursively
		if(node==null)
			{
				System.out.println("The Tree is empty");
			}

			System.out.println(node.data);
			if(node.left!=null)
			{
				printTree(node.left);
			}
			if(node.right!=null)
			{
				printTree(node.right);	
			}

    }

}
