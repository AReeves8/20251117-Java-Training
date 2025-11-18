package com.skillstorm.spingmvc.mappers;

import org.springframework.stereotype.Component;

import com.skillstorm.spingmvc.dtos.UserDto;
import com.skillstorm.spingmvc.models.User;

/*
 * converts UserDto to User and vice versa
 */
@Component
public class UserMapper {
    

    public User toUser(UserDto dto) {
        return new User(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getEmail());
    }

    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }
}
