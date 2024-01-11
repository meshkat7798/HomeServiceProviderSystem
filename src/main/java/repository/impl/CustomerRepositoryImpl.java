package repository.impl;

import entity.user.Customer;
import repository.CustomerRepository;
import javax.persistence.EntityManager;
@SuppressWarnings("unused")
public class CustomerRepositoryImpl extends UserRepositoryImpl<Customer> implements CustomerRepository {
    public CustomerRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }
}
