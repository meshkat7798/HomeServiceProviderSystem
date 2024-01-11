package repository;
import base.repository.BaseEntityRepository;
import entity.Service;
import entity.SubService;

import java.util.List;

@SuppressWarnings("unused")
public interface SubServiceRepository extends BaseEntityRepository<SubService, Integer> {
    List<SubService> subServicesOfOneService(Service service);
}
