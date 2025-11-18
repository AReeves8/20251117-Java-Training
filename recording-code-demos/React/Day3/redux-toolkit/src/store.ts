import { configureStore } from "@reduxjs/toolkit";
import counterReducer from './slices/counterSlice';


/**
 * redux uses the store for handling state across your ENTIRE application
 * 
 * 
 *      this store is basically going to be all of your reducers for your entire application
 */
const store = configureStore({
    reducer : {
        counter : counterReducer        // using the reducer from counterSlice
    }
});


export default store;
