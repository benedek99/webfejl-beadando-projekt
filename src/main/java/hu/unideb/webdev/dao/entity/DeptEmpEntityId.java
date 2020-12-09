package hu.unideb.webdev.dao.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class DeptEmpEntityId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "emp_no")
    private EmployeeEntity employee;

    @ManyToOne
    @JoinColumn(name = "dept_no")
    private DepartmentEntity department;
}
