package Java.Day2.MultiThreading;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPractice {
    
    /*
     * every thread has its own stack
     *      so things liek methods and instance variables, each thread will have its own copy of
     * 
     *  volatile - synchronization across threads
     *           won't block other threads from manipulating the variable
     * 
     *  static - means that there is only 1 reference in memory
     */
    private static volatile int count = 0;


    /*
     * ATOMIC VALUES
     *      basically means "all at once" and "all or none"
     * 
     * 
     *      AtomicInteger, AtomicDouble, etc.
     *          creates Thread-safe versions of the primitive type
     * 
     * 
     *      all methods are atomic by default
     */
    private static AtomicInteger atomicCount = new AtomicInteger(0);

    public static void increment() {
        count++;
        atomicCount.incrementAndGet();      // ++atomicCount vs atomicCount++
    }
    
    
    public static void main(String[] args) {
        
        // creating two threads that will each loop 100000000 times
        CustomThread t1 = new CustomThread(100000000);
        CustomThread t2 = new CustomThread(100000000);
        
        //expected result: count and atomicCount should be 200000000

        t1.start();
        t2.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // this isn't synchronizd so it is not likely to be up to date
        System.out.println("Current count is " + count);

        try {

            // telling main to wait for these threads to finish
            t1.join();
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("Total count is " + count);
        System.out.println("Total atomicCount is " + atomicCount);


        /*
         * CONCURRENCY ISSUES
         *      lost update - when a variable is updated, but is then changed immediately after by something else
         * 
         */
    }
}


class CustomThread extends Thread {

    private int loopAmount;

    public CustomThread(int loopAmount) {
        this.loopAmount = loopAmount;
    }

    @Override
    public void run() {
        for(int i = 0; i < loopAmount; i++) {

            // increamenting count and atomicCount
            ThreadPractice.increment();
        }
    }
}
