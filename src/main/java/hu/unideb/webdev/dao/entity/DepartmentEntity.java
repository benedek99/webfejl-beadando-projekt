package hu.unideb.webdev.dao.entity;

import hu.unideb.webdev.model.Gender;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "departments", schema = "employees")
public class DepartmentEntity implements Serializable {

    @Id
    @Column(name = "dept_no")
    private String departmentNumber;

    @Column(name = "dept_name")
    private String departmentName;

}
