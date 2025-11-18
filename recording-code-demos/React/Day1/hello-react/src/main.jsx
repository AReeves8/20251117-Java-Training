import React from 'react'
import ReactDOM from 'react-dom/client'
import App, { num, increment } from './App.jsx'
import './index.css'
//import { num } from './App.jsx'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>

    {/** StrictMode is it mounts and immediately unmounts your components
     *    useful for spotting memory leaks 
     *      if somethinf persists after unmount - then memory leak
     * 
     *    only used in dev server - NOT IN PRODUCTION BUILD
     */}

    {/** everything returned by App.jsx, display here */}
    <App />
  </React.StrictMode>,
)
