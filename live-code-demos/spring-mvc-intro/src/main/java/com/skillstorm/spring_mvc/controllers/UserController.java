package com.skillstorm.spring_mvc.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.spring_mvc.dtos.UserDto;
import com.skillstorm.spring_mvc.models.User;
import com.skillstorm.spring_mvc.services.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




/**
 * STEREOTYPE ANNOTATIONS
 *      more specific versions of @Component
 *          @Controller, @Service, @Repository
 */

/**
 * @RestController vs @Controller
 *      RestController is a more specific version on Controller
 *          adds @ResponseBody to EVERY mapping method in the class
 *              uses Jackson Object Mapper to auto-convert Java objects to JSON
 */
@RestController
@RequestMapping("/users")       // handle ALL requests to /users (get, post, put, delete)
public class UserController {


    //@Autowired             old style

    // constructor injection is the new hip way
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


    // handles all GET requests to /users/hello-world
    @RequestMapping("/hello-world")     // at the method level, request mapping defaults to GET
    public String helloWorld() {
        return "Hello World!! :)";
    }


    /**
     * GET - retrieve
     * POST - create
     * PUT - update
     * DELETE - delete
     */


    /**
     * handle any GET /users
     * 
     * the same as doing @RequestMapping(method = RequestMethod.GET) on another method
     */
    @GetMapping
    public List<UserDto> findAllUsers() {
        return service.findAllUsers();
    }

    /**
     * GET /users/id/{id}
     * 
     *      Path Variable - allow us to grab some dynamic piece of data from our URI
     */
    @GetMapping("/id/{id}")
    public UserDto findById(@PathVariable("id") long userId) {
        return service.findById(userId);
    }

    /**
     * GET /users/name?first-name=X,last-name=Y
     * 
     *      Request Parameter - used for filters primarily
     *          can choose to make them required or not
     *          default is to be required
     */
    @GetMapping("/name")
    public List<UserDto> findByFirstName(
        @RequestParam(name = "first-name", required = false) String firstName,
        @RequestParam(name = "last-name", required = false) String lastName
    ) {
        return service.findByFirstName(firstName, lastName);
    }
    
    /**
     * POST /users
     *  
     * @RequestBody will pull data out of the body of the HTTP request
     *      - Jackson Object Mapper will convert JSON -> Java Objects
     * 
     * ResponseEntity
     *      - gives you more control over your HTTP responses
     *      - modify headers, status codes, cookies, etc.
     * 
     */
    @PostMapping
    public ResponseEntity<UserDto> createNewUser(@RequestBody User newUser) {
                
        try {
            // returns UserDto object with a 201 status code
            UserDto user = service.createUser(newUser);
            return new ResponseEntity<UserDto>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();     // builder design pattern
        }
    }

    // PUT /users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") long userId, @RequestBody User user) {

        // user object contains NEW information to update the existing user record with
        UserDto updatedUser = service.updateUser(user);
        //return new ResponseEntity<UserDto>(HttpStatus.NO_CONTENT);        // return 204 if you don't want to return the updated object
        return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);     // return 200 if you want to return the updated object
    }

    // DELETE /users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") long userId) {

        service.deleteUser(userId);
        return new ResponseEntity<UserDto>(HttpStatus.NO_CONTENT);        // return 204 if you don't want to return the updated object
    }
}
