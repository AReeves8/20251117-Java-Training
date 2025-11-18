package Java.Day1.FourPillars;


/*
 * INHERITANCE
 *      - when a child class is given access to the variable and meethods in the parent
 *      - extends for classes; can extend only one
 *      - implements for interfaces; can implements multiple
 */
public class Segway extends Vehicle {


    private String battery;

    public Segway() {
        super();
    }

    public Segway(String type, int numWheels, String battery) {
        
        // this();  to call another constructor in the same class

        super(type, numWheels);    // calls the constructor in the parent class
        this.battery = battery;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }    

    @Override
    public void drive() {
        System.out.println(battery + " is used to drive.");
    }

    @Override
    public void start() {
        System.out.println("A button is pressed to start the segway");
    }

    @Override
    public String toString() {
        return "Segway [battery=" + battery + "]";
    }

    
    
}
