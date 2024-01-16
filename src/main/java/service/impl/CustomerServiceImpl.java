package service.impl;

import entity.user.Customer;
import repository.CustomerRepository;
import service.CustomerService;
import utility.InputHandling;
import utility.Validation;

@SuppressWarnings("unused")
public class CustomerServiceImpl extends UserServiceImpl<Customer, CustomerRepository> implements CustomerService {
    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
    }

    @Override
    public Customer setCustomerInfo() {
        System.out.println("*** Please Fill The Form Below: ***");

        System.out.println("firstname:");
        String firstname = InputHandling.nameInput();

        System.out.println("lastname:");
        String lastname = InputHandling.nameInput();


        System.out.println("email:");
        String email = InputHandling.stringInput();
        while (!Validation.isValidEmail(email)) {
            System.out.println("Please Enter A Valid Email Address!");
            System.out.println("email:");
            email = InputHandling.stringInput();
            while (existByEmail(email)) {
                System.out.println("This Email Already Exists! Please Choose Another One:");
                System.out.println("email:");
                email = InputHandling.stringInput();
            }
        }

        System.out.println("username:");
        String username = InputHandling.stringInput();
        while (existByUserName(username)) {
            System.out.println("This Username Already Exists! Please Choose Another One:");
            System.out.println("username:");
            username = InputHandling.stringInput();
        }
        System.out.println("password:");
        String password = InputHandling.stringInput();
        while (!Validation.isValidPassword(password)) {
            System.out.println("""
                    Password should be at least 8 characters\040
                    and must contain at least 1 sign(!@#$%), 1 number and 1 Capital letter!""");
            password = InputHandling.stringInput();
        }

        return new Customer(firstname, lastname,email,username,password);
    }
}
