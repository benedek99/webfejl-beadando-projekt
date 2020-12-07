package hu.unideb.webdev.dao;

import hu.unideb.webdev.exceptions.UnknownEmployeeException;
import hu.unideb.webdev.exceptions.UnknownGenderException;
import hu.unideb.webdev.model.Employee;

import java.util.Collection;

public interface EmployeeDao {

    void createEmployee(Employee employee) throws UnknownGenderException;

    Collection<Employee> readAll();

    void deleteEmployee(Employee employee) throws UnknownEmployeeException;
}
