package entity.user;

import entity.Offer;
import entity.Order;
import entity.SubService;
import entity.enumeration.Role;
import entity.enumeration.SpecialistStatus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuppressWarnings("unused")
public class Specialist extends User {

    @Column
    @Enumerated(EnumType.STRING)
    private SpecialistStatus status;
    private String photo;
    @ElementCollection
    private List<Integer> specialistScores;
    private String specialities;

    @ToString.Exclude
    @ManyToMany(mappedBy = "specialists", cascade = CascadeType.ALL)
    private List<SubService> subServices;

    @ToString.Exclude
    @OneToMany(mappedBy = "specialist", cascade = CascadeType.ALL)
    private List<Order> orders;

    @ToString.Exclude
    @OneToMany(mappedBy = "specialist", cascade = CascadeType.ALL)
    private List<Offer> offers;

    public Specialist(String firstname, String lastname, String email,String username, String password,String photo,String specialities) {
        super(firstname, lastname, email, username, password);
        super.setRole(Role.SPECIALIST);
        this.status = SpecialistStatus.NEW;
        this.photo = photo;
        this.specialities = specialities;
        this.specialistScores = new ArrayList<>();
    }
}