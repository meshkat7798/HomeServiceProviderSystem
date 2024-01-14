package menu;

import entity.MyOrder;
import entity.SubService;
import entity.enumeration.OrderStatus;
import entity.enumeration.SpecialistStatus;
import entity.user.Specialist;
import utility.InputHandling;
import utility.SecurityContext;

import java.util.List;

@SuppressWarnings("unused")
public class SpecialistMenu {
    String username;
    String password;


    public SpecialistMenu(String username, String password) {
        this.username = username;
        this.password = password;
        login();
    }

    public void login() {
        if (MainMenu.specialistService.existByUserNameAndPassword(username, password)) {
            Specialist specialist = MainMenu.specialistService.findByUserName(username);
            if(!specialist.getSpecialistStatus().equals(SpecialistStatus.CONFIRMED)){
                System.out.println("You Can Not Have Access To System Until The Admin Confirms You As a Specialist." +
                        " Please Try Again Later");
                MainMenu.mainMenu.start();
            }else{
            SecurityContext.fillUserContext(specialist);
            specialist(specialist);}
        } else {
            System.out.println("Invalid Username Or Password");
            MainMenu.mainMenu.start();
        }
    }

    public void specialist(Specialist specialist){
        System.out.println( """
                1. Change Username And Password
                2. Check The New Orders In You Specialities Awaiting For Specialists
                3. Check Your Speciality Sub Services
                4. SignOut
                
                                   
                """);
        System.out.println("Please choose from above:");
        int input = InputHandling.switchInput(1, 4);
        switch (input){
            case 1-> {MainMenu.specialistService.creatOrUpdate(MainMenu.specialistService.changeUserAndPass(specialist));
            specialist(specialist);}
                case 2->{
                int count =0;
                List<SubService> subServices= specialist.getSubServices();
                List<MyOrder> newMyOrders = MainMenu.orderService.findAllOrders();
                for (MyOrder myOrder : newMyOrders
                     ) { if(subServices.contains(myOrder.getSubService()) &&
                        myOrder.getOrderStatus().equals(OrderStatus.AWAITING_SPECIALIST_OFFER)){
                    System.out.println("ID " + myOrder.getId()+ ": " + myOrder);
                    count++;
                }
                }
                if (count==0){
                    System.out.println("Nothing To Show!");
                }
                specialist(specialist);
            }
            case 3 ->{
                List<SubService> subServices = specialist.getSubServices();
                if (subServices.isEmpty()){
                    System.out.println("No SubServices Yet!");
                }else {
                    for (SubService subservice: subServices
                         ) {
                        System.out.println("ID " + subservice.getId()+ ": " +subservice);
                    }
                }
                specialist(specialist);
            }
            case 4 -> MainMenu.mainMenu.start();

    }
}}
