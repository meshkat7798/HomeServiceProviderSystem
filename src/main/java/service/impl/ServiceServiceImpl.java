package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Service;
import repository.ServiceRepository;
import service.ServiceService;
import utility.InputHandling;

import java.util.List;

@SuppressWarnings("unused")
public class ServiceServiceImpl extends BaseEntityServiceImpl<Service,Integer, ServiceRepository> implements ServiceService {
    public ServiceServiceImpl(ServiceRepository repository) {
        super(repository);
    }

    @Override
    public Service setServiceInfo() {
        System.out.println("*** Please Name The New Service: ***");

        System.out.println("name:");
        String name = InputHandling.nameInput();
        return new Service(name);
    }

    @Override
    public void showServices(){
        List<Service> services = (List<Service>) findAll();
        for (Service service: services
             ) {
            System.out.print("ID: "+service.getId()+": " + service);
            System.out.println();
        }
    }
}
