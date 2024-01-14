package menu;

import entity.user.Customer;
import entity.user.Specialist;
import service.*;
import utility.ApplicationContext;
import utility.InputHandling;


@SuppressWarnings("unused")
public class MainMenu {
    public static CommentService commentService  = ApplicationContext.getCommentService();
    public static CustomerService customerService =ApplicationContext.getCustomerService();
    public static OfferService offerService =ApplicationContext.getOfferService();
    public static OrderService orderService  =ApplicationContext.getOrderService();
    public static ServiceService serviceService = ApplicationContext.getServiceService();
    public static SpecialistService specialistService  =ApplicationContext.getSpecialistService();
    public static SubServiceService subServiceService =ApplicationContext.getSubServiceService();
    public static MainMenu mainMenu  = new MainMenu();

    public void start() {
        System.out.println( """
                *** Welcome ***
                1. SignUp
                2. SignIn
                3. Exit
                                   
                """);
        System.out.println("Please choose from above:");
        int input = InputHandling.switchInput(1, 3);
        switch (input) {
            case 1 -> signUp();
            case 2 -> loginMenu();
            case 3 -> {
                System.out.println("See You soon :)");
                System.exit(0);
            }
        }


    }
    void loginMenu(){
        System.out.println( """
                1. Admin
                2. Customer
                3. Specialist
                4. Back
                                   
                """);
        System.out.println("Please choose from above:");
        int input = InputHandling.switchInput(1, 4);
        switch (input){
            case 1->{
                AdminMenu adminMenu = new AdminMenu();
                System.out.println("Please Enter Your Username: ");
                String username = InputHandling.stringInput();
                System.out.println("Please Enter Your Password: ");
                String password = InputHandling.stringInput();
                if(!(adminMenu.getUserName().equals(username)) || !(adminMenu.getPassWord().equals(password))){
                    System.out.println("Wrong Username Or Password!");
                    loginMenu();
                }else {
                    adminMenu.admin();
                }
            }
            case 2->{
                System.out.println("Please Enter Your Username: ");
                String username = InputHandling.stringInput();
                System.out.println("Please Enter Your Password: ");
                String password = InputHandling.stringInput();
                new CustomerMenu(username,password);

            }
            case 3->{
                System.out.println("Please Enter Your Username: ");
                String username = InputHandling.stringInput();
                System.out.println("Please Enter Your Password: ");
                String password = InputHandling.stringInput();
                new SpecialistMenu(username,password);
            }
            case 4 ->start();
            }
        }



    void signUp(){
        System.out.println( """
                1. Specialist
                2. Customer
                3. Back
                                   
                """);
        System.out.println("Please choose from above:");
        int input = InputHandling.switchInput(1,3);
        switch (input){
            case 1-> {
                Specialist specialist = specialistService.setSpecialistInfo();
                specialistService.creatOrUpdate(specialist);
                System.out.println("You Are Successfully Registered!" +
                        " Please Wait For Admin To Confirm You ");
                start();
            }
            case 2 ->{
                Customer customer = customerService.setCustomerInfo();
                customerService.creatOrUpdate(customer);
                System.out.println("Welcome "+customer.getFirstname()+"! " +
                        "You Are Successfully Registered!");
                start();
            }
            case 3-> start();
        }
    }
}
