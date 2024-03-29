package service;

import base.service.BaseEntityService;
import entity.user.User;

import java.time.LocalDate;

@SuppressWarnings("unused")
public interface UserService<T extends User> extends BaseEntityService<T,Integer> {

    boolean existByUserName(String username);

    boolean existByEmail(String email);

    T changeUserAndPass(T user);

    boolean existByUserNameAndPassword(String username, String password);

    T findByUserName(String userName);

}
