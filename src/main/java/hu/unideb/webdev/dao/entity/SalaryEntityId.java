package hu.unideb.webdev.dao.entity;

import hu.unideb.webdev.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Builder
@Embeddable
@Getter
public class SalaryEntityId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "emp_no")
    private EmployeeEntity employee;

    @Column(name = "from_date")
    private Timestamp fromDate;

}
