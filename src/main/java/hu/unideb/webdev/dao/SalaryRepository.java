package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.SalaryEntity;
import org.springframework.data.repository.CrudRepository;

public interface SalaryRepository extends CrudRepository<SalaryEntity, Integer> {
}
