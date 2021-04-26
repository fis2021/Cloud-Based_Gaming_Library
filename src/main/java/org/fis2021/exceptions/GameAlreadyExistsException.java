package org.fis2021.exceptions;

public class GameAlreadyExistsException extends Exception{

    public GameAlreadyExistsException(String gameName)
    {
        super(String.format("The game %s already added to the library",gameName));
    }

}
