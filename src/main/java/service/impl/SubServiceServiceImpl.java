package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Service;
import entity.SubService;
import repository.SubServiceRepository;
import service.SubServiceService;
import utility.InputHandling;

import java.util.List;

@SuppressWarnings("unused")
public class SubServiceServiceImpl extends BaseEntityServiceImpl<SubService,Integer, SubServiceRepository> implements SubServiceService {
    public SubServiceServiceImpl(SubServiceRepository repository) {
        super(repository);
    }

    @Override
    public SubService setSubServiceInfo(Service service) {
        System.out.println("*** Please Name The New Service: ***");

        System.out.println("name:");
        String name = InputHandling.nameInput();
        while (existByName(name)){
            System.out.println("There is a SubService with this name already! choose another name:");
            name = InputHandling.nameInput();
        }

        System.out.println("base price:");
        double basePrice = InputHandling.doubleInput();

        System.out.println("Details:");
        String details = InputHandling.stringInput();
        return new SubService(service,name,basePrice,details);
    }

    @Override
    public void showSubServices(Service service){
        List<SubService> subServices = subServicesOfOneService(service);
        for (SubService subService: subServices
        ) {
            System.out.print("ID: "+subService.getId()+": " + subService);
            System.out.println();
        }
    }

    @Override
    public List<SubService> subServicesOfOneService(Service service) {
        return repository.subServicesOfOneService(service);
    }

    @Override
    public boolean existByName(String name) {
        return repository.existByName(name);
    }

    @Override
    public SubService changePriceAndDetails(SubService subService) {
        System.out.println("Enter new price:");
        double basePrice = InputHandling.doubleInput();

        System.out.println("Enter new Details:");
        String details = InputHandling.stringInput();

        subService.setDetails(details);
        subService.setBasePrice(basePrice);
        subService.setId(subService.getId());
        creatOrUpdate(subService);
        System.out.println("BasePrice And Details Edited Successfully!");
        return subService;
    }
}
