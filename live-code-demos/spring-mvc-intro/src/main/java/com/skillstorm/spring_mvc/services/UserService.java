package com.skillstorm.spring_mvc.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.skillstorm.spring_mvc.dtos.UserDto;
import com.skillstorm.spring_mvc.models.User;

@Service        // stereotype annotation for service classes
public class UserService {


    public List<UserDto> findAllUsers() {

        // simulating getting data from a db
        List<User> users = new LinkedList<>();
        users.add(new User(1, "Austin", "Reeves", "areeves@skillstorm.com", "password123"));
        users.add(new User(2, "Marcus", "Laguerre", "MLaguerre@skillstorm.com", "password123"));
        users.add(new User(3, "Daniel", "Rollins", "DRollins@skillstorm.com", "password123"));

        // converting data from "db" to DTO
        List<UserDto> userDtos = new LinkedList<>();
        for(User user : users) {
            userDtos.add(UserDto.convertToDto(user));
        }
        return userDtos;
    }

    public UserDto findById(long id) {
        return UserDto.convertToDto(new User(id, "Austin", "Reeves", "areeves@skillstorm.com", "password123"));
    }

    public List<UserDto> findByFirstName(String firstName, String lastName) {
        List<User> users = new LinkedList<>();

        // simulating getting data from a db
        users.add(new User(1, firstName, lastName, "areeves@skillstorm.com", "password123"));
        users.add(new User(2, firstName, lastName, "MLaguerre@skillstorm.com", "password123"));

        List<UserDto> userDtos = new LinkedList<>();
        for(User user : users) {
            userDtos.add(UserDto.convertToDto(user));
        }
        return userDtos;
    }

    public UserDto createUser(User user) {
        Random random = new Random();
        user.setId(random.nextLong());
        return UserDto.convertToDto(user);
    }

    public UserDto updateUser(User user) {
        return UserDto.convertToDto(user);
    }

    public boolean deleteUser(long id) {

        // it is VERY COMMON to archive records instead of deleting them
        // setting some "deleted" flag to true;

        return true;
    }
}
