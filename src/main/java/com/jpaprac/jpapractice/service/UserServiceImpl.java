package com.jpaprac.jpapractice.service;

import com.jpaprac.jpapractice.model.entity.User;
import com.jpaprac.jpapractice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

public abstract class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public User getUserWithReadCommitted(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }


    public Page<User> getUsersWithPaging(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public void processUsersStream() {
        try (Stream<User> userStream = userRepository.findAllByCustomStream()) {
            userStream.forEach(user -> {
                System.out.println("Processing user: " + user.getName());
            });
        }
    }

}
