package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Order;
import entity.enumeration.OrderStatus;
import repository.OrderRepository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("unused")
public class OrderRepositoryImpl extends BaseEntityRepositoryImpl<Order,Integer> implements OrderRepository {
    public OrderRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Order> getEntityClass() {
        return Order.class;
    }

    @Override
    public List<Order> loadByOrderStatus(OrderStatus orderStatus) {
            List<Order> results = entityManager.createQuery("""
                            FROM Order i
                            WHERE i.orderStatus= :orderStatus
                            """, Order.class)
                    .setParameter("orderStatus", orderStatus)
                    .getResultList();
            return results;
        }

    @Override
    public boolean existByOrderStatus(OrderStatus orderStatus) {
        String sql = "select count(u.orderStatus) from Order u where u.orderStatus = :orderStatus ";
        TypedQuery<Long> query = entityManager.createQuery(sql, Long.class);
        query.setParameter("orderStatus", orderStatus);
        return query.getSingleResult() > 0;

    }
    }

