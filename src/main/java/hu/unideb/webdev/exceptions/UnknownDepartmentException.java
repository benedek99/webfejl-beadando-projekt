package hu.unideb.webdev.exceptions;

import hu.unideb.webdev.model.Department;
import hu.unideb.webdev.model.Employee;

public class UnknownDepartmentException extends Exception{


    private Department department;

    public UnknownDepartmentException(Department department){
        this.department = department;
    }

    public UnknownDepartmentException(String message,Department department){
        super(message);
        this.department = department;
    }

    public UnknownDepartmentException(String message){
        super(message);
    }


}