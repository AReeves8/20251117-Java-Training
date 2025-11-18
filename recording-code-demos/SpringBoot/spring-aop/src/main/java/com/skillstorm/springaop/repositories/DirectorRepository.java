package com.skillstorm.springaop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.springaop.models.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer>{
    
}
