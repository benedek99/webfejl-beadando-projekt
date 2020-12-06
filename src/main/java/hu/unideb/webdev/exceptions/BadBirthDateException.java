package hu.unideb.webdev.exceptions;

public class BadBirthDateException extends Exception{

    public BadBirthDateException(){

    }

    public BadBirthDateException(String message){
        super(message);
    }
}
