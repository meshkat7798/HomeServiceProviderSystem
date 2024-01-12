package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Service;
import repository.ServiceRepository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@SuppressWarnings("unused")
public class ServiceRepositoryImpl extends BaseEntityRepositoryImpl<Service, Integer> implements ServiceRepository {
    public ServiceRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Service> getEntityClass() {
        return Service.class;
    }

    @Override
    public boolean existByName(String name) {
        String sql = "select count(u.name) from Service u where u.name = :name ";
        TypedQuery<Long> query = entityManager.createQuery(sql, Long.class);
        query.setParameter("name", name);
        return query.getSingleResult() > 0;

    }
}
