package service;

import base.service.BaseEntityService;
import entity.Service;
@SuppressWarnings("unused")
public interface ServiceService extends BaseEntityService<Service,Integer> {

    Service setServiceInfo();

    void showServices();

    boolean existByName(String name);
}
