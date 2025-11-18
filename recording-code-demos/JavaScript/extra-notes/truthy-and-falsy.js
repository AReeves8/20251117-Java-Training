/**
 * When casting any data type to a boolean, it needs to be able to be cast
 * 
 * Falsy and truthy are used to determine whether the casted value is true or false
 * Anything that is truthy is simply not falsy
 * 
 * Falsy:
 * - false
 * - 0
 * - 0n
 * - ""
 * - null
 * - undefined
 * - NaN (Not a Number. The typeof NaN is number)
 * - document.all (Trivia knowledge)
 * 
 * Truthy:
 * - "0"
 * - "false"
 * - {}
 * - []
 * - Everything else that isn't on the falsy list
 */


let someVal = [null];

if (someVal) {
    console.log('It is truthy!');
} else {
    console.log('It is falsy!');
}


// == Logical Equals (Equality) Operator

console.log(2 == 2); // true
console.log(2 == '2'); // true

// === Strict Equality Operator
// First performs a comparison of the typeof, then compares values (only if the first part passed)

console.log(2 === 2); // true
console.log(2 === '2'); // false

function myStrictEqualityOperator(left, right) {
    if (typeof left == typeof right) {
        return left == right;
    }
    return false;
}

console.log(myStrictEqualityOperator(2, '2'));

// One situation for using ==
// Comparing null and undefined

console.log(null == undefined); // true
console.log(null === undefined); // false

// Checks that it is both not null AND not undefined
if (someVal != null) {
    // Do something with the value
}

someVal = 0;
// This also prevents null and undefined BUT it also prevents ALL falsy values
if (someVal) {
    // do some logic
    console.log('Hurray');
}