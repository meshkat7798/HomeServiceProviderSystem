package service;

import base.service.BaseEntityService;
import entity.Order;
import entity.enumeration.OrderStatus;
import entity.user.Customer;

import java.util.List;

@SuppressWarnings("unused")
public interface OrderService extends BaseEntityService<Order,Integer> {
    Order setOrderInfo(Customer customer);

    List<Order> loadByOrderStatus(OrderStatus orderStatus);

    boolean existByOrderStatus(OrderStatus orderStatus);
}
