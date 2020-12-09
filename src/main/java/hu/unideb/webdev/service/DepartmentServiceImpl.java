package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.DepartmentDao;
import hu.unideb.webdev.exceptions.DepartmentAlreadyExistsException;
import hu.unideb.webdev.exceptions.UnknownDepartmentException;
import hu.unideb.webdev.model.Department;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentDao departmentDao;

    @Override
    public Collection<Department> readAll() {
        return departmentDao.readAll();
    }

    @Override
    public void createDepartment(Department department) throws UnknownDepartmentException, DepartmentAlreadyExistsException {
        departmentDao.createDepartment(department);
    }

    @Override
    public void deleteDepartment(Department department) throws UnknownDepartmentException {
        departmentDao.deleteDepartment(department);
    }

    @Override
    public void updateDepartment(Department departmentOld, Department departmentNew) throws UnknownDepartmentException, DepartmentAlreadyExistsException {
        departmentDao.updateDepartment(departmentOld,departmentNew);
    }
}
