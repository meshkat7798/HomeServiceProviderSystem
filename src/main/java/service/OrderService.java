package service;

import base.service.BaseEntityService;
import entity.Order;
import entity.user.Customer;

@SuppressWarnings("unused")
public interface OrderService extends BaseEntityService<Order,Integer> {
    Order setOrderInfo(Customer customer);
}
