package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.user.User;
import repository.UserRepository;
import service.UserService;
import utility.InputHandling;
import utility.Validation;


@SuppressWarnings("unused")
public class UserServiceImpl <T extends User, R extends UserRepository<T>> extends BaseEntityServiceImpl<T, Integer, R> implements UserService<T> {
    public UserServiceImpl(R repository) {
        super(repository);
    }

    @Override
    public T changeUserAndPass(T user) {
        System.out.println("Enter new username:");
        String username = InputHandling.stringInput();
        while (existByUserName(username)) {
            System.out.println("This userName Already Exists! Please choose another one:");
            username = InputHandling.stringInput();
        }
        System.out.println("Enter new password:");
        String password = InputHandling.stringInput();
        while (!Validation.isValidPassword(password)) {
            System.out.println("""
                    Password should be at least 8 characters\040
                    and must contain at least 1 sign(!@#$%), 1 number and 1 Capital letter!""");
            password = InputHandling.stringInput();
        }
        user.setUsername(username);
        user.setPassword(password);
        user.setId(user.getId());
        System.out.println("Username And Password Edited Successfully!");
        return user;
    }

    @Override
    public boolean existByUserNameAndPassword(String username, String password) {
        return repository.existByUserNameAndPassword(username, password);
    }

    @Override
    public T findByUserName(String username) {
        return repository.findByUserName(username);
    }

    @Override
    public boolean existByUserName(String username) {
        return repository.existByUserName(username);
    }

    @Override
    public boolean existByEmail(String email) {
        return repository.existByEmail(email);
    }
}


