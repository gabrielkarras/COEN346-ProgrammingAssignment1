/*
    Course: COEN 346 Programming Assignment 1
    Professor: B.Goodarzi
    Date: 23/09/2020
    Author(s): Gabriel Karras - 40036341
               Alexia Capitina - (insert Id)
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

        // Input
        String f1 = "input.txt";
        // String f2 = "input2.txt";

        int[] entry1 = ReadFile(f1);
        // int[] entry2 = ReadFile(f2);

        System.out.println("\ninput.txt");
        System.out.print(Arrays.toString(entry1));
        // System.out.println("\ninput2.txt");
        // System.out.print(Arrays.toString(entry2));


        // Find Defective Light Bulbs
        try{
            FindDefective search = new FindDefective(entry1, 0, entry1.length);
            search.start();
            search.join();

            // Output
            System.out.println("\nSystem thread counter: " + Thread.activeCount());
            System.out.println("\nDefective bulbs are: " + search.getDefectives());
            System.out.println("\nThe number of threads for this problem is " + search.getCounter() ) ;

        }
        catch(Exception e){
            e.getStackTrace();
        }
    }


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
