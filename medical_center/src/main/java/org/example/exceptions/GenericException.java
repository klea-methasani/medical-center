package org.example.exceptions;

public class GenericException extends RuntimeException{
    public GenericException(String message){
        super(message);
    }

    public static GenericException idNotNull(){
        String message = "Id must be null";
        return new GenericException(message);
    }
    public static GenericException idIsNull(){
        String message = "Id must not be null";
        return new GenericException(message);
    }

    public static GenericException usernameExists(String username){
        String message = String.format("Record with username %s exists!", username);
        return new GenericException(message);
    }
}
