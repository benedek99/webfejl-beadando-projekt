package hu.unideb.webdev.dao;

import hu.unideb.webdev.exceptions.DepartmentAlreadyExistsException;
import hu.unideb.webdev.exceptions.UnknownDepartmentException;
import hu.unideb.webdev.exceptions.UnknownEmployeeException;
import hu.unideb.webdev.model.Department;
import hu.unideb.webdev.model.Employee;

import java.util.Collection;

public interface DepartmentDao {

    void createDepartment(Department department) throws UnknownDepartmentException, DepartmentAlreadyExistsException;

    Collection<Department> readAll();

    void deleteDepartment(Department department) throws UnknownDepartmentException;

    void updateDepartment(Department departmentOld, Department departmentNew) throws UnknownDepartmentException, DepartmentAlreadyExistsException;
}