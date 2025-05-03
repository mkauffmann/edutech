package br.com.pucminas.edutech.controller;

import br.com.pucminas.edutech.model.dto.UserDTO;
import br.com.pucminas.edutech.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<String> getUserAccessToken(@RequestBody UserDTO user){
        return service.getUserAccessToken(user);
    }

    @PostMapping("/")
    public ResponseEntity<String> createUser(@RequestBody UserDTO user){
        return service.createUser(user);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException ex) {
        String[] error = ex.getMessage().split("\": \"");
        return ResponseEntity.status(ex.getStatusCode()).body(error[1]);
    }

}

