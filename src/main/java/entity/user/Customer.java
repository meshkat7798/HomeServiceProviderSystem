package entity.user;

import entity.MyOrder;
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
    private List<MyOrder> myOrders;

    public Customer(String firstname, String lastname, String email, String username, String password) {
        super(firstname, lastname, email, username, password);
        this.setCredit(10000);
    }
}
