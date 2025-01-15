package com.jpaprac.jpapractice.repository;

import com.jpaprac.jpapractice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
