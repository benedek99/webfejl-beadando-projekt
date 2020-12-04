package hu.unideb.webdev.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Employee {

    private String birth_date;
    private String first_name;
    private String last_name;
    private String gender;
    private  String hire_date;
}
