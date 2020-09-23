import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class AssignmentMain {

    public static void main(String[] args) throws Exception {

        String f1 = "input.txt";
        String f2 = "input2.txt";

        int[] entry1 = ReadFile(f1);
        int[] entry2 = ReadFile(f2);


        System.out.print(Arrays.toString(entry1));
        System.out.print(Arrays.toString(entry2));
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
