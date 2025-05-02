package br.com.pucminas.edutech.service;

import br.com.pucminas.edutech.model.dto.TokenDTO;
import br.com.pucminas.edutech.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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

    public ResponseEntity<String> createUser(UserDTO user){
        String accessToken = getServiceAccountAccessToken().getAccess_token();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate rt = new RestTemplate();

        String url = env.getProperty("keycloak.url") +
                "/admin/realms/" + env.getProperty("keycloak.realm") +
                "/users";

        HttpEntity<String> entity
                = new HttpEntity<>("{\"username\": \"teste\"}", headers);

        return rt.postForEntity(url, entity, String.class);
    }


}
