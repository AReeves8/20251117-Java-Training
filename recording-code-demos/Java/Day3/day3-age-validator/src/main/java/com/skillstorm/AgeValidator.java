package com.skillstorm;

public class AgeValidator {
    

    public boolean oldEnoughToDrive(Person person) {
        if(person.getAge() >= 16) {
            System.out.println(person.getName() + " is old enough to drive.");
            return true;
        }
        else {
            /*
             * 
             * THROWS vs THROW
             *      throw - "I am literally throwing an exception right now."
             *      throws - "Hey everybody, this method could throw an excpetion so be prepared to catch it."
             */
            throw new InvalidAgeException("Must be at least 16 yeares old to drive.");
        }
    }

    public boolean oldEnoughToVote(Person person) {
        if(person.getAge() >= 18) {
            System.out.println(person.getName() + " is old enough to vote.");
            return true;
        }
        else {
            throw new InvalidAgeException("Must be at least 18 yeares old to vote.");
        }
    }
    
    public boolean oldEnoughToPurchaseAlcohol(Person person) {
        if(person.getAge() >= 21) {
            System.out.println(person.getName() + " is old enough to purchase alcohol.");
            return true;
        }
        else {
            throw new InvalidAgeException("Must be at least 21 yeares old to purchase alcohol.");
        }
    }
}
