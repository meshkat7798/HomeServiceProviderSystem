package service;

import base.service.BaseEntityService;
import entity.Service;
import entity.SubService;
import entity.user.Specialist;

import java.util.List;

@SuppressWarnings("unused")
public interface SubServiceService extends BaseEntityService<SubService,Integer> {

    SubService setSubServiceInfo(Service service);

    void showSubServicesOfOneService(Service service);

    List<SubService> subServicesOfOneService(Service service);

    boolean existByName(String name);

    SubService changePriceAndDetails(SubService subService);
    void addSpecialistToSubService(SubService service, Specialist specialist);

    void removeSpecialistFromSubService(SubService service, Specialist specialist);
}
