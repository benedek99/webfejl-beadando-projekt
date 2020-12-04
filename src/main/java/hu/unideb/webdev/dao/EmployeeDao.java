package hu.unideb.webdev.dao;

import hu.unideb.webdev.model.Employee;

import java.util.Collection;

public interface EmployeeDao {

   // void createEmployee(Employee employee);

    Collection<Employee> readAll();

    //void deleteEmployee(Employee employee);
}
