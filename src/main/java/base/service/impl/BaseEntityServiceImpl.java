package base.service.impl;


import base.entity.BaseEntity;
import base.repository.BaseEntityRepository;
import base.service.BaseEntityService;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@SuppressWarnings("unused")
@RequiredArgsConstructor
public class BaseEntityServiceImpl<T extends BaseEntity<ID>, ID extends Serializable, R extends BaseEntityRepository<T, ID>> implements BaseEntityService<T, ID> {

    protected final R repository;

    @Override
    public void creatOrUpdate(T entity) {
        repository.creatOrUpdate(entity);
    }

    @Override
    public void delete(ID id) {
        repository.delete(id);
        System.out.println("Account Deleted Successfully!");
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public Collection<T> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    @Override
    public void beginTransaction() {
        repository.beginTransaction();
    }

    @Override
    public void commitTransaction() {
        repository.commitTransaction();
    }

    @Override
    public void rollBack() {
        repository.rollBack();
    }

}
