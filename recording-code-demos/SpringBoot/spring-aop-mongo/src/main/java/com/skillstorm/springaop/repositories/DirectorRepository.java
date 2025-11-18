package com.skillstorm.springaop.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.springaop.models.Director;

@Repository
public interface DirectorRepository extends MongoRepository<Director, String>{
    
}
