package hu.unideb.webdev.service;

import hu.unideb.webdev.exceptions.UnknownEmployeeException;
import hu.unideb.webdev.exceptions.UnknownGenderException;
import hu.unideb.webdev.model.Employee;

import java.util.Collection;

public interface EmployeeService {

    Collection<Employee> getAllEmployee();

    void recordEmployee(Employee employee) throws UnknownGenderException;

    void deleteEmployee(Employee employee) throws UnknownEmployeeException;

    void updateEmployee(Employee employeeOld, Employee employeeNew) throws UnknownEmployeeException;
}
