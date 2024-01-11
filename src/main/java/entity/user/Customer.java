package entity.user;

import entity.Comment;
import entity.Order;
import entity.enumeration.Role;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuppressWarnings("unused")
public class Customer extends User {
    @ToString.Exclude
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Customer(String firstname, String lastname, String email, String username, String password) {
        super(firstname, lastname, email, username, password);
    }
}
