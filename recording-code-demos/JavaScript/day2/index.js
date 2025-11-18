/**
 * here is the javascript for index.html for the Movies API
 */


const URL = 'http://localhost:8282/movies';
let allMovies = [];

/**
 * document - gives access to html elements
 * 
 * event listeners are how we respond to events in the html
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
    /**
     * DOM - Document Object Model
     *  
     *  DOMContentLoaded is an event that fires when the DOM is loaded
     *      page loads and refreshes
     * 
     */


    /**
     * AJAX - Asynchronous JavaScript and XML
     * 
     *  the primary object of AJAX, is XmlHttpRequest (XHR)
     * 
     */

    let xhr = new XMLHttpRequest();         // creating a new XHR object (this puts it into state 0 - unsent)

    /**
     * onreadystatechange - is a callback that fires everytime the state of the xhr changes
     * 
     *      5 stages (ready states):
     *          0 - unsent
     *          1 - opened
     *          2 - headers received
     *          3 - loading
     *          4 - done        ---> this is the main one we care about
     */
    xhr.onreadystatechange = () => {

        /**
         * strict equality (===) and regular equality (==) in JS
         *      strict equality checks values and data types
         *      regualr equality only checks values
         * 
         *      ex: 4 === '4' is false but 4 == '4' is true
         */
        if(xhr.readyState === 4) {
            // at readyState 4, we have our response from the server 

            // responseText contains the response from the server
            // JSON.parse() parses the JSON into js objects
            let movies = JSON.parse(xhr.responseText);

            // add movies to table
            movies.forEach(newMovie => {
                addMovieToTable(newMovie);
            });

        }

    };

    // use open to set the request method and the url to send the request to (state changed to 1 - opened)
    xhr.open('GET', URL);

    // this sends the request 
    xhr.send();

});


////////////////////////////////////////////
///// POST REQUEST AND ADDING TO TABLE /////
////////////////////////////////////////////

document.getElementById('new-movie-form').addEventListener('submit', (event) => {

    // event object gives info about the event that we are listening for
    event.preventDefault();         //preventDefault() is going to prevent the form from refreshing the page 

    /**
     * one option to grab form data is to call document.getElementById().value for each input field
     * 
     * a beter option is to us FORM DATA
     * 
     * FormData provides all the data from a form in an easy to work with obect
     */

    // FormData takes in the html tags for your form
    let inputData = new FormData(document.getElementById('new-movie-form'));

    let newMovie = {
        // use .get() to retrieve a field from form data and pass in the NAME attribute from the <input> tag
        title : inputData.get('new-movie-title'),         
        rating : inputData.get('new-movie-rating'),
        director : {
            firstName : inputData.get('new-director-first'),
            lastName : inputData.get('new-director-last')
        }
    }


    /**
     * 
     * rather than using raw xhr objects, we can use fetch()
     * 
     * 
     * fetch() is a built-in function that can send http requests
     *      fetch() returns a Promise
     * 
     *      PROMISES
     *          something that will return.. eventually
     *          happen asynchronously from the rest of your program
     * 
     *      ASYNC and AWAIT
     *          use async on function to tell you program that the function returns a promise
     * 
     *          use await to tell our program to wait for some asynchronous operation
     *              they can ONLY be used inside async functions
     */
    doPostRequest(newMovie);

});

async function doPostRequest(newMovie) {

    let returnedData = await fetch(URL + '/movie', {
        method : 'POST',
        headers : {
            'Content-Type' : 'application/json'         // make sure your server is expecting to receive JSON in the body
        },
        body : JSON.stringify(newMovie)      // turns a js object into JSON
    });

    // .json() to deserialize the JSON back into js object - this ALSO returns a promise
    let movieJson = await returnedData.json();

    console.log('MOVIE JSON' + movieJson);

    // just need to add movie to table
    addMovieToTable(movieJson);

    // reset the form
    document.getElementById('new-movie-form').reset();
}

function addMovieToTable(newMovie) {

    // DOM Manipulation - where we manually change the DOM

    // creting all necessary DOM elements
    let tr = document.createElement('tr');      // will create a <tr> tag
    let id = document.createElement('td');      // will create a <td> tag for movie id
    let title = document.createElement('td');      // will create a <td> tag for movie title
    let rating = document.createElement('td');      // will create a <td> tag for movie rating
    let director = document.createElement('td');      // will create a <td> tag for director
    let editBtn = document.createElement('td');      // will create a <td> tag for edit button
    let deleteBtn = document.createElement('td');      // will create a <td> tag for delete button

    id.innerText = newMovie.id;
    title.innerText = newMovie.title;
    rating.innerText = newMovie.rating;
    director.innerText = newMovie.director.firstName + ' ' + newMovie.director.lastName;

    editBtn.innerHTML = 
    `<button class="btn btn-primary" id="edit-button" onclick="activateEditForm(${newMovie.id})">Edit</button>`;

    deleteBtn.innerHTML = 
    `<button class="btn btn-primary" id="delete-button" onclick="activateDeleteForm(${newMovie.id})">Delete</button>`;

    // adds the <td> tags as nested children to the tr> tag
    tr.appendChild(id);
    tr.appendChild(title);
    tr.appendChild(rating);
    tr.appendChild(director);
    tr.appendChild(editBtn);
    tr.appendChild(deleteBtn);

    // setting the idattribute for the <tr>
    tr.setAttribute('id', 'TR' + newMovie.id);

    // adds the <tr> tag to the <tbody> tag
    document.getElementById('movie-table-body').appendChild(tr);

    // adding the new movie to the list of all the movies
    allMovies.push(newMovie);

}


///////////////////////////////////////////
///// CANCEL BUTTONS AND FORM TOGGLES /////
///////////////////////////////////////////

document.getElementById('update-cancel-button').addEventListener('click', (event) => {
    event.preventDefault();
    resetAllForms();
});

document.getElementById('delete-cancel-button').addEventListener('click', (event) => {
    event.preventDefault();
    resetAllForms();
    
});

function resetAllForms() {

    // clears data from all forms
    document.getElementById('new-movie-form').reset();
    document.getElementById('update-movie-form').reset();
    document.getElementById('delete-movie-form').reset();

    // dispalys only the new-movie-form
    document.getElementById('new-movie-form').style.display = 'block';
    document.getElementById('update-movie-form').style.display = 'none';
    document.getElementById('delete-movie-form').style.display = 'none'; 
}

function activateEditForm(movieId) {
    // find the movie and its <tr> that needs to be edited
    for(let m of allMovies) {
        if(m.id === movieId) {
            document.getElementById('update-movie-id').value = m.id;
            document.getElementById('update-movie-title').value = m.title;
            document.getElementById('update-movie-rating').value = m.rating;
            document.getElementById('update-director-id').value = m.director.id;
            document.getElementById('update-director-first').value = m.director.firstName;
            document.getElementById('update-director-last').value = m.director.lastName;
        }
    }

    // showing only the edit form
    document.getElementById('new-movie-form').style.display = 'none';
    document.getElementById('update-movie-form').style.display = 'block';   // block is the default for showing a tag
    document.getElementById('delete-movie-form').style.display = 'none';

}

function activateDeleteForm(movieId) {
    // find the movie and its <tr> that needs to be edited
    for(let m of allMovies) {
        if(m.id === movieId) {
            document.getElementById('delete-movie-id').value = m.id;
            document.getElementById('delete-movie-title').value = m.title;
            document.getElementById('delete-movie-rating').value = m.rating;
            document.getElementById('delete-director-id').value = m.director.id;
            document.getElementById('delete-director-first').value = m.director.firstName;
            document.getElementById('delete-director-last').value = m.director.lastName;
        }
    }

    // showing only the edit form
    document.getElementById('new-movie-form').style.display = 'none';
    document.getElementById('update-movie-form').style.display = 'none';
    document.getElementById('delete-movie-form').style.display = 'block';   // block is the default for showing a tag
    
}


/////////////////////////////////////////////////
///// UPDATE REQUEST AND CHANGING THE TABLE /////
/////////////////////////////////////////////////

document.getElementById('update-movie-form').addEventListener('submit', (event) => {
    event.preventDefault();		// prevent default form actions from occuring

    // retrieving data from the update form
    let inputData = new FormData(document.getElementById('update-movie-form'));

    // MAKE SURE TO INCLUDE IDS WHEN DOING A PUT REQUEST
    let movie = {
        id : document.getElementById('update-movie-id').value,    // FormData cannot access values from disabled fields
        title : inputData.get('update-movie-title'),         
        rating : inputData.get('update-movie-rating'),
        director : {
            id : document.getElementById('update-director-id').value,
            firstName : inputData.get('update-director-first'),
            lastName : inputData.get('update-director-last')
        }
    }

    /**
     * alternative/preferred way to handle promises:
     *      rather than using async/await, we can use .then() and pass in a callback function
     * 
     *      .then will run whenever the promise returns succesfully
     *      .catch will run whenever the promise returns unsuccessfully
     */
    fetch(URL + '/movie', {
        method : 'PUT',
        headers: {
            "Content-Type": "application/json",
        },
        body : JSON.stringify(movie)
    })
    .then((data) => {
        // this will handle all 100, 200, and 300 status code responses
        
        // we still need to serialize the response into JSON
        return data.json();
    })
    .then((movieJson) => {          // handling the promise returned by data.json (*** this is where we update the table ***)
        
        // adding the updated movie to our table
        updateMovieInTable(movieJson);

        // reset the forms
        document.getElementById('update-movie-form').reset();
        document.getElementById('new-movie-form').style.display = 'block';
        document.getElementById('update-movie-form').style.display = 'none';
    })
    .catch((error) => {
        // this will handle all 400 and 500 status code responses

        console.error(error);   // generally, you never want to use console.log() - especially in a production environment
    })
});

function updateMovieInTable(movie) {
    document.getElementById('TR' + movie.id).innerHTML = `
    <td>${movie.id}</td>
    <td>${movie.title}</td>
    <td>${movie.rating}</td>
    <td>${movie.director.firstName + ' ' + movie.director.lastName}</td>
    <td><button class="btn btn-primary" id=editButton" onclick="activateEditForm(${movie.id})">Edit</button></td>
    <td><button class="btn btn-primary" id=deleteButton" onclick="activateDeleteForm(${movie.id})">Delete</button></td>
    `;
}


//////////////////////////////////////////////////
///// DELETE REQUEST AND REMOVING FROM TABLE /////
//////////////////////////////////////////////////

document.getElementById('delete-movie-form').addEventListener('submit', (event) => {
    event.preventDefault();		// prevent default form actions from occuring


    // get the data from the form since all the fields are disabled and FormData won't capture them
    let movieId = document.getElementById('delete-movie-id').value;
    let titleOnForm = document.getElementById('delete-movie-title').value;		
    let ratingOnForm = document.getElementById('delete-movie-rating').value;	
    let directorId = document.getElementById('delete-director-id').value;
    let directorFirstOnForm = document.getElementById('delete-director-first').value;
    let directorLastOnForm = document.getElementById('delete-director-last').value;

    // creating the movie object that needs to be deleted
    let movie = {
        id : movieId,
        title : titleOnForm,
        rating : ratingOnForm,
        director : {
            id : directorId,
            firstName : directorFirstOnForm,
            lastName : directorLastOnForm
        }
    };

    // sending delete request
    fetch(URL + '/movie', {
        method : 'DELETE',
        headers: {
            "Content-Type": "application/json",
        },
        body : JSON.stringify(movie)
    })
    .then((data) => {

        // delete request returns no-content so there's no need to deserialize the response and wait for that promie
        // just need to check that the response we got back is 204 - No Content and we can delete it on the front end
        if(data.status === 204) {
            // remove movie from table
            removeMovieFromTable(movie);

            // resetting all forms
            resetAllForms();
        }
    })
    .catch((error) => {
        console.error(error);   
    })

});

function removeMovieFromTable(movie) {

    // removing the <tr> from the table when a movie gets deleted
    const element = document.getElementById('TR' + movie.id);
    element.remove();
}