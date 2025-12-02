package com.skillstorm.spring_aop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.spring_aop.models.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer>{
    
}
