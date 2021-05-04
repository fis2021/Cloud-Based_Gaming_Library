package org.fis2021.exceptions;

public class GameAlreadySentException extends Exception{

    public GameAlreadySentException(String gameName)
    {
        super(String.format("The game %s has already been sent to the admin!",gameName));
    }

}
