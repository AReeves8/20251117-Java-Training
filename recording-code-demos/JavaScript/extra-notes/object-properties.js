/**
 * Object.defineProperty allows you to specify new properties on an object AND set some info about it
 * 
 * writable - Prevents the writing/mutation of that specific property 
 * enumerable - Prevents it from showing up when you enumerate over the object (Property does still exist)
 * configurable - Locking the configuration of that property (Once set to false, can't be changed back to true)
 */
const animal = {
    breed: 'Panda',
    name: 'Petey',
}
Object.defineProperty(animal, 'age', {enumerable: true, writable: false, value: 200});
console.log(animal);

// you can also use defineProperty to alter the behavior of an existing property
Object.defineProperty(animal, 'breed', {enumerable: false});