import java.util.ArrayList;
import java.util.List;

public class FindDefective extends Thread {

    private int low; // lower limit of array
    private int high; // higher limit of array
    private int[] array; // array of light bulbs
    public int threadCount; // Records total number of threads in program
    List<Integer> defectives = new ArrayList<Integer>(); // Dynamic(Re-sizeable) array of integers


    /**
     * Constructor for defective light bulbs
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
        if( high >= low ){
            int mid = (high + low)/2; // index of middle entry

            if(mid == 0){
                logDefective(mid);
            }

            FindDefective tleft = new FindDefective(array, low, mid - 1);
            FindDefective tright = new FindDefective(array, mid + 1, high);
            tleft.start();
            increaseCounter();
            tright.start();
            increaseCounter();

            try {
                tleft.join();
                tright.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else{
            // do nothing
        }

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
     * Returns array of defectives
     */
    public List<Integer> getDefectives()
    {
        return this.defectives;
    }

    public void printDefectives()
    {
        System.out.println("Defective bulbs are: " + defectives.toString());
    }
}
