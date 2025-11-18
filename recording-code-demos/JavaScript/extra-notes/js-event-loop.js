/*
    JS Event Loop
        this is the runtime model JS uses
            compared to Java which has a top-down model 
        
            the event loop determines which subtasks to execute when 
                runs more like a queue or a series of queues

                things like setTimeout and setInterval are macrotasks
                while promises are microtasks
                    microtasks get priority over macrotasks
*/

console.log('1');                                       // add to execution stack
setTimeout(() => console.log('2'), 0);                  // add to macrotask queue
setTimeout(() => console.log('3'), 0);                  // add to macrotask queue
Promise.resolve('4').then(data => console.log(data));   // add to microtask queue
console.log('5');                                       // add to execution stack

// What order will the above execute in?
// answer: 1, 5, 4, 2, 3
// in general: execution stack > microtask queue > macrotask queue