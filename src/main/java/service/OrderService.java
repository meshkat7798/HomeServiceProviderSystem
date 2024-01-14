package service;

import base.service.BaseEntityService;
import entity.MyOrder;
import entity.user.Customer;

import java.util.List;

@SuppressWarnings("unused")
public interface OrderService extends BaseEntityService<MyOrder,Integer> {
    MyOrder setOrderInfo(Customer customer);

    List<MyOrder> findAllOrders();
}
