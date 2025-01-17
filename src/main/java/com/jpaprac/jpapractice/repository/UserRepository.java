package com.jpaprac.jpapractice.repository;

import com.jpaprac.jpapractice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);


    @Query("SELECT u FROM User u")
    Stream<User> findAllByCustomStream();
}