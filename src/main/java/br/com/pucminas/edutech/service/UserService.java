package br.com.pucminas.edutech.service;

import br.com.pucminas.edutech.model.dto.keycloak.KeycloakRole;
import br.com.pucminas.edutech.model.dto.keycloak.KeycloakUserDTO;
import br.com.pucminas.edutech.model.dto.LoginResponse;
import br.com.pucminas.edutech.model.dto.RegistrationResponse;
import br.com.pucminas.edutech.model.dto.TokenDTO;
import br.com.pucminas.edutech.model.dto.UserDTO;
import br.com.pucminas.edutech.model.dto.UserResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private Environment env;

    public ResponseEntity<LoginResponse> getUserAccessToken(UserDTO user){

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

        ResponseEntity<String> tokenResponse = rt.postForEntity(url, entity, String.class);
        
        if (tokenResponse.getStatusCode().is2xxSuccessful()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> tokenData = mapper.readValue(tokenResponse.getBody(), new TypeReference<Map<String, Object>>() {});
                
                UserResponse userInfo = null;
                try {
                    String accessToken = (String) tokenData.get("access_token");
                    KeycloakUserDTO keycloakUser = getUserInfoFromToken(accessToken);
                    
                    if (keycloakUser != null) {
                        userInfo = new UserResponse(
                            keycloakUser.getId(),
                            keycloakUser.getUsername(),
                            keycloakUser.getEmail()
                        );
                    }
                } catch (Exception userInfoError) {
                    System.err.println("Failed to get user info: " + userInfoError.getMessage());
                }
                
                LoginResponse loginResponse = new LoginResponse(
                    (String) tokenData.get("access_token"),
                    (Integer) tokenData.get("expires_in"),
                    (String) tokenData.get("refresh_token"),
                    (String) tokenData.get("token_type"),
                    userInfo
                );
                
                return ResponseEntity.ok(loginResponse);
            } catch (Exception e) {
                System.err.println("Error processing login response: " + e.getMessage());
                e.printStackTrace();
                throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing login response: " + e.getMessage());
            }
        } else {
            throw new HttpClientErrorException(tokenResponse.getStatusCode(), "Login failed");
        }
    }

    private KeycloakUserDTO getUserInfoFromToken(String accessToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            RestTemplate rt = new RestTemplate();
            String url = env.getProperty("keycloak.url") +
                    "/realms/" + env.getProperty("keycloak.realm") +
                    "/protocol/openid-connect/userinfo";

            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            System.out.println("Calling userinfo URL: " + url);
            ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println("Userinfo response: " + response.getBody());
            
            ObjectMapper mapper = new ObjectMapper();
            KeycloakUserDTO userDTO = mapper.readValue(response.getBody(), KeycloakUserDTO.class);
            
            return userDTO;
        } catch (Exception e) {
            System.err.println("Error getting user info from token: " + e.getMessage());
            e.printStackTrace();
            
            return extractUserFromJWT(accessToken);
        }
    }
    
    private KeycloakUserDTO extractUserFromJWT(String accessToken) {
        try {
            String[] chunks = accessToken.split("\\.");
            if (chunks.length < 2) {
                return null;
            }
            
            java.util.Base64.Decoder decoder = java.util.Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            
            ObjectMapper mapper = new ObjectMapper();
            com.fasterxml.jackson.databind.JsonNode jsonNode = mapper.readTree(payload);
            
            KeycloakUserDTO userDTO = new KeycloakUserDTO();
            userDTO.setId(jsonNode.get("sub").asText());
            userDTO.setUsername(jsonNode.get("preferred_username").asText());
            userDTO.setEmail(jsonNode.get("email").asText());
            userDTO.setEnabled(true);
            
            System.out.println("Extracted user from JWT: " + userDTO.getUsername());
            return userDTO;
        } catch (Exception e) {
            System.err.println("Error extracting user from JWT: " + e.getMessage());
            return null;
        }
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

    public ResponseEntity<RegistrationResponse> createUser(UserDTO user) throws HttpClientErrorException{
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
        setUserStudentRole(userId, headers);
        
        // After creating the user, automatically log them in to get token
        ResponseEntity<LoginResponse> loginResponse = getUserAccessToken(user);
        LoginResponse login = loginResponse.getBody();
        
        if (login != null) {
            RegistrationResponse registrationResponse = new RegistrationResponse(
                login.access_token(),
                login.expires_in(),
                login.refresh_token(),
                login.token_type(),
                login.user()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponse);
        }
        
        throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to generate tokens after registration");
    }

    private KeycloakUserDTO getUserById(String userId, HttpHeaders headers) {
        RestTemplate rt = new RestTemplate();

        String url = env.getProperty("keycloak.url") +
                "/admin/realms/" + env.getProperty("keycloak.realm") +
                "/users/" + userId;

        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        ResponseEntity<KeycloakUserDTO> response = rt.exchange(url, HttpMethod.GET, entity, KeycloakUserDTO.class);
        return response.getBody();
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
