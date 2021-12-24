package com.OzUFlix.CS320.Repository;

import com.OzUFlix.CS320.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findById(int id);

    @Query("SELECT u FROM User AS u WHERE u.name=?1 and u.password=?2")
    public User findUserByNameAndPassword(String name, String password);

}
