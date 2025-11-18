import { useState } from "react";
import Counter from "./components/ItemCounter/Counter";
import Item from "./components/ItemCounter/Item";
import RefCounter from "./components/RefCounter/RefCounter";

// components are just functions
export default function App() {

    const [shouldRender, setShouldRender] = useState(true);

    return (
        <>

            <button onClick={() => setShouldRender(!shouldRender)}>Toggle Counter</button>

            {/** calling the counter component and displaying it here 
             * 
             *  these two counters have independent state
            */}
            {shouldRender ? <Counter /> : null  /** if shouldRender is true, show the component. else, show null */}
            {shouldRender && <Counter /> }


            <Item name='Puzzle' desc='1000 pieces'>     {/** name and desc can be accessed in Item by calling props */}
                <p>Child!</p> 
                {shouldRender && <RefCounter /> }
            </Item>
        </>
    );
}


/**
 * Exporting is how we make the component available to the rest of the app
 * 
 *      default exports
 *          use the default keyword "export default ..."
 * 
 *          only have one per file
 * 
 *          
 * 
 *      named exports
 *          simply use export keyword only "export ..."
 * 
 *          can have as many as you want
 * 
 *          named exports need to be destructured when importing in other files
 *              ex: in main.jsx you'd have import { num } from './App.jsx'
 */

// default export at the bottom is also ok
//export default App;


// doing a named export
export const num = 10;


// the function will hnadle acces to the data it needs. other components won't need to import count to use increment
let count = 0;
export const increment = () => {
    count++;
} 



