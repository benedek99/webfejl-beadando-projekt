package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.EmployeeEntity;
import hu.unideb.webdev.exceptions.UnknownEmployeeException;
import hu.unideb.webdev.exceptions.UnknownGenderException;
import hu.unideb.webdev.model.Employee;
import hu.unideb.webdev.model.Gender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao{

    private final EmployeeRepository employeeRepository;
    private final SalaryRepository salaryRepository;
    private final TitleRepository titleRepository;

    @Override
    public Collection<Employee> readAll(){
        return StreamSupport.stream(employeeRepository.findAll().spliterator(),false)
                .map(entity -> new Employee(
                        entity.getBirthDate().toString(),
                        entity.getFirstName(),
                        entity.getLastName(),
                        entity.getGender().toString(),
                        entity.getHireDate().toString()
                ))
                .limit(100)
                .collect(Collectors.toList());
    }

    @Override
    public void createEmployee(Employee employee) throws UnknownGenderException{

        EmployeeEntity employeeEntity;

        employeeEntity = EmployeeEntity.builder()
                .birthDate(Timestamp.valueOf(employee.getBirth_date()))
                .firstName(employee.getFirst_name())
                .lastName(employee.getLast_name())
                .gender(Gender.valueOf(employee.getGender()))
                .hireDate(Timestamp.valueOf(employee.getHire_date()))
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
                    return employee.getBirth_date().equals(entity.getBirthDate()) &&
                            employee.getFirst_name().equals(entity.getFirstName()) &&
                            employee.getLast_name().equals(entity.getLastName()) &&
                            employee.getGender().equals(entity.getGender()) &&
                            employee.getHire_date().equals(entity.getHireDate());

                }
        ).findAny();
        if(!employeeEntity.isPresent()){
            throw new UnknownEmployeeException(String.format("Employee Not Found %s",employee), employee);
        }
        employeeRepository.delete(employeeEntity.get());
    }
}
