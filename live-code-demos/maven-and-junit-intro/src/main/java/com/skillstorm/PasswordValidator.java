package com.skillstorm;

public class PasswordValidator {

    public static boolean validate(String input) {

        if(input == null || input.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return hasSpecialCharacter(input) 
            && hasNumber(input)
            && hasCapitalLetter(input)
            && hasMinimumLength(input)
            && hasNoSpaces(input);
    }

    public static boolean hasSpecialCharacter(String input) {
        return (input.contains("$") || input.contains("@") || input.contains("!"));
    }

    public static boolean hasNumber(String input) {
        return input.chars().anyMatch(Character::isDigit);
    }

    public static boolean hasMinimumLength(String input) {
        return (input.length() >= 8);
    }

    public static boolean hasCapitalLetter(String input) {
        return input.chars().anyMatch(Character::isUpperCase);
    }

    public static boolean hasNoSpaces(String input) {
        return !input.contains(" ");
    }
}
