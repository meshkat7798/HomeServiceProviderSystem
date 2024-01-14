package menu;

import entity.Order;
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
            customer(customer);
        } else {
            System.out.println("Invalid Username Or Password");
            MainMenu.mainMenu.start();
        }
    }

    public void customer(Customer customer) {
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
                customer(customer);
            }

            case 2 -> {
                Order order = MainMenu.orderService.setOrderInfo(customer);
                MainMenu.orderService.creatOrUpdate(order);
                System.out.println("Your Order Is Placed Successfully And Awaits For Specialists Offers");
                customer(customer);
            }
            case 3 -> {
                List<Order> orders = customer.getOrders();
                if ( orders.isEmpty()){
                    System.out.println("No Orders Yet");
                    customer(customer);
                }else {
                int count = 0;
                for (Order order : orders
                ) {
                    if (order.getCustomer().equals(customer)) {
                        System.out.println("ID " + order.getId() + ": " + order);
                        count++;
                    }

                }
                if (count == 0) {
                    System.out.println("No Histories Yet!");
                }
                customer(customer);
            }}

            case 4 -> MainMenu.mainMenu.start();
        }
    }
}
