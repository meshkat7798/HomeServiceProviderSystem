package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.user.User;
import repository.UserRepository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@SuppressWarnings("unused")
public abstract class UserRepositoryImpl<T extends User> extends BaseEntityRepositoryImpl<T, Integer> implements UserRepository<T> {
    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public boolean existByUserName(String username) {
            String sql = "select count(u.username) from "
                    + getEntityClass().getSimpleName()
                    + " u where u.username = :username" ;
            TypedQuery<Long> query = entityManager.createQuery(sql, Long.class);
            query.setParameter("username", username);
            return query.getSingleResult() > 0;
    }

    @Override
    public boolean existByUserNameAndPassword(String username, String password) {
            String sql = "select count(u.userName) from "
                    + getEntityClass().getSimpleName()
                    + " u where u.username = :username And u.password = :password";
            TypedQuery<Long> query = entityManager.createQuery(sql, Long.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getSingleResult() > 0;

    }

    @Override
    public T findByUserName(String username) {
            String hql = "SELECT t FROM " + getEntityClass().getSimpleName() + " t WHERE t.username = :username";
            TypedQuery<T> query = entityManager.createQuery(hql, getEntityClass());
            query.setParameter("username", username);
            return query.getSingleResult();
    }
}
