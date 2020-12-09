package hu.unideb.webdev.service;

import hu.unideb.webdev.exceptions.DepartmentAlreadyExistsException;
import hu.unideb.webdev.exceptions.UnknownDepartmentException;
import hu.unideb.webdev.model.Department;

import java.util.Collection;

public interface DepartmentService {

    void createDepartment(Department department) throws UnknownDepartmentException, DepartmentAlreadyExistsException;

    Collection<Department> readAll();

    void deleteDepartment(Department department) throws UnknownDepartmentException;

    void updateDepartment(Department departmentOld, Department departmentNew) throws UnknownDepartmentException, DepartmentAlreadyExistsException;
}