import { useReducer, useRef } from "react";



/**
 * REACT HOOK - useReducer()
 *      similar to useState because it DOES return actual state
 *          preferred when you have more complicated state to manage 
 * 
 *      return some state and a dispatcher function
 * 
 *      this dipatcher function is going to take in some action
 *          these actions are predefined (by you) ways to manipulate the state
 * 
 *      define these actions in a reducer function 
 * 
 * 
 *      when you call useReducer, you ned to pass in your reducer function as well as your initial value for the state
 * 
 */
export default function UseReducerCounter() {

    /**
     * Reducer function
     *      take in 2 params: the state, and the action to perform
     *          action is usually an object with at least 2 properties:
     *              - type - is the action to perform
     *              - payload (optional) - is any data that action needs to update the state
     *  
     *      returns our updated state
     */
    const counterReducer = (state : any, action : any) => {         // ts types are required on function params. any means that it can be anything

        switch(action.type) {
            
            // defining the actions for our reducer
            case 'INCREMENT' :
                return {  ...state, count : state.count + 1 };      // copying the current state and then overriding the count property to the new value
            case 'DECREMENT' :
                return {  ...state, count : state.count - 1 };
            case 'SET' :
                return {  ...state, count : action.payload };       // setting the sate to be whatever is in the payload
            default :
                return state;                                       // returning the current state if we get a undefined action
        }

    }

    // creting the state and the dispatcher function
    // the dispatcher function is going to call the reducer function for us
    const [state, dispatch] = useReducer(counterReducer, { count : 0});

    
    // give types to functions using angle brackets (< >)
    const inputRef = useRef<HTMLInputElement>(null);


    return (
        <>
            <h1>useReducer</h1>
            <h2>Count: {state.count}</h2>

            {/**
             * dispatcher function will take in your action object (with type and payload) and call the reducer for you
             */}

            <button onClick={() => dispatch({type: 'INCREMENT'})}>+</button>
            <button onClick={() => dispatch({type: 'DECREMENT'})}>-</button>

            <input type="number" ref={inputRef}/>
            <button onClick={() => dispatch({type: 'SET', payload : Number(inputRef?.current?.value)})}>Set Count</button>
        </>
    );

}