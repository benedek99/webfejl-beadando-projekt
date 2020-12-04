package hu.unideb.webdev.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
public class SalaryEntityId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "emp_no")
    private EmployeeEntity employee;

    @Column(name = "from_date")
    private Timestamp fromDate;
}
