package com.OzUFlix.CS320.Service;

import com.OzUFlix.CS320.DTO.DirectorDTO;
import com.OzUFlix.CS320.DTO.MovieDTO;
import com.OzUFlix.CS320.Model.Available;
import com.OzUFlix.CS320.Model.Director;
import com.OzUFlix.CS320.Model.Movie;
import com.OzUFlix.CS320.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public Movie save(Movie movie){ return movieRepository.save(movie); }

    public List<MovieDTO> findAll(){
        List<Movie> movies = movieRepository.findAll();
        List<MovieDTO> movieDTOs = new ArrayList<MovieDTO>();
        for (Movie movie: movies) {
            movieDTOs.add(new MovieDTO(movie.getId(), movie.getName(), movie.getDirector(),movie.getTopic(),movie.getAvailable(),movie.getRents()));
        }
        return movieDTOs;
    }

    public MovieDTO findById(int id){
        Movie movie = movieRepository.findById(id);
        MovieDTO movieDTO = new MovieDTO(movie.getId(), movie.getName(), movie.getDirector(),movie.getTopic(),movie.getAvailable(),movie.getRents());
        return  movieDTO;
    }

    public void deleteById(int id){ movieRepository.deleteById(id); }

    public List<Movie> searchMovie(int available){return  movieRepository.searchMovie(available);}
}