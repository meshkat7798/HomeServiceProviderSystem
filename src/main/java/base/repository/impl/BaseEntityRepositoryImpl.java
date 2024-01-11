package base.repository.impl;

import base.entity.BaseEntity;
import base.repository.BaseEntityRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Collection;

@SuppressWarnings("unused")
@RequiredArgsConstructor

public abstract class BaseEntityRepositoryImpl<T extends BaseEntity<ID>, ID extends Serializable> implements BaseEntityRepository<T, ID> {
    protected final EntityManager entityManager;

    @Override
    public void beginTransaction() {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
    }

    @Override
    public void commitTransaction() {
        if(entityManager.getTransaction().isActive()){
            entityManager.getTransaction().commit();
        }

    }

    @Override
    public void rollBack() {
        if(entityManager.getTransaction().isActive()){
            entityManager.getTransaction().rollback();
        }
    }
    public void nonTransactionalSave(T entity) {

        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }

    }

    @Override
    public void creatOrUpdate(T entity) {
        try {
            beginTransaction();
            nonTransactionalSave(entity);
            commitTransaction();
            entityManager.clear();
        }catch (Exception e){
            e.printStackTrace();
            rollBack();
        }

    }

    @Override
    public void delete(ID id) {
        try {
            beginTransaction();
            T entity = entityManager.find(getEntityClass(),id);
            entityManager.remove(entity);
            commitTransaction();
        }catch (Exception e){
            e.printStackTrace();
            rollBack();
        }

    }

    @Override
    public T findById(ID id) {
        return entityManager.find(getEntityClass(), id);
    }

    @Override
    public Collection<T> findAll() {

        return entityManager.createQuery(
                "FROM " + getEntityClass().getSimpleName(), getEntityClass()
        ).getResultList();
    }

    @Override
    public boolean existsById(ID id) {
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(t) from " + getEntityClass().getSimpleName() + " t where t.id = :id", Long.class
        );
        query.setParameter("id", id);
        return query.getSingleResult() > 0;
    }
    public abstract Class<T> getEntityClass();
}
