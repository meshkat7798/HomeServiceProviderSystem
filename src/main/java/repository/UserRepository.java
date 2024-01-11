package repository;
import base.repository.BaseEntityRepository;
import entity.user.User;
@SuppressWarnings("unused")
public interface UserRepository <T extends User> extends BaseEntityRepository<T, Integer> {

    boolean existByUserName(String username);

    boolean existByUserNameAndPassword(String username, String password);

    T findByUserName(String userName);
}
