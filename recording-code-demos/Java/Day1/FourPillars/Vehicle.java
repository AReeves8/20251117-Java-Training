package Java.Day1.FourPillars;

/*
 * ABSTRACTION
 *      - a class that can have abstract methods
 * 
 *  
 *      - cannot be initialized
 *      - extends keyword
 */
public abstract class Vehicle implements Driveable{
    
    private String type;
    private int numWheels;
    
    public Vehicle() {

    }
    
    public Vehicle(String type, int numWheels) {
        this.type = type;
        this.numWheels = numWheels;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumWheels() {
        return numWheels;
    }

    public void setNumWheels(int numWheels) {

        // only initiliaze the value if it is non-negative
        if(numWheels >= 0) {
            this.numWheels = numWheels;
        }
        
    }


    // an abstract method for how to start the vehicle
    public abstract void start();

    // don't have to implement abstract methods from parents inside of an abstract class
    //public abstract void drive();
}
