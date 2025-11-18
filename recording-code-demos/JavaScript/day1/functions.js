// in JS, functiopns are treated like first-class

// you can pass functions into other functions as a parameter, you can also return a function, from a function


function greeterGenerator(name) {

    /**
     * Closure - functions in JS retain access to variables and values in their outside lexical environment
     */
    return function() {
        console.log('Hello, ' + name);
    }

}

// greetChris is a FUNCTION, not a variable
const greetChris = greeterGenerator('Chris');
greetChris();

// even with multiple functions returned, each hold their own lexical environment
const greetAustinSmith = greeterGenerator('Austin Smith');
greetAustinSmith();

greeterGenerator('Jordan')();




/**
 * 
 * HIGH ORDER FUNCTIONS (HOFs)
 *      any function function that takes a function as a param, OR returns a function
 *      
 * 
 *      arrays have lots of built in HOF
 * 
 */

const fruits = ['apple', 'orange', 'banana', 'mango', 'grapes', 'strawberry', 'blackberry', 'blueberry'];

// for each loops through your array. takes in a function with 3 params: an iteration variable, an index (OPTIONAL), and a copy of your array (OPTIONAL)
fruits.forEach(function (fruitItrVar, index, copyOfFruitsArray) {
    
    fruitItrVar = fruitItrVar + index
    console.log(`Fruit at index -- ${index}: ${fruitItrVar}`);

    copyOfFruitsArray.push(fruitItrVar);        // DOES modify fruits array
});
console.log(fruits);


// map returns a new list with each value changed by the function
const upperCasedFruits = fruits.map(function(fruit) {
    return fruit.toUpperCase();
});
console.log(fruits);            // original is unchanged
console.log(upperCasedFruits);  


// filter returns a new list that meats the criteria specified in the function
const berries = fruits.filter(function(fruit) {
    return fruit.includes('berry');
})
console.log(berries);


const numbers = [10, 20, 30, 40, 50];

// reduce is going to return some accumulated value
const sum = numbers.reduce(function(prevValue, currValue) {
    // prevValue will be updated each iteration to be what was RETURNED by the last iteration
    
    return prevValue + currValue;
});
console.log(sum);


/**
 * CALLBACK FUNCTIONS
 *      functions that are invoked at a later point in time
 * 
 *      two common examples: setTimeout, and setInterval
 */

// setTimeout takes in a callback function and an amount of time - kinda like .sleep() in java
setTimeout( () => {
    // this callback function executes once the time has passed
    console.log('It has been 3 seconds!');

}, 3000 );      // 3000 ms -> 3 s


let count = 10;

// setInterval runs the callback function each time the given time elapses. ned to make sure to use clearInterval to stop it
const countdown = setInterval(() => {
    
    if (count <= 0) {
        console.log('LIFTOFF!!!!');
        clearInterval(countdown);   // not a break, only stops the Interval from continuing after the current iteration
        return;
    }

    console.log('Curret count: ' + count);
    count--;

}, 1000);