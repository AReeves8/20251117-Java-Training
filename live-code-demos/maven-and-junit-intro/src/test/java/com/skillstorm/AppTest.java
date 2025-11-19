package com.skillstorm;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppTest {


    @BeforeAll
    public void classSetup() {      // needs to be static without @TestInstance on class

        // used to setup something for the entire class. 
        // ex: establishing a database connection

        System.out.println("Runs once before all test methods.");
    }

    @BeforeAll
    public void classSetupTwo() {
        // can have multiple of these annotations. 
        // there is no guarentee of run order
        //  - generally not advised to have multiple
        System.out.println("SETUP TWO: Runs once before all test methods.");
    }

    @BeforeEach
    public void methodSetup() {

        // setup more specifc info for tests
        // initializing a mock dataset for each test

        System.out.println("Runs before each test method.");
    }

    @AfterEach
    public void methodTeardown() {

        // tearing down whatever was setup
        // removing mock dataset

        System.out.println("Runs after each test method.");
    }

    @AfterAll
    public void classTeardown() {       // needs to be static without @TestInstance on class

        // teardown anything that was setup at the class level
        // ex: closing a database connection

        System.out.println("Runs once after all test methods.");
    }


    
    @Test       // define a test method
    //@Disabled   // prevent a test method from being ran
    public void shouldAnswerWithTrue() {
        System.out.println(helperMethod());
        assertTrue( true );
    }



    // can have regular methods in the test class
    private String helperMethod() {
        return "Regular method that can be used as a helper for test methods.";
    }
}
