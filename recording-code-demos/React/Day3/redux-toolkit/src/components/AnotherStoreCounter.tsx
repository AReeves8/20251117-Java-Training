import { useSelector, useDispatch } from "react-redux";
import { increment, decrement, set} from '../slices/counterSlice'
import { useRef } from "react";

export default function AnotherStoreCounter() {

    /**
     * this does the exact same thing as StoreCounter.tsx
     *      
     *      when state from a store is changed in one component, it is changed everywhere!
     */
   
    const {count} = useSelector((store : any) => store.counter);
    const dispatch = useDispatch();


    // give types to functions using angle brackets (< >)
    const inputRef = useRef<HTMLInputElement>(null);

    return (
        <>
            <h1>Another Store</h1>
            <h2>Count: {count}</h2> 

            <button onClick={() => dispatch(increment())}>+</button>
            <button onClick={() => dispatch(decrement())}>-</button>

            <input type="number" ref={inputRef}/>
            <button onClick={() => dispatch(set(Number(inputRef?.current?.value)))}>Set Count</button>
        </>
    );
}