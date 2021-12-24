package com.OzUFlix.CS320.Service;

import com.OzUFlix.CS320.DTO.AvailableDTO;
import com.OzUFlix.CS320.Model.Available;
import com.OzUFlix.CS320.Model.Movie;
import com.OzUFlix.CS320.Repository.AvailableRepository;
import com.OzUFlix.CS320.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvailableService {
    @Autowired
    AvailableRepository availableRepository;

    @Autowired
    MovieRepository movieRepository;

    public Available save(Available available){ return availableRepository.save(available); }

    public AvailableDTO saveMovie(int movieId, int availableId){
        availableRepository.findById(availableId).getMovies().add(movieRepository.getById(movieId));
        availableRepository.save(availableRepository.findById(availableId));
        return  findById(availableId);
    }

    public List<AvailableDTO> findAll(){
        List<Available> availables = availableRepository.findAll();
        List<AvailableDTO> availableDTOs = new ArrayList<AvailableDTO>();
        for (Available available: availables) {
            availableDTOs.add(new AvailableDTO(available.getId(),available.getInfo(),available.getMovies()));
        }
        return availableDTOs;
    }

    public AvailableDTO findById(int id){
        Available available = availableRepository.findById(id);
        AvailableDTO availableDTO = new AvailableDTO(available.getId(),available.getInfo(), available.getMovies());
        return  availableDTO;
    }

    public void deleteById(int id){ availableRepository.deleteById(id); }
}