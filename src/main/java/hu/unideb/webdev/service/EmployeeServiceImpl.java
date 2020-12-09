package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.EmployeeDao;
import hu.unideb.webdev.exceptions.InvalidBirthDateException;
import hu.unideb.webdev.exceptions.InvalidGenderException;
import hu.unideb.webdev.exceptions.UnknownEmployeeException;
import hu.unideb.webdev.model.Employee;
import hu.unideb.webdev.model.Gender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
    public void recordEmployee(Employee employee) throws UnknownEmployeeException, InvalidBirthDateException, InvalidGenderException {
        if (employee.getBirthDate().before(Timestamp.valueOf("1920-01-01 01:00:00.0")) || employee.getBirthDate().after(new Timestamp(System.currentTimeMillis()))){
            throw new InvalidBirthDateException(String.format("Invalid Birth Date: %s",employee.getBirthDate()), employee.getBirthDate());
        }
        if (!(employee.getGender().equals("F") || employee.getGender().equals("M"))){
            throw  new InvalidGenderException(String.format("Invalid Gender: %s",employee.getGender()),employee.getGender());
        }
        employeeDao.createEmployee(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) throws UnknownEmployeeException, InvalidBirthDateException, InvalidGenderException {

        if (employee.getBirthDate().before(Timestamp.valueOf("1920-01-01 01:00:00.0")) || employee.getBirthDate().after(new Timestamp(System.currentTimeMillis()))){
            throw new InvalidBirthDateException(String.format("Invalid Birth Date: %s",employee.getBirthDate()), employee.getBirthDate());
        }
        if (!(employee.getGender().equals("F") || employee.getGender().equals("M"))){
            throw  new InvalidGenderException(String.format("Invalid Gender: %s",employee.getGender()),employee.getGender());
        }
        employeeDao.deleteEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee employeeOld, Employee employeeNew) throws UnknownEmployeeException, InvalidBirthDateException, InvalidGenderException {
        if (employeeNew.getBirthDate().before(Timestamp.valueOf("1920-01-01 01:00:00.0")) || employeeNew.getBirthDate().after(new Timestamp(System.currentTimeMillis()))){
            throw new InvalidBirthDateException(String.format("Invalid Birth Date: %s",employeeNew.getBirthDate()), employeeNew.getBirthDate());
        }
        if (!(employeeNew.getGender().equals("F") || employeeNew.getGender().equals("M"))){
            throw  new InvalidGenderException(String.format("Invalid Gender: %s",employeeNew.getGender()),employeeNew.getGender());
        }
        employeeDao.updateEmployee(employeeOld,employeeNew);
    }
}
