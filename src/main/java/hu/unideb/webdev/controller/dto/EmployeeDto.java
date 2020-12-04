package hu.unideb.webdev.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private String emp_no;
    private String birth_date;
    private String first_name;
    private String last_name;
    private String gender;
    private  String hire_date;
}