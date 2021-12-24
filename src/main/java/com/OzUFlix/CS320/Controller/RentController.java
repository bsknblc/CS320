package com.OzUFlix.CS320.Controller;

import com.OzUFlix.CS320.DTO.PenaltyDTO;
import com.OzUFlix.CS320.DTO.RentDTO;
import com.OzUFlix.CS320.Model.Rent;
import com.OzUFlix.CS320.Service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rents")
public class RentController {

    @Autowired
    RentService rentService;

    @GetMapping
    public List<RentDTO> findAll(){ return rentService.findAll(); }

    @GetMapping("/{Rent-id}")
    public RentDTO getRentById(@PathVariable("Rent-id") int id) { return rentService.findById(id); }

    @DeleteMapping("/{Rent-id}")
    public void deleteById(@PathVariable("Rent-id") int id){ rentService.deleteById(id); }

    @PostMapping
    public Rent saveRent(@RequestBody Rent Rent){
        return rentService.save(Rent);
    }

    @PostMapping("/{rent-id}/user/{user-id}")
    public RentDTO saveRentUser(@PathVariable("rent-id") int rentId, @PathVariable("user-id") int userId) {
        return rentService.saveUser(rentId, userId);
    }

    @PostMapping("/{rent-id}/movie/{movie-id}")
    public RentDTO saveRentMovie(@PathVariable("rent-id") int rentId, @PathVariable("movie-id") int movieId) {
        return rentService.saveMovie(rentId, movieId);
    }
}

