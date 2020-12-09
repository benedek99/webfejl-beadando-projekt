package hu.unideb.webdev.dao;

import hu.unideb.webdev.exceptions.SalaryNotFoundException;
import hu.unideb.webdev.exceptions.UnknownEmployeeException;
import hu.unideb.webdev.model.Salary;

import java.util.Collection;

public interface SalaryDao {

    Collection<Salary> readAll();

    void createSalary(Salary salary) throws UnknownEmployeeException;

    void deleteSalary(Salary salary) throws SalaryNotFoundException, UnknownEmployeeException;

    void updateSalary(Salary salary) throws SalaryNotFoundException;
}
