package com.skillstorm.spingmvc.services;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.spingmvc.dtos.UserDto;
import com.skillstorm.spingmvc.mappers.UserMapper;
import com.skillstorm.spingmvc.models.User;

@Service    // stereotype annotation for @Component
public class UserService {
    
    /*
     * all business logic for controllers and repositories
     * 
     * 
     * 
     * for this example, I'm simulating getting data back
     *      IN PRACTICE - you'd want to call you repository
     *  
     */

    @Autowired
    UserMapper mapper;

    public List<UserDto> findAllUsers() {
        List<User> users = new LinkedList<>();

        users.add(new User(0, "Austin", "Reeves", "areeves@skillstorm.com", "veryStrongPasword123!"));
        users.add(new User(1, "Calin", "Blauth", "cblauth@skillstorm.com", "veryStrongPasword123!"));
        users.add(new User(2, "Jordan", "Espinosa", "jespinosa@skillstorm.com", "veryStrongPasword123!"));
        users.add(new User(3, "Chris", "Beherens", "cbehrens@skillstorm.com", "veryStrongPasword123!"));

        // converting list<user> to Stream<User>. Then converting Stream<User> to Stream<UserDto>. Then Stream<UserDto> to List<UserDto>
        List<UserDto> userDtos = users.stream().map(mapper::toDto).collect(Collectors.toList());
        return userDtos;
    }

    public List<User> findUsersByFirstName(String name) {
        List<User> users = new LinkedList<>();

        users.add(new User(0, name, "Reeves", "areeves@skillstorm.com", "veryStrongPasword123!"));
        users.add(new User(1, name, "Blauth", "cblauth@skillstorm.com", "veryStrongPasword123!"));
        users.add(new User(2, name, "Espinosa", "jespinosa@skillstorm.com", "veryStrongPasword123!"));
        users.add(new User(3, name, "Beherens", "cbehrens@skillstorm.com", "veryStrongPasword123!"));

        return users;
    }

    public User findById(long id) {
        return new User(id, "Austin", "Reeves", "areeves@skillstorm.com", "veryStrongPasword123!");
    }

    public User createUser(User user) {
        user.setId(1000);
        return user;
    }

    public User updateUser(long id, User user) {
        user.setId(id);
        return user;
    }

    public User deleteUser(long id, User user) {
        return user;
    }
}
