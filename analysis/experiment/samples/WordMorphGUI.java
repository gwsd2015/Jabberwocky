
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class WordMorphGUI extends JFrame {

    JTextArea textArea;       // This is where we'll write the output.
    JScrollPane scrollPane;   // In case the output is large.

    JTextField startField;
    JTextField endField;
    JTextField stepField;

    String startWord;
    String endWord;
    int numSteps;
    

    // Constructor.

    public WordMorphGUI ()
    {
        // GUI parameters:
        this.setSize (700, 600);
        this.setTitle ("Anagram Finder");
        this.setResizable (true);

        // Build the GUI.
	Container cPane = this.getContentPane();
        textArea = new JTextArea ();
	scrollPane = new JScrollPane (textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        cPane.add (scrollPane, BorderLayout.CENTER);
        
        // Make the controls.
        JPanel panel = new JPanel ();
        panel.add (new JLabel ("Start word: "));
        startField = new JTextField (10);
        panel.add (startField);
        panel.add (new JLabel ("     "));
        panel.add (new JLabel ("End word: "));
        endField = new JTextField (10);
        panel.add (endField);
        panel.add (new JLabel ("     "));
        panel.add (new JLabel ("Max steps: "));
        stepField = new JTextField (5);
        panel.add (stepField);
        panel.add (new JLabel ("     "));


        JButton button = new JButton ("Go");
	button.addActionListener (
	  new ActionListener () {
	      public void actionPerformed (ActionEvent a)
	      {
		  handleButtonClick();
	      }
	  }
        );
        panel.add (button);
        cPane.add (panel, BorderLayout.SOUTH);

        this.setVisible (true);
    }
    

    void handleButtonClick ()
    {
        // Extract the two words.
        startWord = startField.getText();
        endWord = endField.getText();
        String stepStr = stepField.getText();
        if ( (startWord == null) || (endWord == null) || (stepStr == null) ) {
            return;
        }
        startWord = startWord.trim();
        endWord = endWord.trim();
        stepStr = stepStr.trim();
        if ( (startWord.length() == 0) || (endWord.length() == 0) ||
             (stepStr.length() == 0) ) {
            return;
        }

        try {
            numSteps = Integer.parseInt (stepStr);
        }
        catch (NumberFormatException e) {
            return;
        }
        // Convert to lower case and remove blanks.
        startWord = startWord.toLowerCase ();
        endWord = endWord.toLowerCase ();

        textArea.setText ("Searching ...");

        // We'll create a separate thread for the actual computation.
        Thread thread = new Thread () {
                public void run () 
                {
                    findLinks ();
                }
            };
        thread.start ();
    }


    void findLinks ()
    {
        // The static method findAnagrams() in the class AnagramFinder.
        LinkedList<LinkedList<String>> results = WordMorph.findLinks (startWord, endWord, numSteps);

        // Build the output.
        String outputStr = "";

        if ( (results == null) || (results.size() == 0) ){
            outputStr = "No links found";
        }
        else {
            int count = 0;
            for (LinkedList<String> list: results) {
                for (String s: list) {
                    outputStr += " " + s;
                }
                outputStr += "\n";
                count ++;
            }
            outputStr += " => found " + count + " different paths";
        }

        // Write it to the text area.
        textArea.setText (outputStr);
    }
    

    public static void main (String[] argv)
    {
        new WordMorphGUI ();
    }

}
