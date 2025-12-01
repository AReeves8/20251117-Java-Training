package com.skillstorm.spring_data_jpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.spring_data_jpa.models.Director;
import com.skillstorm.spring_data_jpa.repositories.DirectorRepository;



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
