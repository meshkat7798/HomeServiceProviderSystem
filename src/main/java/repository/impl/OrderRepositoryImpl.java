package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.MyOrder;
import repository.OrderRepository;
import javax.persistence.EntityManager;
import java.util.List;

@SuppressWarnings("unused")
public class OrderRepositoryImpl extends BaseEntityRepositoryImpl<MyOrder,Integer> implements OrderRepository {
    public OrderRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<MyOrder> getEntityClass() {
        return MyOrder.class;
    }

    @Override
    public List<MyOrder> findAllOrders() {

        return entityManager.createQuery(
                "FROM MyOrder " , MyOrder.class
        ).getResultList();
    }

    }

