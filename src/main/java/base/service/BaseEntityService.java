package base.service;

import base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Collection;

@SuppressWarnings("unused")
public interface BaseEntityService<T extends BaseEntity<ID>, ID extends Serializable> {

    void creatOrUpdate(T entity);

    void delete(ID id);

    T findById(ID id);

    Collection<T> findAll();

    boolean existsById(ID id);

    void beginTransaction();

    void commitTransaction();

    void rollBack();

}
