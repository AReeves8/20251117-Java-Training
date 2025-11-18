import { configureStore } from '@reduxjs/toolkit';
import { movieApi } from './api/movieApi';


const store = configureStore({
    reducer : {

        // since createApii created the reducer for us using the specified reducerPath, we can use the reducerPath as the name of our reducer
        [movieApi.reducerPath] : movieApi.reducer

    },

    /**
     * middleware is a point in between your api call and your api endpoint
     *      kinda similar to AOP in spring
     *          middleware is often used for things like logging, crash reports, routing, etc.
     * 
     * 
     *      *we* aren't doing much with it, but redux does
     */
    middleware : (defaultMiddleWare) => defaultMiddleWare().concat(movieApi.middleware)
});

export default store;