package com.wdpvendas.springJwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wdpvendas.springJwt.model.User;
import com.wdpvendas.springJwt.service.UserService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> listarTodos(){
        return userService.listarTodos();
    }

}
