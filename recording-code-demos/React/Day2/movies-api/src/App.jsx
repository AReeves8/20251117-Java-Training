import '@trussworks/react-uswds/lib/index.css';
import Movies from './pages/Movies';
import { Grid, Header, PrimaryNav, Title } from '@trussworks/react-uswds';
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom';
import Director from './pages/Director';
import Home from './pages/Home';


export default function App() {


    const navItems = [

        // creating a list of react-router-dom Links to pass to our navbar
        <Link to='/'>Home</Link>,
        <Link to='/movies'>All Movies</Link>,
        <Link to='/directors'>All Directors</Link>
    ];


    return(
        <>

            <Header basic={true} >

                <Title className='text-center'>Movies API</Title>

            </Header>

             {/**
              * REACT ROUTER DOM
              *     client-side routing - means no page refeshes!
              * 
              *     BrowserRouter is ging to work with the History API 
              *         gives it access to url and history of your browser so it can navigate between pages
              * 
              *     Links and Routes to handle the navigation
              *         need to be nested inside of your browser router
              * 
              */}
            <BrowserRouter basename='/'>
            
                <Grid row>
                    <PrimaryNav items={navItems}></PrimaryNav>
                </Grid>
                <Routes>
                    <Route path='/' element={<Home />}/>
                    <Route path='/movies' element={<Movies />}/>
                    <Route path='/directors' element={<Director />}/>
                </Routes>
            
            </BrowserRouter>

            
        </>
    );
}