package com.OzUFlix.CS320.Service;

import com.OzUFlix.CS320.Model.Director;
import com.OzUFlix.CS320.Repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService {
    @Autowired
    DirectorRepository directorRepository;

    public Director save(Director director){ return directorRepository.save(director); }

    public List<Director> findAll(){ return directorRepository.findAll();}

    public Director findById(int id){ return directorRepository.findById(id); }

    public void deleteById(int id){ directorRepository.deleteById(id); }
}