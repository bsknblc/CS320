package com.OzUFlix.CS320.Service;

import com.OzUFlix.CS320.DTO.MovieDTO;
import com.OzUFlix.CS320.DTO.PenaltyDTO;
import com.OzUFlix.CS320.DTO.RentDTO;
import com.OzUFlix.CS320.Model.*;
import com.OzUFlix.CS320.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RentService {
    @Autowired
    RentRepository rentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    PenaltyRepository penaltyRepository;

    @Autowired
    Return_MovieRepository return_movieRepository;

    public Rent save(Rent rent){ return rentRepository.save(rent); }

    public List<RentDTO> findAll(){
        List<Rent> rents = rentRepository.findAll();
        List<RentDTO> rentDTOS = new ArrayList<>();
        for (Rent rent: rents) {
            rentDTOS.add(new RentDTO(rent.getId(),rent.getUser(),rent.getMovie(),rent.getDate(), rent.getPenalty(),rent.getReturn_movie()));
        }
        return rentDTOS;
    }

    public RentDTO findById(int id){
        Rent rent = rentRepository.findById(id);
        RentDTO rentDTO = new RentDTO(rent.getId(),rent.getUser(),rent.getMovie(),rent.getDate(), rent.getPenalty(),rent.getReturn_movie());
        return  rentDTO;
    }

    public void deleteById(int id){ rentRepository.deleteById(id); }

    public RentDTO saveUser(int rentId, int userId){
        User user = userRepository.findById(userId);
        Rent rent = rentRepository.findById(rentId);
        rent.setUser(user);
        rentRepository.save(rent);
        RentDTO rentDTO = new RentDTO(rent.getId(),rent.getUser(),rent.getMovie(),rent.getDate(), rent.getPenalty(),rent.getReturn_movie());

        List<Rent> list = new ArrayList<>();
        list.addAll(user.getRents());
        list.add(rent);
        user.setRents(list);
        userRepository.save(user);

        return  rentDTO;
    }

    public RentDTO saveMovie(int rentId, int movieId){
        Movie movie = movieRepository.findById(movieId);
        if(movie.getAvailable().getInfo()==1) {
            Rent rent = rentRepository.findById(rentId);
            rent.setMovie(movie);
            rentRepository.save(rent);
            RentDTO rentDTO = new RentDTO(rent.getId(), rent.getUser(), rent.getMovie(), rent.getDate(), rent.getPenalty(), rent.getReturn_movie());

            List<Rent> list = new ArrayList<>();
            list.addAll(movie.getRents());
            list.add(rent);
            movie.setRents(list);
            movieRepository.save(movie);
            return  rentDTO;
        }
        return  null;
    }

    public RentDTO saveDate(int rentId){
        Rent rent = rentRepository.findById(rentId);
        rent.setDate(new Date());
        rentRepository.save(rent);
        RentDTO rentDTO = new RentDTO(rent.getId(),rent.getUser(),rent.getMovie(),rent.getDate(), rent.getPenalty(),rent.getReturn_movie());

        return  rentDTO;
    }

    public RentDTO savePenalty(int rentId, int penaltyId){
        Penalty penalty = penaltyRepository.findById(penaltyId);
        Rent rent = rentRepository.findById(rentId);
        rent.setPenalty(penalty);
        rentRepository.save(rent);
        RentDTO rentDTO = new RentDTO(rent.getId(),rent.getUser(),rent.getMovie(),rent.getDate(), rent.getPenalty(),rent.getReturn_movie());

        penalty.setRent(rent);
        penaltyRepository.save(penalty);
        return  rentDTO;
    }

    public RentDTO saveReturnMovie(int rentId, int returnMovieId){
        Return_Movie return_movie = return_movieRepository.findById(returnMovieId);
        Rent rent = rentRepository.findById(rentId);
        rent.setReturn_movie(return_movie);
        rentRepository.save(rent);
        RentDTO rentDTO = new RentDTO(rent.getId(),rent.getUser(),rent.getMovie(),rent.getDate(), rent.getPenalty(),rent.getReturn_movie());

        return_movie.setRent(rent);
        return_movieRepository.save(return_movie);
        return  rentDTO;
    }

    public MovieDTO getMost(){
        int max = 0;
        int maxIndex = 0;
        List<Rent> rents = rentRepository.findAll();
        int[] counts = new int[rents.size()];
        for(Rent rent: rents){
            counts[rent.getMovie().getId()-1] += 1;
        }
        for(int i = 0; i<counts.length;i++){
            if (max<counts[i]){
                max=counts[i];
                maxIndex = i+1;
            }
        }

        Movie movie = movieRepository.findById(maxIndex);
        MovieDTO movieDTO = new MovieDTO(movie.getId(), movie.getName(), movie.getDirector(),movie.getTopic(),movie.getAvailable(),movie.getRents());

        return movieDTO;
    }
}