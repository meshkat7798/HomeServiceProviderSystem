package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Order;
import repository.OrderRepository;
import javax.persistence.EntityManager;
@SuppressWarnings("unused")
public class OrderRepositoryImpl extends BaseEntityRepositoryImpl<Order,Integer> implements OrderRepository {
    public OrderRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Order> getEntityClass() {
        return Order.class;
    }
}
