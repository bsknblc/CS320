package com.OzUFlix.CS320.Controller;

import com.OzUFlix.CS320.DTO.DirectorDTO;
import com.OzUFlix.CS320.DTO.MovieDTO;
import com.OzUFlix.CS320.Model.Movie;
import com.OzUFlix.CS320.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping
    public List<MovieDTO> findAll(){ return movieService.findAll(); }

    @GetMapping("/{movie-id}")
    public MovieDTO getMovieById(@PathVariable("movie-id") int id) {return movieService.findById(id); }

    @GetMapping("/search")
    public List<MovieDTO> searchMovie(@RequestBody String movieInfo){
        List<MovieDTO> result = movieService.searchMovie(movieInfo);
        try{
            int number = Integer.parseInt(movieInfo);
            result.addAll(movieService.searchMovie(number));
        }catch (NumberFormatException e){
        }
        return result;
    }

    @DeleteMapping("/{movie-id}")
    public void deleteById(@PathVariable("movie-id") int id){
        movieService.deleteById(id);
    }

    @PostMapping
    public Movie saveMovie(@RequestBody Movie movie){
        return movieService.save(movie);
    }

    @PostMapping("/{movie-id}/director/{director-id}")
    public MovieDTO saveMovieDirector(@PathVariable("movie-id") int movieId, @PathVariable("director-id") int directorId) {
        return movieService.saveDirector(movieId, directorId);
    }

    @PostMapping("/{movie-id}/topic/{topic-id}")
    public MovieDTO saveMovieTopic(@PathVariable("movie-id") int movieId, @PathVariable("topic-id") int topicId) {
        return movieService.saveTopic(movieId, topicId);
    }

    @PostMapping("/{movie-id}/available/{available-id}")
    public MovieDTO saveMovieAvailable(@PathVariable("movie-id") int movieId, @PathVariable("available-id") int availableId) {
        return movieService.saveAvailable(movieId, availableId);
    }

    @PostMapping("/{movie-id}/rent/{rent-id}")
    public MovieDTO saveMovieRent(@PathVariable("movie-id") int movieId, @PathVariable("rent-id") int rentId) {
        return movieService.saveRent(movieId, rentId);
    }

}
