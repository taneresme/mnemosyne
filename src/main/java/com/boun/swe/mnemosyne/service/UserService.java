package com.boun.swe.mnemosyne.service;

import com.boun.swe.mnemosyne.enums.Role;
import com.boun.swe.mnemosyne.model.User;
import com.boun.swe.mnemosyne.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(final User user) {
        LOGGER.info("Storing user with username: {} and email: {}", user.getUsername(), user.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        LOGGER.info("Successfully stored user with id: {} and username: {}", user.getId(), user.getUsername());
    }

    public User findByUsername(final String username) {
        LOGGER.info("Retrieving user with username: {} ", username);
        return userRepository.findByUsername(username);
    }

}