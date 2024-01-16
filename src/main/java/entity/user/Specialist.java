package entity.user;

import entity.Offer;
import entity.MyOrder;
import entity.Service;
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
    private SpecialistStatus specialistStatus;
    @Lob
    private byte[] profilePicture;
    @ElementCollection
    private List<Integer> specialistScores;
    private String specialities;

    @ToString.Exclude
    @ManyToMany(mappedBy = "specialists")
    private List<SubService> subServices;

    @ToString.Exclude
    @ManyToMany(mappedBy = "specialists")
    private List<Service> services;

    @ToString.Exclude
    @OneToMany(mappedBy = "specialist", cascade = CascadeType.ALL)
    private List<MyOrder> myOrders;

    @ToString.Exclude
    @OneToMany(mappedBy = "specialist", cascade = CascadeType.ALL)
    private List<Offer> offers;

    public Specialist(String firstname, String lastname, String email, String username, String password, byte[] profilePicture, String specialities) {
        super(firstname, lastname, email, username, password);
        this.profilePicture = profilePicture;
        this.specialities = specialities;
        super.setRole(Role.SPECIALIST);
        this.specialistStatus = SpecialistStatus.NEW;
        this.specialistScores = new ArrayList<>();
        this.setCredit(0);
    }

}
