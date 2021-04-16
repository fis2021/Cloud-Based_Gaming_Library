package org.fis2021.exceptions;

import java.util.StringTokenizer;

public class UsernameAlreadyExistsException extends Exception {

    private String username, password;

    public UsernameAlreadyExistsException(String username) {
        super(String.format("An account with the username %s already exists!", username));
        this.username = username;
    }

    public UsernameAlreadyExistsException(String username, String password){
        super(String.format("Username or password not valid!"));
    }

    public String getUsername() {
        return username;
    }
}