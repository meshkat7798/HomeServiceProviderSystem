package menu;

import entity.Service;
import entity.SubService;
import entity.enumeration.SpecialistStatus;
import entity.user.Specialist;
import lombok.Getter;
import lombok.Setter;
import utility.InputHandling;

import java.util.List;

@Getter
@Setter
@SuppressWarnings("unused")
public class AdminMenu {
    private String userName;
    private String passWord;


    public AdminMenu() {
        userName = "meshi";
        passWord = "Meshi@123";
    }

    void admin() {
        System.out.println("""
                *** Welcome Dear Admin ***
                1. Check And Confirm New Specialists
                2. Add New Service
                3. Add New Sub To A Service
                4. Add Specialist To SubService
                5. Remove Specialist From SubService
                6. Edit SubServices
                7. SignOut
                                
                           
                """);
        System.out.println("Please choose from above:");
        int input = InputHandling.switchInput(1, 7);
        switch (input) {
            case 1 -> checkAndConfirmNewSpecialists();
            case 2 -> addNewService();
            case 3 -> addNewSubToAService();
            case 4 -> addSpecialistToSubService();
            case 5 -> removeSpecialistFromSubService();
            case 6 -> editSubServices();
            case 7 -> MainMenu.mainMenu.start();

        }

    }

    void checkAndConfirmNewSpecialists() {
        if (isSpecialistEmpty(SpecialistStatus.NEW)) {
            admin();
        } else {
            MainMenu.specialistService.showSpecialistsByStatus(SpecialistStatus.NEW);
            System.out.println("Please Choose by ID:");
            int specialistId = InputHandling.integerInput();
            MainMenu.specialistService.confirmSpecialist(specialistId);
            System.out.println("Specialist Confirmed Successfully!");
            admin();
        }
    }


    void addNewService() {
        Service service = MainMenu.serviceService.setServiceInfo();
        MainMenu.serviceService.creatOrUpdate(service);
        System.out.println("New Service Added Successfully!");
        admin();
    }


    void addNewSubToAService() {
        if (isServiceEmpty()) {
            admin();
        } else {
            MainMenu.serviceService.showServices();
            System.out.println("Please Choose The Service You To Add Sub To By ID:");
            int serviceId = InputHandling.integerInput();
            while (!MainMenu.serviceService.existsById(serviceId)) {
                System.out.println("Please Choose A Valid ID:");
                serviceId = InputHandling.integerInput();
            }
            Service service = MainMenu.serviceService.findById(serviceId);
            SubService subService = MainMenu.subServiceService.setSubServiceInfo(service);
            MainMenu.subServiceService.creatOrUpdate(subService);
            System.out.println("New SubService Added Successfully!");
            admin();
        }
    }


    void addSpecialistToSubService() {
        if (isSpecialistEmpty(SpecialistStatus.CONFIRMED)) {
            admin();
        } else {
            MainMenu.specialistService.showSpecialistsByStatus(SpecialistStatus.CONFIRMED);
            System.out.println("Please Choose A Confirmed Specialist By ID:");
            int specialistId = InputHandling.integerInput();
            if (!MainMenu.specialistService.existsById(specialistId)) {
                System.out.println("InValid ID!!");
                admin();
            } else {
                Specialist specialist = MainMenu.specialistService.findById(specialistId);
                if (!specialist.getSpecialistStatus().equals(SpecialistStatus.CONFIRMED)) {
                    System.out.println("Specialist With This ID Is Not Confirmed Yet!");
                    admin();
                } else {
                    if (isServiceEmpty()) {
                        admin();
                    } else {
                        MainMenu.serviceService.showServices();
                        System.out.println("Please Choose A Service By ID:");
                        int serviceId = InputHandling.integerInput();
                        while (!MainMenu.serviceService.existsById(serviceId)) {
                            System.out.println("Please Choose A Valid ID:");
                            serviceId = InputHandling.integerInput();
                        }
                        Service service = MainMenu.serviceService.findById(serviceId);
                        if (isSubServiceEmpty(service)) {
                            admin();
                        } else {
                            MainMenu.subServiceService.showSubServicesOfOneService(service);
                            System.out.println("Please Choose A SubService By ID:");
                            int subServiceId = InputHandling.integerInput();
                            if (!MainMenu.subServiceService.existsById(subServiceId)) {
                                System.out.println("InValid ID!!");
                                admin();
                            } else {
                                SubService subService = MainMenu.subServiceService.findById(subServiceId);
                                if (!subService.getService().equals(service)) {
                                    System.out.println("This Sub Does not Belong To This Service!");
                                    admin();
                                } else {
                                    if (specialist.getSubServices().contains(subService)) {
                                        System.out.println("This Specialist Already Has This SubService!");
                                        admin();
                                    } else {

                                        MainMenu.subServiceService.addSpecialistToSubService(subService, specialist);
                                        System.out.println("Specialist Added To SubService Successfully!");
                                        admin();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    void removeSpecialistFromSubService() {
        if (isServiceEmpty()) {
            admin();
        } else {
            MainMenu.serviceService.showServices();
            System.out.println("Please Choose A Service By ID:");
            int serviceId = InputHandling.integerInput();
            while (!MainMenu.serviceService.existsById(serviceId)) {
                System.out.println("Please Choose A Valid ID:");
                serviceId = InputHandling.integerInput();
            }
            Service service = MainMenu.serviceService.findById(serviceId);
            if (isSubServiceEmpty(service)) {
                admin();
            } else {
                MainMenu.subServiceService.showSubServicesOfOneService(service);
                System.out.println("Please Choose A SubService By ID:");
                int subServiceId = InputHandling.integerInput();
                if (!MainMenu.subServiceService.existsById(subServiceId)) {
                    System.out.println("InValid ID!!");
                    admin();
                } else {
                    SubService subService = MainMenu.subServiceService.findById(subServiceId);
                    if (!subService.getService().equals(service)) {
                        System.out.println("This Sub Does not Belong To This Service!");
                        admin();
                    } else {

                        List<Specialist> specialists = subService.getSpecialists();
                        if (specialists.isEmpty()) {
                            System.out.println("No Specialists For This SubService!");
                            admin();
                        } else {
                            for (Specialist specialist : specialists
                            ) {
                                System.out.println("SpecialistID " + specialist.getId() + ": " + specialist);
                            }
                            System.out.println("Please Choose The Specialist You Want To Remove By ID:");
                            int specialistId = InputHandling.integerInput();
                            if (!MainMenu.specialistService.existsById(specialistId)) {
                                System.out.println("Invalid Id!!");
                                admin();
                            } else {
                                Specialist specialist = MainMenu.specialistService.findById(specialistId);
                                if (!specialist.getSubServices().contains(subService)) {
                                    System.out.println("This Specialist Does Not Have This SubService!");
                                    admin();
                                } else {

                                    MainMenu.subServiceService.removeSpecialistFromSubService(subService, specialist);
                                    System.out.println("Specialist Removed From SubService Successfully!");
                                    admin();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    void editSubServices() {
        if (isServiceEmpty()) {
            admin();
        } else {
            MainMenu.serviceService.showServices();
            System.out.println("Please Choose A Service By ID:");
            int serviceId = InputHandling.integerInput();
            while (!MainMenu.serviceService.existsById(serviceId)) {
                System.out.println("Please Choose A Valid ID:");
                serviceId = InputHandling.integerInput();
            }
            Service service = MainMenu.serviceService.findById(serviceId);
            if (isSubServiceEmpty(service)) {
                admin();
            } else {
                MainMenu.subServiceService.showSubServicesOfOneService(service);
                System.out.println("Please Choose A SubService By ID:");
                int subServiceId = InputHandling.integerInput();
                while (!MainMenu.subServiceService.existsById(subServiceId) ||
                        !MainMenu.subServiceService.findById(subServiceId).getService().equals(service)) {
                    System.out.println("Please Choose A Valid ID:");
                    subServiceId = InputHandling.integerInput();
                }
                SubService subService = MainMenu.subServiceService.findById(subServiceId);
                MainMenu.subServiceService.changePriceAndDetails(subService);
                admin();
            }
        }
    }


    boolean isServiceEmpty() {
        if (MainMenu.serviceService.findAll().isEmpty()) {
            System.out.println("There Are No Services Yet. Please Add A Service First!");
            return true;
        } else return false;
    }

    boolean isSpecialistEmpty(SpecialistStatus specialistStatus) {
        if (MainMenu.specialistService.loadBySpecialistStatus(specialistStatus).isEmpty()) {
            System.out.println("No " + specialistStatus + " Specialists!");
            return true;
        } else return false;
    }

    boolean isSubServiceEmpty(Service service) {
        if (MainMenu.subServiceService.subServicesOfOneService(service).isEmpty()) {
            System.out.println("There Are No SubServices For This Service!");
            return true;
        } else return false;
    }

}
