package hu.unideb.webdev.service;

import hu.unideb.webdev.exceptions.InvalidDatesException;
import hu.unideb.webdev.exceptions.InvalidSalaryException;
import hu.unideb.webdev.exceptions.UnknownEmployeeException;
import hu.unideb.webdev.model.Salary;

import java.util.Collection;

public interface SalaryService {

    Collection<Salary> readAll();

    public void createSalary(Salary salary) throws UnknownEmployeeException, InvalidSalaryException, InvalidDatesException;
}
