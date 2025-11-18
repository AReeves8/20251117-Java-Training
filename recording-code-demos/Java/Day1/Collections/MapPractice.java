package Java.Day1.Collections;

import java.util.HashMap;

public class MapPractice {
    

    public static void main(String[] args) {
        /*
        * MAPS
        *      unordered and operate on key-value pairs
        *      keys have to be 100% unique
        *      values can be anything
        */

        /*
        * HASHMAPS
                these use hashcode() to determine index location
                    this creates constant lookup time O(1)
                built on arrays so they do have indexes, but we don't work with these indexes

                collisions:
                    these *shouldn't* happen
                        but they can
                    
                    when multiple keys have the same hashcode and get put into the same index location
                        java creates a linked-list at that index location

                under the hood: these are arrays of linked-lists
        */

        HashMap<String, Animal> animalMap = new HashMap<>();

        Animal lion = new Animal("leo");
        Animal tiger = new Animal("terry");
        Animal elephant = new Animal("elle");


        // use .put() for insertion
        animalMap.put("lion", lion);
        animalMap.put("tiger", tiger);
        animalMap.put("elephant", elephant);


        // use .get() with the key
        System.out.println(animalMap.get("lion"));

        // use .put() with an existing key to override the value at that key
        Animal lion2 = new Animal("leonardo");
        animalMap.put("lion", lion2);
        System.out.println(animalMap);


        // use keyset() or values() to loop through those
        for(String animal : animalMap.keySet()) {
            System.out.println(animal);
        }
        for(Animal animal : animalMap.values()) {
            System.out.println(animal);
        }

        
    }
    
}
