package entity.user;

import base.entity.BaseEntity;
import entity.enumeration.Role;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@MappedSuperclass
@NoArgsConstructor
@SuppressWarnings("unused")
@AllArgsConstructor
public class User extends BaseEntity<Integer> {

    private String firstname;
    private String lastname;
    private String email;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
    private String username;
    private String password;
    private LocalDate registrationDate;
    private LocalTime registrationTime;
    private double credit;


    public User(String firstname, String lastname, String email, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.registrationDate=LocalDate.now();
        this.registrationTime = LocalTime.now();
    }

}
