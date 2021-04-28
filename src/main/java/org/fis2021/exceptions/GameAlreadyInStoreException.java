package org.fis2021.exceptions;

public class GameAlreadyInStoreException extends Exception{

    public GameAlreadyInStoreException(String gameName)
    {
        super(String.format("The game %s has already been added to the store!",gameName));
    }

}
