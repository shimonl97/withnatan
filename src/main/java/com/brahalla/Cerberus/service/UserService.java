package com.brahalla.Cerberus.service;

import com.brahalla.Cerberus.model.user.User;
import com.brahalla.Cerberus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 0503337710 on 11/09/2016.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        // duplicate username
        User userCreated = userRepository.findOne(user.getId());
        if (userCreated != null) {
            return userCreated;
        }
        return userRepository.save(user);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public User getById(String username) {
        return userRepository.findOne(username);
    }

}