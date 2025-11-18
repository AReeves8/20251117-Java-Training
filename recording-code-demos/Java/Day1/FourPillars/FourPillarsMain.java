package Java.Day1.FourPillars;



public class FourPillarsMain {

    // main is the starting point of your projects
    public static void main(String[] args) {

        // how we print to command line in java
        System.out.println("Hello World!");


        /*
         * POLYMORPHISM
         *      covariance - allows a child class to act in place of a parent class
         */

        Vehicle car = new Car("inline 4", "Honda", "Civic", "car", 4);
        System.out.println(car.toString());
        System.out.println(car.getNumWheels());
        car.start();
        car.drive();


        Vehicle segway = new Segway("segway", 2, "lithium-ion");
        System.out.println(segway.toString());
        System.out.println(segway.getNumWheels());
        segway.start();
        segway.drive();
    }
}