package repository;
import base.repository.BaseEntityRepository;
import entity.MyOrder;

import java.util.List;

@SuppressWarnings("unused")
public interface OrderRepository extends BaseEntityRepository<MyOrder, Integer> {

    List<MyOrder> findAllOrders();
}
