package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.EmployeeDao;
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
    public void recordEmployee(Employee employee){
        employeeDao.createEmployee(employee);
    }
}
