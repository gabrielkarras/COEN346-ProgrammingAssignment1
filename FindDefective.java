import java.util.ArrayList;
import java.util.List;


public class FindDefective extends Thread {


    private int low; // lower limit of array
    private int high; // higher limit of array
    private int[] array; // array of light bulbs
    static int threadCount = 1; // Records total number of threads in program
    static List<Integer> defectives = new ArrayList<Integer>(); // Dynamic(Re-sizeable) array of integers


    /**
     * Find Defective Constructor
     * @param array of lightbulbs
     * @param low lower limit of passed array
     * @param high upper limit of passed array
     */
    public FindDefective(int[] array, int low, int high)
    {
        this.array = array;
        this.low = low;
        this.high = high;
    }


    /**
     *  As long as our lower limit doesn't cross our upper limit(this means the array is of size 0)
     *  then we generate the middle entry and look if it contains a dead light bulb(0).
     *  If it is a dead lightbulb, we then log it in the defective array.
     *  We then branch one thread to the left side of the middle index,
     *  and the other thread to the right side of the middle index.
     *  For each called created thread we increase the thread counter.
     *  This continues until
     */
    @Override
    public void run() {

        int mid = (high + low)/2; // index of middle entry
        if ( !lighting(array, low, high)) { // We proceed with search when there's is still no lighting

            // Verifying that our high and low pointers don't cross, otherwise keep searching
            if( high > low ){

                // Search the array by dividing into two sub-array (2 threads)
                // Creating left thread
                FindDefective tleft = new FindDefective(array, low, mid);
                increaseCounter();
                // Creating right thread
                FindDefective tright = new FindDefective(array, mid + 1, high);
                increaseCounter();

                tleft.start();
                tright.start();

                try {
                    tleft.join();
                    tright.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                // Verify if the array value is 0. If yes, log that position in the array list
                if (array[mid] == 0 ) {
                    logDefective(mid+1) ;
                }
            }
        }
    }


    /**
     * Searches throughout the given array for defective lightbulbs.
     * If it finds defective lightbulbs it will return False
     * Otherwise, it will return True
     * @param array of lightbulbs
     * @param low pointer to current lower limit of search within the array
     * @param high pointer to current upper limit of search within the array
     * @return False if we find a defective lightbulb within the array, otherwise True
     */
    public boolean lighting(int []array, int low,int high){
        for (int i =low; i<=high;i++){
            if (array[i] == 0){
                return false;
            }
        }
        return true;
    }

    /* Getter and Setter methods */
    /**
     * Adds index of defective light bulb to defective array list
     * @param index of defective light bulb
     */
    public synchronized void logDefective(int index)
    {
        this.defectives.add(index);
    }

    /**
     * Returns array of defectives
     */
    public List<Integer> getDefectives()
    {
        return this.defectives;
    }

    /**
     * Prints array of defectives
     */
    public void printDefectives()
    {
        System.out.println("Defective bulbs are: " + defectives.toString());
    }

    public void resetDefectives()
    {
        this.defectives.clear();
    }

    /**
     * Increases thread count by + 1
     */
    public synchronized void increaseCounter()
    {
        this.threadCount++;
    }

    /**
     * Retrieves thread counter
     * @return int: threadCount
     */
    public int getCounter()
    {
        return this.threadCount;
    }

    /**
     *  Prints content of threadCount
     */
    public void printCounter()
    {
        System.out.println("The number of threads for this problem is " + this.threadCount);
    }

    /**
     * Resets thread counter back to 1 (this includes the main thread)
     */
    public void resetCounter()
    {
        threadCount = 1;
    }

}
