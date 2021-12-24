package com.OzUFlix.CS320.Controller;

import com.OzUFlix.CS320.DTO.MovieDTO;
import com.OzUFlix.CS320.DTO.PenaltyDTO;
import com.OzUFlix.CS320.Model.Penalty;
import com.OzUFlix.CS320.Service.PenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/penalties")
public class PenaltyController {

    @Autowired
    PenaltyService penaltyService;

    @GetMapping
    public List<PenaltyDTO> findAll(){ return penaltyService.findAll(); }

    @GetMapping("/{penalty-id}")
    public PenaltyDTO getPenaltyById(@PathVariable("penalty-id") int id) {return penaltyService.findById(id); }

    @DeleteMapping("/{penalty-id}")
    public void deleteById(@PathVariable("penalty-id") int id){ penaltyService.deleteById(id); }

    @PostMapping
    public Penalty savePenalty(@RequestBody Penalty penalty){
        return penaltyService.save(penalty);
    }

    @PostMapping("/{penalty-id}/user/{user-id}")
    public PenaltyDTO savePenaltyUser(@PathVariable("penalty-id") int penaltyId, @PathVariable("user-id") int userId) {
        return penaltyService.saveUser(penaltyId, userId);
    }

    @PostMapping("/{penalty-id}/rent/{rent-id}")
    public PenaltyDTO savePenaltyRent(@PathVariable("penalty-id") int penaltyId, @PathVariable("rent-id") int rentId) {
        return penaltyService.saveRent(penaltyId, rentId);
    }

    @PostMapping("/{penalty-id}/returnmovie/{returnmovie-id}")
    public PenaltyDTO savePenaltyReturnMovie(@PathVariable("penalty-id") int penaltyId, @PathVariable("returnmovie-id") int returnMovieId) {
        return penaltyService.saveReturnMovie(penaltyId, returnMovieId);
    }
}

