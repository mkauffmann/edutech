package br.com.pucminas.edutech.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import br.com.pucminas.edutech.model.dto.LoginResponse;
import br.com.pucminas.edutech.model.dto.RegistrationResponse;
import br.com.pucminas.edutech.model.dto.UserDTO;
import br.com.pucminas.edutech.model.dto.UserResponse;
import br.com.pucminas.edutech.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> getUserAccessToken(@RequestBody UserDTO user){
        return service.getUserAccessToken(user);
    }

    @PostMapping("/")
    public ResponseEntity<RegistrationResponse> createUser(@RequestBody UserDTO user){
        return service.createUser(user);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException ex) {
        String message = ex.getMessage();
        if (message != null && message.contains("\": \"")) {
            String[] error = message.split("\": \"");
            if (error.length > 1) {
                return ResponseEntity.status(ex.getStatusCode()).body(error[1]);
            }
        }
        return ResponseEntity.status(ex.getStatusCode()).body(message != null ? message : "An error occurred");
    }

}

