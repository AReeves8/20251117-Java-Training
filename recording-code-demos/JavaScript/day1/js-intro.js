// JavaScript is relate to Java the same way carpets are related to cars. Or sunglasses related to the sun

// Brendan Eich
// Mocha -> JavaScript


/*
    to run js files - cd into proper director and use command: node <file_name>
*/

console.log('Hello World!!');       // double or single quotes for strings and semi-colons optional


/**
 *  VARIABLES
 *      var, let, and const
 *          var - global or function scoped (huge generalization: never use it)
 *          let - block scoped
 *          const  block scoped, but the value cannot be changed (like the final keyword in java)
 */


// GLOBAL SCOPED - to the entire file
var x = 10;
console.log(x);

var x = 20;             // vars can be redeclared entirely
console.log(x);


function myFunction() {
    
    // FUNCTION SCOPED

    console.log(x);  // undefined due to variable hoisting
    var x = 50;     
      
    /**
     * VARIABLE HOISTING
     *      JS will move your variable declarations to the top of their scope
     *          only the declaration, and not the definition. 
     */


    if(true) {

        var myVar = 'var Austin';   // SCOPED TO THE FUNCTION myFunction()

        let myLet = 'let Austin';   // SCOPED TO THE BLOCK if-statement
        console.log(myLet);     
    }

    //console.log(myLet);       // will give error
    console.log(myVar);
}

myFunction();

const y = 12;
//y = 30;       // errors because const cannot be reassigned


/**
 * 
 * JavaScript still has data typers, we just don't declare them
 * 
 *  Primitives:
 *      number, string, boolean, undefined, null, BigInt (huge numbers, you declare with an n. ex: 1n), Symbol (more of a JS identifier)
 * 
 *  Non-primitives:
 *      object, arrays, functions, etc.
 * 
 */

console.log(typeof y);

let bigInts = 12n;
console.log(bigInts);
console.log(typeof bigInts);

// JS has built in functions for casting to a specific data type
let z = String(12);
console.log(typeof z);

let num = 12;
let myBool = Boolean(num);
console.log(myBool);

num = !!num;            // !!12 -> !false -> true
console.log(num);


/**
 * TYPE COERCION
 *      basically when JS tries to manually convert your data types
 */
let num1 = 5;
let num2 = "10";

// JS has to decide between making num1 and num2 both strings or both numbers
let sum1 = num1 + num2;          // string wins out and num1 is coerced into being a string
console.log('Sum1 = ' + sum1);


let sum2 = num1 + Number(num2);  
console.log('Sum2 = ' + sum2);


/**
 * TEPLATE LITERALS (aka Template Strings)
 *      - strings that are created with ticks(``)   (i call them backticks)
 *      - they print exactly as they appear in code     
 */

let myName = 'Austin';

// can reference variables in template literals with string interpolation: ${ var_name }
let newStr = 
`Here is a list of ${myName}'s favorite foods:
    1. Frenchh Fries
    2. Rice
    3. Waffles`;
console.log(newStr);