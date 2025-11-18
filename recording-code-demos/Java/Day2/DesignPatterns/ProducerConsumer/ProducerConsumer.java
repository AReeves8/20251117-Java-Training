package Java.Day2.DesignPatterns.ProducerConsumer;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
 * PRODUCER CONSUMER
 *      
 *      one entity (usually a thread) produces data (generate/receive/read in)
 *      another entitty (usually a thread) consumes that data (process/manage/manipulate)
 * 
 *      use multithreading in java to do this
 * 
 */

public class ProducerConsumer {
    public static void main(String[] args) {
        
        /*
         * CONCURRENT COLLECTIONS
         *      java.util.concurrent
         *      thread-safe collections
         *          they will handle synchronization for us
         * 
         *      queues are FIFO
         */
        final Queue<Integer> buffer = new ConcurrentLinkedQueue<>();    // the size of the collection will never change
        final int CAPACITY = 5;                                         // final means the value cannot be changed - capacity will always be 5

        Thread producerThread = new Thread(new Runnable() {
            /*
             * ANONYMOUS INNER CLASS
             *      a class that isn't declared but it is defined
             * 
             *      only created with functional interfaces
             */
            @Override
            public void run() {

                // simulating grabbing these values from somewhere and puttiong them on the queue
                int value = 0;
                while(true) {
                    if(buffer.size() < CAPACITY) {
                        System.out.println("Producer produces: " + value);
                        buffer.add(value++);        
                    }
                }
            }
            
        });


        /*
         * LAMBDA FUNCTIONS
         *      shorthand way to implement the *method* of a functional interface
         */
        Thread consumerThread = new Thread((/* any parmaters for the method */) -> {
            
            // THIS IS THE RUN METHOD FROM THE RUNNABLE CLASS
            
            while(true) {
                if(!buffer.isEmpty()) {
                    int value = buffer.poll();  // poll - retreiving the data at the front of the queue and removing it from the queue
                    System.out.println("Consumer consumes: " + value);
                }
            }
        });
        
        producerThread.start();
        consumerThread.start();
        
    }
}
