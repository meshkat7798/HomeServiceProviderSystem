package service;

import entity.user.Customer;
@SuppressWarnings("unused")
public interface CustomerService extends UserService<Customer> {
     Customer setCustomerInfo();

}
