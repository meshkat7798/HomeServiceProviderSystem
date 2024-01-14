package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Service;
import entity.SubService;
import entity.user.Specialist;
import repository.SubServiceRepository;
import service.SpecialistService;
import service.SubServiceService;
import utility.ApplicationContext;
import utility.InputHandling;

import java.util.List;

@SuppressWarnings("unused")
public class SubServiceServiceImpl extends BaseEntityServiceImpl<SubService,Integer, SubServiceRepository> implements SubServiceService {
    public SubServiceServiceImpl(SubServiceRepository repository) {
        super(repository);
    }
    SpecialistService specialistService = ApplicationContext.getSpecialistService();

    @Override
    public SubService setSubServiceInfo(Service service) {
        System.out.println("*** Please Name The New SubService: ***");

        System.out.println("name:");
        String name = InputHandling.nameInput();
        while (existByName(name)){
            System.out.println("There is a SubService with this name already! choose another name:");
            name = InputHandling.nameInput();
        }

        System.out.println("base price:");
        double basePrice = InputHandling.doubleInput();

        System.out.println("Details:");
        String details = InputHandling.sentenceInput();
        return new SubService(service,name,basePrice,details);
    }

    @Override
    public void showSubServicesOfOneService(Service service){
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
        String details = InputHandling.sentenceInput();

        subService.setDetails(details);
        subService.setBasePrice(basePrice);
        subService.setId(subService.getId());
        creatOrUpdate(subService);
        System.out.println("BasePrice And Details Edited Successfully!");
        return subService;
    }

    @Override
    public void addSpecialistToSubService(SubService subService, Specialist specialist) {
        List<Specialist> specialists = subService.getSpecialists();
        List<SubService> subServices = specialist.getSubServices();
        List<Service> services = specialist.getServices();
        if (!specialists.contains(specialist)) {
            specialists.add(specialist);
            subServices.add(subService);
            specialist.setId(specialist.getId());
            subService.setId(subService.getId());
            specialistService.creatOrUpdate(specialist);
            creatOrUpdate(subService);
            if (!services.contains(subService.getService())) {
                services.add(subService.getService());
            }

        } else {
            System.out.println("This Specialist Is Already Subscribed for This SubService!");
        }
    }

    @Override
    public void removeSpecialistFromSubService(SubService subService, Specialist specialist) {
        List<Specialist> specialists = subService.getSpecialists();
        List<SubService> subServices = specialist.getSubServices();
        if (!specialists.contains(specialist)) {
            System.out.println("This Specialist Is Not Subscribed for This SubService!");


        } else {
            specialists.remove(specialist);
            subServices.remove(subService);
            specialist.setId(specialist.getId());
            subService.setId(subService.getId());
            specialistService.creatOrUpdate(specialist);
            creatOrUpdate(subService);
        }
    }

}
