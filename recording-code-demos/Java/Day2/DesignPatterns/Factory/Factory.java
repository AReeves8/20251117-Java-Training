package Java.Day2.DesignPatterns.Factory;

/*
 * 
 * FACTORY
 *      you don't instantiate objects - the factory does
 * 
 *      you have one general class or interface with a bunch of child objects
 *          rather than your main class depending on ALL the child objects, it only has to depend on the factory
 * 
 * 
 *      COUPLING VS COHESION
 *          cohesion refers to relationships within your classs
 *              low cohesion means that a class performs many different things
 *              high cohesion means that your class is focused on a specific set of tasks
 *          coupling refers to relationships between different classes
 *              low coupling means you're dependent on few classes
 *              high coupling means you're very dependent on many classes
 * 
 * 
 *          In code: we want HIGH COHESION and LOW COUPLING
 * 
 * 
 *      the point of the factory is to DECOUPLE your code - lower your coupling
 */

public class Factory {
    public static void main(String[] args) {
        

        /* 
        THIS IS HIGHLY COUPLED CODE:
        Superhero cap = new CaptainAmerica();
        Superhero aus = new AustinReeves();
        Superhero iron = new IronMan();
        */

        Superhero hero = SuperheroFactory.createSuperhero("Iron Man");
        hero.activateSuperpower();
        hero.sayName();


        /*
         * the point is that the factory handles the creation of objects
         * this class doesn't have to worry about the nuances of each class
         */

    }
}


class SuperheroFactory {
    public static Superhero createSuperhero(String type) {
        if(type.equals("Captain America")) {
            return new CaptainAmerica();
        } 
        else if(type.equals("Austin Reeves")) {
            return new AustinReeves();
        }
        else if(type.equals("Iron Man")) {
            return new IronMan();
        }
        else {
            throw new IllegalArgumentException("Invalid superhero: " + type);
        }
    }
}
