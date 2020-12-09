package hu.unideb.webdev.service;

import hu.unideb.webdev.exceptions.InvalidBirthDateException;
import hu.unideb.webdev.exceptions.InvalidGenderException;
import hu.unideb.webdev.exceptions.UnknownEmployeeException;
import hu.unideb.webdev.model.Employee;

import java.util.Collection;

public interface EmployeeService {

    Collection<Employee> getAllEmployee();

    void recordEmployee(Employee employee) throws UnknownEmployeeException, InvalidBirthDateException, InvalidGenderException;

    void deleteEmployee(Employee employee) throws UnknownEmployeeException, InvalidBirthDateException, InvalidGenderException;

    void updateEmployee(Employee employeeOld, Employee employeeNew) throws UnknownEmployeeException, InvalidBirthDateException, InvalidGenderException;
}
