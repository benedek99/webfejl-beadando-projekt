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
@Table(name = "titles", schema = "employees")
public class TitleEntity implements Serializable {

    @EmbeddedId
    private TitleEntityId titleEntityId;

    @Column(name = "to_date")
    private Timestamp toDate;

}
