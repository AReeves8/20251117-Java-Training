/*
    ASYNC AND AWAIT
    
        alternative way to use and handle promises

        async functions automatically return a Promise
            whatever data is returned by the function is the "resolved" state
            if the function throws an exception, that wil be the "rejected" state

        await will act as our .then() function
            await will pause the execution of the code block around it, making it asynchronous from the rest of the program
                THUS it MUST be inside of an async function itself
                
                    

*/

async function myAsyncFunc() {
    return 'This is an async function';
}

const awaitFunc = async () => {

    // await WILL fail if the Promise is rejected - need try-catch block
    const msg = await myAsyncFunc(); 
    console.log(msg + ' inside awaitFunc');
}

awaitFunc();



////////////////////////////////////////////////////////
// Now, a more complicated example - Grocery shopping //
////////////////////////////////////////////////////////


// these are the items the grocery store has
const inStockItems = ['Apple', 'Banana', 'Milk', 'Eggs', 'Bread', 'Ice Cream'];

// time it takes to travel the grocery store
const travel = async location => {
    await new Promise(resolve => setTimeout(() => {
        console.log(`Traveling to ${location}`);
        resolve();
    }, 5000));
} 

// time it takes to finf the items in the store
const findItems = async itemsOnList => {
    await new Promise((resolve, reject) => setTimeout(() => {
        
        // looping through the given grocery list
        for (let item of itemsOnList) {
            
            /*
                find loops through an array and checks some condition on each element
                returns the first element where the condition is true or undefined if all are false
            */
            if (inStockItems.find(inStockItem => inStockItem === item) == undefined) {
                
                // this means the item on the grocery list could not be found
                return reject(`${item} could not be found`);
            }
        }
        console.log(`Purchasing ${items}`);
        resolve();
    }, 3000));
}

// time it takes to checkout at the store
const checkout = async () => {
    await new Promise(resolve => setTimeout(() => {
        console.log('Checking out');
        resolve();
    }, 2000));
}


const buyItemsFromStore = async (startLocation, endLocation, items) => {
    /**
     * 1. Drive to the store
     * 2. Find items
     * 3. Purchase
     * 4. Head home
     */
    try {
        await travel(endLocation);      // Must finish getting to the store, before buying items
        await findItems(items);         
        await checkout();
        await travel(startLocation);
        console.log('Shopping success!');
    } catch (err) {
        console.error(err);
        console.log('Shopping failure :(');
    } finally {
        console.log('Shopping journey over');
    }
}

buyItemsFromStore('Home', 'Publix', ['Apple', 'Milk', 'Honey']);