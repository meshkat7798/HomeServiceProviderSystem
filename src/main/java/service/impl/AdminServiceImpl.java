package service.impl;

import entity.SubService;
import entity.enumeration.SpecialistStatus;
import entity.user.Specialist;
import service.AdminService;
import service.SpecialistService;
import service.SubServiceService;
import utility.ApplicationContext;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    SpecialistService specialistService = ApplicationContext.getSpecialistService();
    SubServiceService subServiceService = ApplicationContext.getSubServiceService();
    @Override
    public void addSpecialistToSubService(SubService service, Specialist specialist) {
        List<Specialist> specialists = service.getSpecialists();
        List<SubService> subServices = specialist.getSubServices();
        if (!specialists.contains(specialist)){
            specialists.add(specialist);
            subServices.add(service);
            specialist.setId(specialist.getId());
            service.setId(service.getId());
            specialistService.creatOrUpdate(specialist);
            subServiceService.creatOrUpdate(service);

        }else {
            System.out.println("This Specialist Is Already Subscribed for This SubService!");
        }
    }

    @Override
    public void removeSpecialistFromSubService(SubService service, Specialist specialist) {
        List<Specialist> specialists = service.getSpecialists();
        List<SubService> subServices = specialist.getSubServices();
        if (!specialists.contains(specialist)){
            System.out.println("This Specialist Is Not Subscribed for This SubService!");


        }else {
            specialists.remove(specialist);
            subServices.remove(service);
            specialist.setId(specialist.getId());
            service.setId(service.getId());
            specialistService.creatOrUpdate(specialist);
            subServiceService.creatOrUpdate(service);
        }
    }
    @Override
    public Specialist confirmSpecialist(int specialistId){
        if(!specialistService.existsById(specialistId)){
            System.out.println("Specialist With This Id does Not Exist!");
            return null;
        }else {
            Specialist specialist = specialistService.findById(specialistId);
            specialist.setStatus(SpecialistStatus.CONFIRMED);
            specialist.setId(specialist.getId());
            specialistService.creatOrUpdate(specialist);
            return specialist;
        }
    }


}
