package com.jpaprac.jpapractice.service;

import com.jpaprac.jpapractice.model.entity.User;
import com.jpaprac.jpapractice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public abstract class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public User getUserWithReadCommitted(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
