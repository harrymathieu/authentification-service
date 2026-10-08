package org.sample.devops.service.authentication.infra;

public class User {
    private final String mail;
    private final String firstname;
    private final String lastname;
    private final String password;

    public User(String mail, String firstname, String lastname, String password){
        this.mail = mail;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    public String getLastname(){
        return lastname;
    }

    public String getMail(){
        return mail;
    }

    public String getFirstname(){
        return firstname;
    }

    public String getPassword(){
        return password;
    }
}
