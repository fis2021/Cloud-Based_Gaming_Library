package org.fis2021.exceptions;

import java.util.StringTokenizer;

public class UsernameNotFoundException extends Exception {

    private String username, password;

    public UsernameNotFoundException(String username) {
        super(String.format("The Username %s is invalid!", username));
        this.username = username;
    }

    public UsernameNotFoundException(String username, String password) {
        super(String.format("Insert your Username and Password in their specific fields!"));
    }

    public String getUsername() {
        return username;
    }
}