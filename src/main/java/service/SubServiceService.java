package service;

import base.service.BaseEntityService;
import entity.Service;
import entity.SubService;

import java.util.List;

@SuppressWarnings("unused")
public interface SubServiceService extends BaseEntityService<SubService,Integer> {

    SubService setSubServiceInfo(Service service);

    void showSubServices(Service service);

    List<SubService> subServicesOfOneService(Service service);

    boolean existByName(String name);

    SubService changePriceAndDetails(SubService subService);
}
