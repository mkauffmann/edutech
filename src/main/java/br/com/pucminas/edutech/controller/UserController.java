package br.com.pucminas.edutech.controller;

import br.com.pucminas.edutech.model.dto.UserDTO;
import br.com.pucminas.edutech.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
