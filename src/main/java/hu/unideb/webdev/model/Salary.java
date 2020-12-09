package hu.unideb.webdev.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.sql.Time;
import java.sql.Timestamp;


@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Salary {

    private int empNo;
    private int salary;
    private Timestamp fromDate;
    private Timestamp toDate;
}
