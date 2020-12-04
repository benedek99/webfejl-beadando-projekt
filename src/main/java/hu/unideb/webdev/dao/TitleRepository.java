package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.TitleEntity;
import org.springframework.data.repository.CrudRepository;

public interface TitleRepository extends CrudRepository<TitleEntity, Integer> {
}
