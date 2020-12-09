package hu.unideb.webdev.model;

import hu.unideb.webdev.dao.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Employee {

    private Timestamp birthDate;
    private String firstName;
    private String lastName;
    private String gender;
    private Timestamp hireDate;

    public Employee(EmployeeEntity entity){
        new Employee(
                entity.getBirthDate(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getGender().toString(),
                entity.getHireDate());
    }
}
