package ru.gridusov.shorturl.exceptions;

public class NotEnoughSpaceError extends RuntimeException{
    public NotEnoughSpaceError(String message){
        super(message);
    }

}
