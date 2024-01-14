package repository;
import base.repository.BaseEntityRepository;
import entity.Order;
import entity.enumeration.OrderStatus;

import java.util.List;

@SuppressWarnings("unused")
public interface OrderRepository extends BaseEntityRepository<Order, Integer> {
    List<Order> loadByOrderStatus(OrderStatus orderStatus);

    boolean existByOrderStatus(OrderStatus orderStatus);
}
