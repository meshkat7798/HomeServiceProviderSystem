package menu;

import entity.MyOrder;
import entity.Service;
import entity.SubService;
import entity.user.Customer;
import utility.InputHandling;
import utility.SecurityContext;

import java.util.List;

@SuppressWarnings("unused")
public class CustomerMenu {
    String username;
    String password;


    public CustomerMenu(String username, String password) {
        this.username = username;
        this.password = password;
        login();
    }

    public void login() {
        if (MainMenu.customerService.existByUserNameAndPassword(username, password)) {
            Customer customer = MainMenu.customerService.findByUserName(username);
            SecurityContext.fillUserContext(customer);
            customerMenu(customer);
        } else {
            System.out.println("Invalid Username Or Password");
            MainMenu.mainMenu.start();
        }
    }

    public void customerMenu(Customer customer) {
        System.out.println("""
                1. Change Username And Password
                2. Place A New Order
                3. Check Your Order History
                4. SignOut
                                
                                   
                """);
        System.out.println("Please choose from above:");
        int input = InputHandling.switchInput(1, 4);
        switch (input) {
            case 1 -> {
                MainMenu.customerService.creatOrUpdate(MainMenu.customerService.changeUserAndPass(customer));
                customerMenu(customer);
            }

            case 2 -> {
                if(AdminMenu.isServiceEmpty()){
                    customerMenu(customer);
                }else{
                System.out.println("Please Choose a Service By ID From Below:");
                System.out.println();
                MainMenu.serviceService.showServices();
                int serviceId = InputHandling.integerInput();
                while (!MainMenu.serviceService.existsById(serviceId)) {
                    System.out.println("Please Choose An Existing ID:");
                    serviceId = InputHandling.integerInput();
                }
                Service service = MainMenu.serviceService.findById(serviceId);
                if(AdminMenu.isSubServiceEmpty(service)){customerMenu(customer);
                }else {
                System.out.println("Please Choose a SubService By ID From Below:");
                System.out.println();
                MainMenu.subServiceService.showSubServicesOfOneService(service);
                int subServiceId = InputHandling.integerInput();
                while (!MainMenu.subServiceService.existsById(subServiceId)) {
                    System.out.println("Please Choose An Existing ID:");
                    subServiceId = InputHandling.integerInput();
                }
                SubService subService = MainMenu.subServiceService.findById(subServiceId);

                MyOrder myOrder = MainMenu.orderService.setOrderInfo(customer,subService);
                MainMenu.orderService.creatOrUpdate(myOrder);
                System.out.println("Your Order Is Placed Successfully And Awaits For Specialists Offers");
                customerMenu(customer);
            }}}
            case 3 -> {
                if ( customer.getMyOrders().isEmpty()){
                    System.out.println("No Orders Yet");
                    customerMenu(customer);
                }else {
                    List<MyOrder> myOrders = customer.getMyOrders();
                int count = 0;
                for (MyOrder myOrder : myOrders
                ) {
                    if (myOrder.getCustomer().equals(customer)) {
                        System.out.println("ID " + myOrder.getId() + ": " + myOrder);
                        count++;
                    }

                }
                if (count == 0) {
                    System.out.println("No Histories Yet!");
                }
                customerMenu(customer);
            }}

            case 4 -> MainMenu.mainMenu.start();
        }
    }
}
