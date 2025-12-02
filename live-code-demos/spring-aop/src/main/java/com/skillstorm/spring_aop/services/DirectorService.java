package com.skillstorm.spring_aop.services;

import org.springframework.stereotype.Service;

import com.skillstorm.spring_aop.models.Director;
import com.skillstorm.spring_aop.repositories.DirectorRepository;



@Service
public class DirectorService {
    
    private final DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public Director saveDirector(Director director) {
        return directorRepository.save(director);
    }

}
