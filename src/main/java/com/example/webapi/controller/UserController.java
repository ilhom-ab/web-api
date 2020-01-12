package com.example.webapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webapi.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserRepository userRepository;

}
