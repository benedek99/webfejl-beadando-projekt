package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.EmployeeDao;
import hu.unideb.webdev.exceptions.UnknownEmployeeException;
import hu.unideb.webdev.exceptions.UnknownGenderException;
import hu.unideb.webdev.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Override
    public Collection<Employee> getAllEmployee(){
        return employeeDao.readAll();
    }

    @Override
    public void recordEmployee(Employee employee) throws UnknownGenderException {
        employeeDao.createEmployee(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) throws UnknownEmployeeException {
        employeeDao.deleteEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee employeeOld, Employee employeeNew) throws UnknownEmployeeException {
        employeeDao.updateEmployee(employeeOld,employeeNew);
    }
}
