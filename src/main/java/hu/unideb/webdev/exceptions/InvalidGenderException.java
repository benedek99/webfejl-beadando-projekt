package hu.unideb.webdev.exceptions;

import hu.unideb.webdev.model.Gender;

public class InvalidGenderException extends  Exception{

    private String gender;

    public InvalidGenderException(String message,String gender){
        super(message);
        this.gender = gender;
    }

    public InvalidGenderException(String message){
        super(message);
    }
}
