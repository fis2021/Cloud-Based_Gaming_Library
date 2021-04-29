package org.fis2021.exceptions;

public class NoGameFoundException extends  Exception{
    public NoGameFoundException(){
        super(String.format("No game found"));
    }
}
