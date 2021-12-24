package com.OzUFlix.CS320.Service;

import com.OzUFlix.CS320.DTO.MovieDTO;
import com.OzUFlix.CS320.DTO.RentDTO;
import com.OzUFlix.CS320.DTO.TopicDTO;
import com.OzUFlix.CS320.DTO.UserDTO;
import com.OzUFlix.CS320.Model.*;
import com.OzUFlix.CS320.Repository.PenaltyRepository;
import com.OzUFlix.CS320.Repository.RentRepository;
import com.OzUFlix.CS320.Repository.Return_MovieRepository;
import com.OzUFlix.CS320.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RentRepository rentRepository;

    @Autowired
    Return_MovieRepository return_movieRepository;

    @Autowired
    PenaltyRepository penaltyRepository;

    public User save(User user){ return userRepository.save(user); }

    public List<UserDTO> findAll(){
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user: users) {
            userDTOS.add(new UserDTO(user.getId(),user.getName(),user.getPassword(),user.getUserType(),user.getRents(),user.getReturn_movies(),user.getPenalties()));
        }
        return userDTOS;
    }

    public UserDTO findById(int id){
        User user = userRepository.findById(id);
        UserDTO userDTO = new UserDTO(user.getId(),user.getName(),user.getPassword(),user.getUserType(),user.getRents(),user.getReturn_movies(),user.getPenalties());
        return  userDTO;
    }

    public void deleteById(int id){
        userRepository.deleteById(id);
    }

    public boolean validateUser(String username, String password){
        User user = userRepository.findUserByNameAndPassword(username, password);
        boolean count=false;
        if (user!=null){
            count=true;
        }
        return  count;
    }

    public UserDTO saveRent(int userId, int rentId){
        Rent rent = rentRepository.findById(rentId);
        User user = userRepository.findById(userId);
        List<Rent> list = new ArrayList<>();
        list.addAll(user.getRents());
        list.add(rent);
        user.setRents(list);
        UserDTO userDTO = new UserDTO(user.getId(),user.getName(),user.getPassword(),user.getUserType(),user.getRents(),user.getReturn_movies(),user.getPenalties());

        rent.setUser(user);
        rentRepository.save(rent);

        return  userDTO;
    }

    public UserDTO saveReturnMovie(int userId, int returnMovieId){
        Return_Movie return_movie = return_movieRepository.findById(returnMovieId);
        User user = userRepository.findById(userId);
        List<Return_Movie> list = new ArrayList<>();
        list.addAll(user.getReturn_movies());
        list.add(return_movie);
        user.setReturn_movies(list);
        userRepository.save(user);
        UserDTO userDTO = new UserDTO(user.getId(),user.getName(),user.getPassword(),user.getUserType(),user.getRents(),user.getReturn_movies(),user.getPenalties());

        return_movie.setUser(user);
        return_movieRepository.save(return_movie);

        return  userDTO;
    }

    public UserDTO savePenalty(int userId, int penaltyId){
        Penalty penalty = penaltyRepository.findById(penaltyId);
        User user = userRepository.findById(userId);
        List<Penalty> list = new ArrayList<>();
        list.addAll(user.getPenalties());
        list.add(penalty);
        user.setPenalties(list);
        userRepository.save(user);
        UserDTO userDTO = new UserDTO(user.getId(),user.getName(),user.getPassword(),user.getUserType(),user.getRents(),user.getReturn_movies(),user.getPenalties());

        penalty.setUser(user);
        penaltyRepository.save(penalty);

        return  userDTO;
    }


}
