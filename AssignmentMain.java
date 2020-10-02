/*
    Course: COEN 346 Programming Assignment 1
    Professor: B.Goodarzi
    Date: 23/09/2020
    Author(s): Gabriel Karras - 40036341
               Alexia Capitina - (40024870)
    Due Date: 03/10/2020

    Title: Binary tree traversal to find defective light bulbs using  multi-threading
    Problem:
        Assume that there is a series of light bulbs connected in a sequential manner.
        In this situation, if one of the bulbs is defective it will cause all the bulbs to be off.
        A potential solution to find quickly the faulty bulb(s) is to divide recursively the series into sub series and
        keep investigating the sub series that do not show light.
        We assume the sub series with no faulty bulb(s) will show light.
 */


/*
    Imported Libraries
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;


public class AssignmentMain{


    public static void main(String[] args) throws Exception {

        // File name
        String f1 = "input.txt";
        String f2 = "input2.txt";

        // Read .txt and write into array
        int[] entry1 = ReadFile(f1);
        int[] entry2 = ReadFile(f2);

        // Print content of input/entries
        System.out.println("\nInput entries for input.txt and input2.txt:");
        System.out.println("\ninput.txt");
        System.out.println(Arrays.toString(entry1));
        System.out.println("\ninput2.txt");
        System.out.print(Arrays.toString(entry2) + "\n");


        // Find Defective Light Bulbs
        try{

            System.out.println("\nResults from input.txt: ");
            // Find defective lightbulbs for input.txt
            FindDefective search1 = new FindDefective(entry1, 0, entry1.length-1);
            search1.start(); // starts thread
            search1.join(); // recalls thread

            // Output for input.txt
            search1.printCounter(); // prints the number of threads for input.txt
            search1.printDefectives(); // prints the index of each defective lightbulb
            search1.resetCounter();
            search1.resetDefectives();

            System.out.println("\nResults from input2.txt: ");
            // Find defective lightbulbs for input2.txt
            FindDefective search2 = new FindDefective(entry2, 0, entry2.length-1);
            search2.start();
            search2.join();

            // Output for input2.txt
            search2.printCounter();
            search2.printDefectives();

        }
        catch(Exception e){
            e.getStackTrace();
        }
    }


    /* Helper Methods*/

    /**
     * Accepts an input file in which the first digit represents the size of the array and
     * the following digits represent the states of the light bulb(0 or 1).
     * The method will then return an array populated by these light bulb states.
     *
     * @param fileName Name of the file and its file path. Must be located outside of src folder
     * @return array of entries(must be 0 or 1)
     * @throws FileNotFoundException Throws exception if system couldn't file the file
     */
    public static int[] ReadFile(String fileName) throws FileNotFoundException {

        // Create Scanner object
        Scanner in = new Scanner(new FileReader(fileName));

        // First digit in .txt represents array size
        int size = in.nextInt(); // Store array size
        int[] entries = new int[size]; // Declare and initialize array

        // Logs the following digits from .txt as array entries
        for(int i = 0; i < size; i++){

            int temp = in.nextInt();
            // Verify if entry is in valid range(0,1) or else print error
            if(temp == 0 || temp == 1){
                entries[i] = temp;
            }
            else{
                throw new IllegalArgumentException("Entry is not in the valid range of (0,1)");
            }
        }
        return entries;
    }
}
