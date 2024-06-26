package org.example.BlogProject.controllers;

import org.example.BlogProject.models.Author;
import org.example.BlogProject.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@Controller
public class LoginController {

    @Autowired
    ServiceLayer service;


    @PostMapping("/process_register")
    public ResponseEntity <Author> processRegister(@Valid @RequestBody Author user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getAuthorPassword());
        user.setAuthorPassword(encodedPassword);

        service.saveAuthor(user);

        return ResponseEntity.ok(user);
    }
}