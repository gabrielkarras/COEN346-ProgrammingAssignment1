import java.util.ArrayList;
import java.util.List;

public class FindDefective implements Runnable {

    private int low; // lower limit of array
    private int high; // higher limit of array
    private int[] array; // array of light bulbs
    public int threadCount; // Records total number of threads in program
    List<Integer> defectives = new ArrayList<Integer>(); // Dynamic(Re-sizeable) array of integers


    public FindDefective(int[] array, int low, int high)
    {
        this.array = array;
        this.low = low;
        this.high = high;
    }

    @Override
    public void run() {
        if( high >= low ){
            int mid = (high + low)/2; // index of middle entry

            if(mid == 0){
                logDefective(mid);
            }

        }

    }

    /* Getters and Setters function */
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
        System.out.println("Current Thread Count: " + this.threadCount);
    }
}
