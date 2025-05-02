package br.com.pucminas.edutech.controller;

import br.com.pucminas.edutech.model.dto.TokenDTO;
import br.com.pucminas.edutech.model.dto.UserDTO;
import br.com.pucminas.edutech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
