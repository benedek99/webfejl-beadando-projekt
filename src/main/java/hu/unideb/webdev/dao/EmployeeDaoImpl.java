package hu.unideb.webdev.dao;

import hu.unideb.webdev.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
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

}
