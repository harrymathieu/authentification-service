package org.sample.devops.service.authentication.domain;

import org.sample.devops.service.authentication.infra.User;
import org.sample.devops.service.authentication.infra.UserDB;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserDB userDB;

    public AuthenticationService(UserDB userDB){
        this.userDB = userDB;
    }

    public Optional<User> authenticate(String mail, String password){
        Optional<User> user= this.userDB.getUserbyMail(mail);
        if(user.isPresent()){
            if(user.get().getPassword().equals(password)){
                return user;
            }
        }
        return Optional.empty();
    }
}
