import { Movie } from "../api/movieApi";

type MovieProps = {
    movie: Movie,
    children? : React.ReactNode
}

export default function Movies({movie} : MovieProps) {
    return ( 
        <>
            <h2>{movie.title}</h2>
            <h3>{movie.rating}</h3>
            <h3>{movie?.director?.firstName + ' ' + movie?.director?.lastName}</h3>
            <h3>--------------------------------------------------------------</h3>
        </>
    );
}