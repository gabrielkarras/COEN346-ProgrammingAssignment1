# **Binary Tree Traversal to Find Defective Light Bulbs using  Multi-Threading** #

Assume that there is a series of light bulbs connected in a sequential manner. In this situation, if one of the bulbs is defective it will cause all the bulbs to be off. A potential solution to find quickly the faulty bulb(s) is to divide recursively the series into sub-series and keep investigating the sub-series that do not show light. We assume the sub-series with no faulty bulb(s) will show light. 

**Goal**: Write a recursive threading method to find the defective bulbs and the number of threads that have been created for this purpose Your design and implementation should meet the following requirements: -The main function should read an input text file and fill into an array of integers. The first line of input defines the size of the array (the number of bulbs) and status of each bulb is given in one line as: The input will consist of the state of each bulb:

•0: to indicate the bulb is defective

•1:  to indicate the bulb is functioning properly

•The program should make sure the input files entries are in the required format (0 or 1)

•The main thread runs “FindDefective” function as a new thread and passes the input array to that thread. The main function should wait until FindDefective terminate and then print out the number of created threads in the program along with the list of defective bulbs. 

•The “FindDefective” function is responsible to select a “pivot” (𝑛𝑛2) and divide its input array (e.g. arr) into  two  sub-arrays  (e.g.  leftArr  and  rightArr).  It  will  then  call  the  “FindDefective”  recursively  by creating  and  starting  the  threads  for the leftArr  and  rightArr.  The  main  requirement  of  this  assignment  is  that  the  implementation  should  enable  traversing  concurrently  leftArr  and  rightArr. Whenever a thread detects a defective bulb it should enter the index of that bulb into another array that is called “defectives”. The main function should print out the content of “defectives” array when all the threads are terminated.

**Hint**: Since “defectives” array is shared among the threads and is filled by different threads, make sure the access to this array is properly synchronized. You may not find any problem for the small size array. However for the big size arrays the synchronization will be an issue.

•The recursion should be invoked as long as there is at least one defective bulb in the sub-series and the sub-series is not lightning up.


## **Design Steps:** ##

 1. Able to read inputs from .txt file
    1. First integer element dictates size
    2. The following entries are either 0 or 1 and populate the tree
		    1. Program must verify that entries are 0 or 1	
 2. Build Tree Traversal:
	  1. Accepts array as input
	  2. FindDefectives is recursive
	  3. FindDefectives(BinarySearch)
	  4. Each recursive branch becomes a thread
	  5. Must count the total number of threads
	  6. Shared data must be Synchronized
      1. The input array while searching
      2. The thread counter
      3. The array of defectives
	  7. Whenever traversal finds a defective bulb, it must log the element index into another array
 3. Must output the content of defective array(indexes of defective light bulbs) and the total number of threads FindDefective used.
