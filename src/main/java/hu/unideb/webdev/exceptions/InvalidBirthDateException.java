package hu.unideb.webdev.exceptions;

import java.sql.Timestamp;

public class InvalidBirthDateException extends Exception{

    private Timestamp birthDate;
    public InvalidBirthDateException(Timestamp birthDate){
        this.birthDate = birthDate;
    }

    public InvalidBirthDateException(String message, Timestamp birthDate){
        super(message);
        this.birthDate = birthDate;
    }

    public InvalidBirthDateException(String message){
        super(message);
    }
}
