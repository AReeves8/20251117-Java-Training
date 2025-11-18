/**
 * ES stands for EcmaScript
 *      ES6 was added to JS in 2015
 * 
 *      added a bunch of new stuff to JS
 */

// arrow functions
[1,2,3].map( (data, data2) => { 'Hello' });     // takes in 2 params, returns 'Hello'

/**
 *  if you only have one paramter, () are optional
 *  but for 0, or 2+ params, you need ()
 * 
 *  if you only have oneline in your function, {} are optional
 *  but for 0, or 2+ lines, you need {}
 * 
 * 
 *  Valid arrow functions:
 *      a => a*2        
 *      a => a
 *      (a, b) => a*b 
 *      () => 'hello'
 *      () => {}        // doesn't do anything but is valid
 */


// normal function declaration
function myFunction() {

}

// this is also a common way to declare functions
const myFunc = () => {

}


/**
 * JAVA SCRIPT OBJECTS
 *      basically like maps with key-value pairs
 *      also very similar to python dictionaries
 *  
 */

// object literal syntax
const person = {
    age : 23,
    name : {
        firstName : 'Austin',
        lastName : 'Reeves'
    },
    favColor : 'gray'
}

console.log(person.age);

// const is on the OBJECT, not its PROPERTIES. so they can still be modified
person.age = 25;
console.log(person.age);

// can add propertiesto objects after they are created
person.favDayOfTheWeek = 'Friday!'; // PAY DAY WOOOOO
console.log(person);

// this will make your object read-only
Object.freeze(person);

person.location = 'Dallas';     // won't be added - but DOESN'T throw an error
console.log(person);


/**
 * Objects are NOT iterable. They are ENUMERABLE
 * 
 *      enumerables use for..in loops
 *      iterables use for..of loops     (arrays)
 */

for (let property in person) {
    console.log(`Key: ${property}. Value: ${person[property]}`);     
}



/**
 * DESTRUCTURING
 *      object destructuring and array destructuring
 * 
 *      basically breaking down the properties of something into its own variables
 *      
 */

const animal = {
    species : 'dog',
    name : 'Ruby'
}

// taking the properties of animal and converting them to their own variables
const {species, name} = animal;

//species = 'cat';        // in general you want to avoid
console.log(species);


const nums = [1,2,3,4,5];

// taking the elements inside num and putting them to their own variables
const [num1, num2, num3] = nums; 

console.log(num2);

// skipping elements in arrays
const [,,,num4] = nums;
console.log(num4);      // will print 4



/**
 * Nullish Coalescing Operator
 *      kinda similar to && or ||
 * 
 *      ?? - check for null. if so, return a different value
 */

let portValue = null;

// if portValue has a vlaue, assign it. If its null, use 8080 instead
let port = portValue ?? 8080;

console.log(port);


/**
 * 
 * OPTIONAL CHAINING
 *      ?. - null check, but its used for accessing properties in an object
 *  
 *      if what is in frot of the question amr is null, ignore everythnig behind it
 */

animal.home = {
    street : '123 Dallas Dr',
    city : 'Dallas',
    state : 'Texas'
}

// standard null check
if(animal.home != null) {
    console.log(animal.home);
}

// using optional chaining
console.log(animal?.home?.city);

animal.home = null;
console.log(animal?.home?.city);
