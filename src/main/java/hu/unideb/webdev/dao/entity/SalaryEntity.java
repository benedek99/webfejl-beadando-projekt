package hu.unideb.webdev.dao.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "salaries", schema = "employees")
public class SalaryEntity implements Serializable {

    @EmbeddedId
    private SalaryEntityId salaryEntityId;

    @Column(name = "salary")
    private int salary;


    @Column(name = "to_date")
    private Timestamp toDate;

}