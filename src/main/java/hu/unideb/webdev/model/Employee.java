package hu.unideb.webdev.model;

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
}
