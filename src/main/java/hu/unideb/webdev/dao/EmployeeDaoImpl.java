package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.EmployeeEntity;
import hu.unideb.webdev.exceptions.UnknownEmployeeException;
import hu.unideb.webdev.model.Employee;
import hu.unideb.webdev.model.Gender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao{

    private final EmployeeRepository employeeRepository;

    @Override
    public Collection<Employee> readAll(){
        return StreamSupport.stream(employeeRepository.findAll().spliterator(),false)
                .map(entity -> new Employee(
                        entity.getBirthDate(),
                        entity.getFirstName(),
                        entity.getLastName(),
                        entity.getGender().toString(),
                        entity.getHireDate()
                ))
                .limit(100)
                .collect(Collectors.toList());
    }

    @Override
    public void createEmployee(Employee employee) {

        EmployeeEntity employeeEntity;

        employeeEntity = EmployeeEntity.builder()
                .birthDate(employee.getBirthDate())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .gender(Gender.valueOf(employee.getGender()))
                .hireDate(employee.getHireDate())
                .build();

        log.info("EmployeeEntity: {}", employeeEntity);

        try{
            employeeRepository.save(employeeEntity);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }

    }

    @Override
    public void deleteEmployee(Employee employee) throws UnknownEmployeeException {
        Optional<EmployeeEntity> employeeEntity = StreamSupport.stream(employeeRepository.findAll().spliterator(),false).filter(
                entity -> {
                    return employee.getBirthDate().equals(entity.getBirthDate()) &&
                            employee.getFirstName().equals(entity.getFirstName()) &&
                            employee.getLastName().equals(entity.getLastName()) &&
                            employee.getGender().equals(entity.getGender().toString()) &&
                            employee.getHireDate().equals(entity.getHireDate());

                }
        ).findFirst();
        if(!employeeEntity.isPresent()){
            throw new UnknownEmployeeException(String.format("Employee Not Found: %s",employee), employee);
        }
        log.info("EmployeeEntity: {}", employeeEntity.get());
        employeeRepository.delete(employeeEntity.get());
    }

    @Override
    public void updateEmployee(Employee employeeOld, Employee employeeNew) throws UnknownEmployeeException {
        Optional<EmployeeEntity> employeeEntity = StreamSupport.stream(employeeRepository.findAll().spliterator(),false).filter(
                entity -> {
                    return employeeOld.getBirthDate().equals(entity.getBirthDate()) &&
                            employeeOld.getFirstName().equals(entity.getFirstName()) &&
                            employeeOld.getLastName().equals(entity.getLastName()) &&
                            employeeOld.getGender().equals(entity.getGender().toString()) &&
                            employeeOld.getHireDate().equals(entity.getHireDate());

                }
        ).findAny();
        if(!employeeEntity.isPresent()){
            throw new UnknownEmployeeException(String.format("Employee Not Found: %s",employeeOld), employeeOld);
        }

        employeeEntity.get().setBirthDate(employeeNew.getBirthDate());
        employeeEntity.get().setFirstName(employeeNew.getFirstName());
        employeeEntity.get().setLastName(employeeNew.getLastName());
        employeeEntity.get().setGender(Gender.valueOf(employeeNew.getGender()));
        employeeEntity.get().setHireDate(employeeNew.getHireDate());

        log.info("EmployeeEntity: {}", employeeEntity);
        try{
            employeeRepository.save(employeeEntity.get());
        }
        catch(Exception e){
            log.error(e.getMessage());
        }


    }
}
