package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Service;
import repository.ServiceRepository;
import javax.persistence.EntityManager;
@SuppressWarnings("unused")
public class ServiceRepositoryImpl extends BaseEntityRepositoryImpl<Service, Integer> implements ServiceRepository {
    public ServiceRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Service> getEntityClass() {
        return Service.class;
    }
}
