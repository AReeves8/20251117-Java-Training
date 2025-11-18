import { createSlice } from '@reduxjs/toolkit';


/**
 * SLICES
 *      contain the reducers and their actions 
 *          in pure redux (ie. no toolkit) actions and reducers are in separate files
 * 
 * 
 *      where you modify logic for manipulating the state
 * 
 * 
 *      createSlice takes in an object with 3 properties:
 *          - name: the name of your reducer
 *          - initialState: your initial value of your state
 *          - reducers - the actions your reducer can perform
 * 
 * 
 *      createSlice returns an object with a copuple of properties: actions and reducer
 */

const counterSlice = createSlice({
    name : 'counter',
    initialState : {
        count : 0
    },
    reducers : {
        increment : (state) => {
            // normally in react you NEVER want to mutate the state directly
            // in redux, you actually have to mutate it directly
            state.count++;
        },
        decrement : (state) => {
            state.count--;
        },
        set : (state, action) => {
            state.count = action.payload;
        }
    }
});

// exporting the reducer created by createSlice
export default counterSlice.reducer;


// exporting the actions so they can be used elsewhere
export const { increment, decrement, set } = counterSlice.actions;