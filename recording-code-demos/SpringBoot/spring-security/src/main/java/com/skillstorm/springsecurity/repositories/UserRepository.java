package com.skillstorm.springsecurity.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.springsecurity.models.AppUser;


@Repository
public interface UserRepository extends JpaRepository<AppUser, Long>{
    
    // allows us to search for a user in the database
    public Optional<AppUser> findByUsername(String username);
}
