package com.wdpvendas.springJwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wdpvendas.springJwt.model.User;
import com.wdpvendas.springJwt.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable("user")
    public List<User> listarTodos() {
        return userRepository.findAll();
    }

}
