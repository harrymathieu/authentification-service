package org.sample.devops.service.authentification.controller;

import org.sample.devops.service.authentification.db.User;
import org.sample.devops.service.authentification.domain.AuthenticationService;
import org.sample.devops.service.authentification.dto.AuthenticationRequest;
import org.sample.devops.service.authentification.dto.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authentificationRequest){
        Optional<User> userOptional = authenticationService.authenticate(authentificationRequest.mail(), authentificationRequest.password());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            AuthenticationResponse authentificationResponse = new AuthenticationResponse(user.getMail(), user.getFirstname(), user.getLastname());
            return ResponseEntity.ok(authentificationResponse);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
