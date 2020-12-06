package hu.unideb.webdev.exceptions;

public class UnknownGenderException extends Exception{

    public UnknownGenderException(){

    }

    public UnknownGenderException(String message){
        super(message);
    }
}
