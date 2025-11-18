package Java.Day2.DesignPatterns.ProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;

/*
 * This does the same code as the ProducerConsumer class.
 * 
 * The difference is we use a regular queue that is not thread-safe
 * so we have to be more active in managing the thread safety to avoid concurrency issues
 */

public class ProdCon_RegularQueue {
    public static void main(String[] args) {
        
        // a regular queue that is non-concurrent
        final Queue<Integer> queueBuffer = new LinkedList<>();   // Queue is deprecated and usually implemented with some kind of linked-list instead
        final int capacity = 5;

        Thread producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int value = 0;
                while (true) {

                    // this synchronized block will lock the specified resource 
                    synchronized (queueBuffer) {
                        while (queueBuffer.size() == capacity) {
                            try {

                                // when the queue is at capacity, we need the thread enter a WAITING state
                                queueBuffer.wait();

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("Producer produces: " + value);
                        queueBuffer.add(value++);

                        /*
                         * notify will alert other threads to tell them they don't have to wait anymore
                         *      notify() - a single thread
                         *      notifyAll() - every other thread
                         */
                        queueBuffer.notifyAll();
                    }
                }
            }
        });

        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (queueBuffer) {
                        while (queueBuffer.isEmpty()) {
                            try {
                                queueBuffer.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        int value = queueBuffer.poll();
                        System.out.println("Consumer consumes: " + value);
                        queueBuffer.notifyAll();
                    }
                }
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
