import { useEffect, useState } from "react";



export default function Counter() {


    // react doesn't see this as STATE
    let count = 0;


    /**
     * REACT HOOK - useState()
     *      creates some piece of state
     *      
     *      it will take in a default value
     *      it will return an array
     *          this array always has 2 indexes
     *              index 0 contains your variable
     *              index 1 contains a setter function for the variable
     *          usualy will destructure this array into its two returned pieces
     */
    const [counter, setCounter] = useState(0);      // make sure to use proper setter function syntax

    function increment() {
        
        count++;    // does change the value of count, but does not reflect changes on view

        //setCounter(counter + 1);

        // if you need access to the old state for any reason, you can pass in an arrow function to the setter
        setCounter((oldState) => {
            return oldState + 1;
        })

        // you NEVER want to mutate the state directly
        //counter++;      // this won't work
    }



    /**
     * REACT HOOK - useEffct()
     *      taps into the COMPONENTS lifecycle
     *          - Componnet Initialization (on mount) - componet is rendered to DOM
     *          - State Update (did update) - anytime state updates
     *          - Component Destruction (will unmount)  - component is unrendered from DOM
     * 
     * 
     *      where in the lifecycle you tap into, depends on how you use useEffect()
     *          has 2 params:
     *              - callback function (this is what will be invoked when useEffect is ran)
     *              - an optional array (dependency list - the pieces of state we care about)
     *                  - no array - care about ALL state updates 
     *                  - empty array - care about NO state updates
     *                  - array with elements - care about state updates to THESE elements only
     * 
     *      useEffect always runs on mount
     *      useEffect runs on state updates based on the dependency list
     *      useEffect runs on unmount if you return a fucntion
     *          this returned function is invoked during component detruction
     */

    // not commonly used
    useEffect(() => {
        console.log('Runs on mount and ALL state updates');
    });

    // commonly used for fetching data from a server
    useEffect(() => {
        console.log('Runs on mount ONLY');
    }, []);

    // commonly used with forms and data validation
    useEffect(() => {
        console.log('Runs on mount and ONLY when counter is updated');
    }, [counter])

    // commonly used to teardown anything that was setup when nmounting the component
    useEffect(() => {
        console.log('Runs on mount');

        const intervalKey = setInterval(() => {
            console.log(counter);
        }, 1000);



        return () => {
            console.log('Runs when unmounted');
            clearInterval(intervalKey);
        }
    }, [])


    /**
     * UNMOUNTING COMPONENTS
     * 
     *      - refresh the page
     *      - close the app/shut down server
     * 
     *      - In the parent, un-render the component
     * 
     *          the component cannot unrender itself, but its parent can
     *              wherever the component is called from, that is the parent (App.jsx in this case)
     */



    /**
     * in jsx, we return our html
     *      usually we wrap this html in a fragment(<></>)
     *      
     *      your function can only return one parent html element, so we make the fragmet the parent
     * 
     *      fragments will never be rendered on the DOM
     */
    return (
        <>

            {/** 
             * curly braces are used for expressions 
             *      expressions contain js code
             */}
            {/* this is a jsx comment */}
            <h1>Count:</h1>
            <h2>{counter}</h2>  
            <button onClick={increment}>+</button>
        </>
    );
}