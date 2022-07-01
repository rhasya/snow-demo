package com.snow.demo.service;

import com.snow.demo.model.User;
import com.snow.demo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository r) {
        this.repository = r;
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUser(Long id) {
        Optional<User> ou = repository.findById(id);
        if (ou.isPresent()) return ou.get();
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "wrong id");
    }

    public Long createUser(User user) {
        // set enabled to true
        user.setEnabled(true);
        // save
        User result = repository.save(user);
        return result.getId();
    }
}
