package com.OzUFlix.CS320.Controller;

import com.OzUFlix.CS320.DTO.RentDTO;
import com.OzUFlix.CS320.DTO.Return_MovieDTO;
import com.OzUFlix.CS320.Model.Return_Movie;
import com.OzUFlix.CS320.Service.Return_MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/return_movies")
public class Return_MovieController {

    @Autowired
    Return_MovieService return_MovieService;

    @GetMapping
    public List<Return_MovieDTO> findAll(){ return return_MovieService.findAll(); }

    @GetMapping("/{Return_Movie-id}")
    public Return_MovieDTO getReturn_MovieById(@PathVariable("Return_Movie-id") int id) {return return_MovieService.findById(id); }

    @DeleteMapping("/{Return_Movie-id}")
    public void deleteById(@PathVariable("Return_Movie-id") int id){ return_MovieService.deleteById(id); }

    @PostMapping
    public Return_Movie saveReturn_Movie(@RequestBody Return_Movie Return_Movie){
        return return_MovieService.save(Return_Movie);
    }

    @PostMapping("/{returnmovie-id}/user/{user-id}")
    public Return_MovieDTO saveReturnMovieUser(@PathVariable("returnmovie-id") int returnMovieId, @PathVariable("user-id") int userId) {
        return return_MovieService.saveUser(returnMovieId, userId);
    }

    @PostMapping("/{returnmovie-id}/rent/{rent-id}")
    public Return_MovieDTO saveReturnMovieRent(@PathVariable("returnmovie-id") int returnMovieId, @PathVariable("rent-id") int rentId) {
        return return_MovieService.saveRent(returnMovieId, rentId);
    }

    @PostMapping("/{returnmovie-id}/penalty/{penalty-id}")
    public Return_MovieDTO saveReturnMoviePenalty(@PathVariable("returnmovie-id") int returnMovieId, @PathVariable("penalty-id") int penaltyId) {
        return return_MovieService.savePenalty(returnMovieId, penaltyId);
    }
}
