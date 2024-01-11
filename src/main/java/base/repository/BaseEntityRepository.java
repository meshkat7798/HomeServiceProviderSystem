package base.repository;

import java.io.Serializable;
import java.util.Collection;

@SuppressWarnings("unused")
public interface BaseEntityRepository<T,ID extends Serializable> {
    void beginTransaction();

    void commitTransaction();

    void rollBack();

    void creatOrUpdate(T entity);

    void delete(ID id);

    T findById(ID id);

    Collection<T> findAll();

    boolean existsById(ID id);
}
