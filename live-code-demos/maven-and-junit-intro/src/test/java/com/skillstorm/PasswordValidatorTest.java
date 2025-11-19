package com.skillstorm;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordValidatorTest {

    // can define instance variables in class as needed
    //PasswordValidator validator = new PasswordValidator();

    /**
     * TEST DRIVEN DEVELOPMENT (TDD)
     *      - write unit tests FIRST, then write code
     * 
     * 
     *  PASSWORD VALIDATOR
     *      - contains a special chartacter ($, @, !)
     *      - contains a number
     *      - minimum 8 characters
     *      - no spaces
     *      - contains a capital letter
     *      - test empty string
     */


    @Test
    @DisplayName("Has Special Character")       // shows this name on printout of tests
    public void testPasswordContainsSpecialCharacter() {

        assertTrue(PasswordValidator.hasSpecialCharacter("abc123$"));
        assertTrue(PasswordValidator.hasSpecialCharacter("abc123@"));
        assertTrue(PasswordValidator.hasSpecialCharacter("abc123!"));
        assertFalse(PasswordValidator.hasSpecialCharacter("abc123"));
    }

    @Test
    @DisplayName("Has Number")
    public void testPasswordContainsNumber(){
        assertTrue(PasswordValidator.hasNumber("abc123"));
        assertFalse(PasswordValidator.hasNumber("abcxyz"));
    }

    @Test
    @DisplayName("Has At Least 8 Characters")
    public void testPasswordMinimumEightCharacters(){
        assertTrue(PasswordValidator.hasMinimumLength("abc123xyz"));
        assertFalse(PasswordValidator.hasMinimumLength("abcxyz"));
    }

    @Test
    @DisplayName("Has A Capital Letter")
    public void testPasswordContainsCapitalLetter(){
        assertTrue(PasswordValidator.hasCapitalLetter("abc123XYZ"));
        assertFalse(PasswordValidator.hasCapitalLetter("abcxyz"));
    }

    @Test
    @DisplayName("No Spaces")
    public void testPasswordHasNoSpaces(){
        assertTrue(PasswordValidator.hasNoSpaces("abc123XYZ"));
        assertFalse(PasswordValidator.hasNoSpaces("abc xyz"));
    }

    @Test
    @DisplayName("Is Not Empty String")
    public void testPasswordNotEmptyString(){
        assertThrows(IllegalArgumentException.class, () -> {
            PasswordValidator.validate("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            PasswordValidator.validate(null);
        });
    }

    @Test
    @DisplayName("Complete Password")
    public void testPasswordComplete(){
        assertTrue(PasswordValidator.validate("Abc123$@!"));
    }

}
