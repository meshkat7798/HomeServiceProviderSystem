package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Service;
import entity.SubService;
import repository.SubServiceRepository;
import javax.persistence.EntityManager;
import java.util.List;

@SuppressWarnings("unused")
public class SubServiceRepositoryImpl extends BaseEntityRepositoryImpl<SubService,Integer> implements SubServiceRepository {
    public SubServiceRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<SubService> getEntityClass() {
        return SubService.class;
    }

    @Override
    public List<SubService> subServicesOfOneService(Service service) {
        try {
            return entityManager.createQuery("""
                            FROM SubService i
                            WHERE i.service =: service
                            """, SubService.class)
                    .setParameter("service", service)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
