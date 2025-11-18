package Java.Day2.DesignPatterns.Singleton;

/*
 * 
 * SINGLETONS
 * 
 *      when you only want ONE instance of an object
 * 
 *      
 *      useful when working with threads - every thread will be using the same object
 */


public class Singleton {
    
    /*
     * EAGER vs LAZY LOADING
     * 
     *      eagerly loading - instantiate object as soon as possible
     *          faster but could result in unnecesary memory allocation
     * 
     *      lazily loaded - only instantiate the object when it is asked for
     *          slower but no memory is wasted
     */         

    
    private static Singleton instance;      // THIS is the object

    //private static Singleton ins = new Singleton(); // this is eagerly loading
    private int count = 0;

    public Singleton() {

    }

    public void increment() {
        this.count++;
    }

    public void decrement() {
        this.count--;
    }

    public int getCount() {
        return this.count;
    }

    public static Singleton getInstance() {
        //return ins;       if eagerly loading, return the instance

        // lazily loading
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}
