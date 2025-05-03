package br.com.pucminas.edutech.service;

import br.com.pucminas.edutech.model.dto.keycloak.KeycloakRole;
import br.com.pucminas.edutech.model.dto.keycloak.KeycloakUserDTO;
import br.com.pucminas.edutech.model.dto.TokenDTO;
import br.com.pucminas.edutech.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private Environment env;


    public ResponseEntity<String> getUserAccessToken(UserDTO user){

        HttpHeaders headers = new HttpHeaders();
        RestTemplate rt = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String url = env.getProperty("keycloak.url") +
                "/realms/" + env.getProperty("keycloak.realm") +
                "/protocol/openid-connect/token";

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", env.getProperty("keycloak.client-id"));
        formData.add("username", user.getUsername());
        formData.add("password", user.getPassword());
        formData.add("grant_type", env.getProperty("keycloak.grant-type.user"));
        formData.add("client_secret", env.getProperty("keycloak.client-secret"));

        HttpEntity<MultiValueMap<String, String>> entity
                = new HttpEntity<MultiValueMap<String,String>>(formData, headers);

        return rt.postForEntity(url, entity, String.class);
    }

    private TokenDTO getServiceAccountAccessToken(){

        HttpHeaders headers = new HttpHeaders();
        RestTemplate rt = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String url = env.getProperty("keycloak.url") +
                "/realms/" + env.getProperty("keycloak.realm") +
                "/protocol/openid-connect/token";

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", env.getProperty("keycloak.client-id"));
        formData.add("grant_type", env.getProperty("keycloak.grant-type.service"));
        formData.add("client_secret", env.getProperty("keycloak.client-secret"));

        HttpEntity<MultiValueMap<String, String>> entity
                = new HttpEntity<MultiValueMap<String,String>>(formData, headers);

        TokenDTO token =  rt.postForObject(url, entity, TokenDTO.class);
        return token;
    }

    public ResponseEntity<String>  createUser(UserDTO user) throws HttpClientErrorException{
        String accessToken = getServiceAccountAccessToken().getAccess_token();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate rt = new RestTemplate();

        String url = env.getProperty("keycloak.url") +
                "/admin/realms/" + env.getProperty("keycloak.realm") +
                "/users";

        HttpEntity<KeycloakUserDTO> entity
                = new HttpEntity<>(convertToKeycloakUserDTO(user), headers);

        ResponseEntity<String> response = rt.postForEntity(url, entity, String.class);
        List<String> location = Arrays.stream(Objects.requireNonNull(response.getHeaders().getLocation()).toString()
                .split("/")).toList();
        String userId = location.get(location.size() - 1);


        setUserPassword(userId, user.getPassword(), headers);
        return setUserStudentRole(userId, headers);
    }

    private KeycloakUserDTO convertToKeycloakUserDTO(UserDTO user) {
        KeycloakUserDTO kc = new KeycloakUserDTO();

        kc.setUsername(user.getUsername());
        kc.setEnabled(true);

        if (user.getEmail() != null){
            kc.setEmail(user.getEmail());
        }

        return kc;
    }

    private ResponseEntity<String>  setUserPassword(String userId, String password, HttpHeaders headers) throws HttpClientErrorException {
        RestTemplate rt = new RestTemplate();

        String url = env.getProperty("keycloak.url") +
                "/admin/realms/" + env.getProperty("keycloak.realm") +
                "/users/" + userId + "/reset-password";

        HttpEntity<String> entity
                = new HttpEntity<>("{\"type\":\"password\",\"value\":\"" + password + "\",\"temporary\":false}", headers);

        ResponseEntity<String> response = rt.exchange(url, HttpMethod.PUT, entity, String.class);
        return response;

    }

    private ResponseEntity<String> getUserFromUsername(String username, HttpHeaders headers){
        RestTemplate rt = new RestTemplate();

        String url = env.getProperty("keycloak.url") +
                "/admin/realms/" + env.getProperty("keycloak.realm") +
                "/users?username=" + username;

        HttpEntity<KeycloakUserDTO> entity
                = new HttpEntity<>(headers);


        ResponseEntity<String> response = rt.exchange(
                url, HttpMethod.GET, entity, String.class);

        return response;
    }

    private ResponseEntity<String> setUserStudentRole(String userId, HttpHeaders headers) throws  HttpClientErrorException{
        RestTemplate rt = new RestTemplate();

        String url = env.getProperty("keycloak.url") +
                "/admin/realms/" + env.getProperty("keycloak.realm") +
                "/users/" + userId + "/role-mappings/realm";

        KeycloakRole role = new KeycloakRole();
        role.setId(env.getProperty("keycloak.role.student.id"));
        role.setName(env.getProperty("keycloak.role.student.name"));
        role.setComposite("false");
        role.setClientRole("false");
        role.setContainerId(env.getProperty("keycloak.realm.id"));

        List<KeycloakRole> roleList = new ArrayList<>();
        roleList.add(role);

        HttpEntity<List<KeycloakRole>> entity
                = new HttpEntity<>(roleList, headers);

        ResponseEntity<String> response = rt.exchange(url, HttpMethod.POST, entity, String.class);
        return response;
    }

}
