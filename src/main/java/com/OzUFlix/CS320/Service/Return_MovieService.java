package com.OzUFlix.CS320.Service;

import com.OzUFlix.CS320.DTO.RentDTO;
import com.OzUFlix.CS320.DTO.Return_MovieDTO;
import com.OzUFlix.CS320.Model.Penalty;
import com.OzUFlix.CS320.Model.Rent;
import com.OzUFlix.CS320.Model.Return_Movie;
import com.OzUFlix.CS320.Model.User;
import com.OzUFlix.CS320.Repository.PenaltyRepository;
import com.OzUFlix.CS320.Repository.RentRepository;
import com.OzUFlix.CS320.Repository.Return_MovieRepository;
import com.OzUFlix.CS320.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Return_MovieService {
    @Autowired
    Return_MovieRepository return_movieRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RentRepository rentRepository;

    @Autowired
    PenaltyRepository penaltyRepository;

    public Return_Movie save(Return_Movie return_Movie){
        return_Movie.setDate(new Date());
        return return_movieRepository.save(return_Movie); }

    public List<Return_MovieDTO> findAll(){
        List<Return_Movie> return_movies = return_movieRepository.findAll();
        List<Return_MovieDTO> return_movieDTOS = new ArrayList<>();
        for (Return_Movie return_movie: return_movies) {
            return_movieDTOS.add(new Return_MovieDTO(return_movie.getId(), return_movie.getUser(),return_movie.getDate(),return_movie.getRent(),return_movie.getPenalty()));
        }
        return return_movieDTOS;
    }

    public Return_MovieDTO findById(int id){
        Return_Movie return_movie = return_movieRepository.findById(id);
        Return_MovieDTO return_movieDTO = new Return_MovieDTO(return_movie.getId(), return_movie.getUser(),return_movie.getDate(),return_movie.getRent(),return_movie.getPenalty());
        return  return_movieDTO;
    }

    public void deleteById(int id){ return_movieRepository.deleteById(id); }

    public Return_MovieDTO saveUser(int returnMovieId, int userId){
        User user = userRepository.findById(userId);
        Return_Movie return_movie = return_movieRepository.findById(returnMovieId);
        return_movie.setUser(user);
        return_movieRepository.save(return_movie);
        Return_MovieDTO return_movieDTO = new Return_MovieDTO(return_movie.getId(), return_movie.getUser(),return_movie.getDate(),return_movie.getRent(),return_movie.getPenalty());

        List<Return_Movie> list = new ArrayList<>();
        list.addAll(user.getReturn_movies());
        list.add(return_movie);
        user.setReturn_movies(list);
        userRepository.save(user);

        return  return_movieDTO;
    }

    public Return_MovieDTO saveRent(int returnMovieId, int rentId){
        Rent rent = rentRepository.findById(rentId);
        Return_Movie return_movie = return_movieRepository.findById(returnMovieId);
        return_movie.setRent(rent);
        return_movieRepository.save(return_movie);
        Return_MovieDTO return_movieDTO = new Return_MovieDTO(return_movie.getId(), return_movie.getUser(),return_movie.getDate(),return_movie.getRent(),return_movie.getPenalty());

        rent.setReturn_movie(return_movie);
        rentRepository.save(rent);

        return  return_movieDTO;
    }

    public Return_MovieDTO savePenalty(int returnMovieId, int penaltyId){
        Penalty penalty = penaltyRepository.findById(penaltyId);
        Return_Movie return_movie = return_movieRepository.findById(returnMovieId);
        return_movie.setPenalty(penalty);
        return_movieRepository.save(return_movie);
        Return_MovieDTO return_movieDTO = new Return_MovieDTO(return_movie.getId(), return_movie.getUser(),return_movie.getDate(),return_movie.getRent(),return_movie.getPenalty());

        penalty.setReturn_movie(return_movie);
        penaltyRepository.save(penalty);

        return  return_movieDTO;
    }
}