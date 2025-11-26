package com.skillstorm.spring_mvc.dtos;

import com.skillstorm.spring_mvc.models.User;

/**
 * Design Pattern: Data Transfer Objects (DTOs)
 *      - to be a version of a model that can be transferred to different services. 
 *  
 * 
 *      common to have multiple DTOs for a model. 
 *          you might want a UserCreationDto that has no id but DOES include a password
 */
public record UserDto(
    long id, 
    String firstName, 
    String lastName, 
    String email
) {

    // User => UserDto
    public static UserDto convertToDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

    // UserDto => User
    public static User convertToUser(UserDto userDto) {
        return new User(userDto.id(), userDto.firstName, userDto.lastName(), userDto.email(), null);
    }

}
