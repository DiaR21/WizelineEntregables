package com.wizeline.maven.learningjavamaven.controller;


import java.util.logging.Logger;

import com.wizeline.maven.learningjavamaven.model.ResponseDTO;
import com.wizeline.maven.learningjavamaven.model.UserDTO;
import com.wizeline.maven.learningjavamaven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

    @RequestMapping("/api")
    @RestController
    public class UserController {

        @Autowired
        UserService userService;

        private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

        @GetMapping(value = "/login", produces = "aplication/json")
        public ResponseEntity<ResponseDTO> login(@RequestParam String user, String password){
            LOGGER.info("LeargingJava - Procesondo peticion HTTP de tipo GET-Starting...");
            ResponseDTO response = new ResponseDTO();
            response=userService.login(user, password);
            LOGGER.info("Login - Completed");
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
        }

        @PostMapping("/createUser", produces = "aplication/json")
        public  ResponseEntity<ResponseDTO> createUserAccount(@RequestBody UserDTO userDTO) {
            LOGGER.info("LeargingJava - Procesondo peticion HTTP de tipo GET-Starting...");
            ResponseDTO response = new ResponseDTO();
            response = userService.createUser(userDTO.getUser(), userDTO.getPassword());
            LOGGER.info("Login - Completed");
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
        }
    }