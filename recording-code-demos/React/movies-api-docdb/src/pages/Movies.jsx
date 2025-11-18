import { Button, Grid, GridContainer, Modal, ModalHeading, ModalToggleButton } from '@trussworks/react-uswds';
import MoviesTable from '../components/Movies/MoviesTable';
import { useEffect, useRef, useState } from 'react';
import MoviesForm from '../components/Movies/MoviesForm';

export default function Movies() {

    const url = 'http://54.242.130.91:8080/movies';

    // state for our list of movies
    const [movies, setMovies] = useState([]);

    // creating our modal reference that trussworks needs
    const modalRef = useRef(null);


    // make this et request when the componennt is mounted to dom
    useEffect(() => {
        // fetch will default to a GET request
        fetch(url)
            .then(data => data.json())
            .then(returnedData => {
                setMovies(returnedData);
            })
            .catch(error => console.error(error));
    }, []);     // need to add empty dependency list so it runs on mount only



    function handleNewMovie(newMovie) {
        setMovies((oldState) => {
            return [...oldState, newMovie];
        })


        /**
         * THIS IS BAD
         *      this is manual mutation of state
         *          you should NEVER manualy mutate your state
         */
        // let moviesList = movies;
        // moviesList.push(newMovie);
        // setMovies(moviesList);
    }
    

    return(
        <>
            <GridContainer>
                <Grid row>
                    <Grid col={10}>
                        <h1 className='text-centered'>All Movies</h1>
                    </Grid>
                    <Grid col={2}>
                        <ModalToggleButton modalRef={modalRef} opener>New Movie</ModalToggleButton>
                    </Grid>

                </Grid>
                <Grid row>
                    <Grid col>
                        <MoviesTable tableData={movies}/>
                    </Grid>
                </Grid>
            </GridContainer>

            <Modal id='movie-form-modal' ref={modalRef}>
                <ModalHeading id='movie-form-modal-heading'>Enter New Movie Details</ModalHeading>

                <MoviesForm handleNewMovie={handleNewMovie}></MoviesForm>

            </Modal>

            
        </>
    );
}