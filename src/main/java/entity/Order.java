package entity;

import base.entity.BaseEntity;
import entity.enumeration.OrderStatus;
import entity.user.Customer;
import entity.user.Specialist;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuppressWarnings("unused")
public class Order extends BaseEntity<Integer> {

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Customer customer;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Specialist specialist;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private SubService subService;

    private String address;
    private double offeredPrice;
    private double finalPrice;
    private LocalDate dateOfNeed;
    private  String details;
    private LocalDate creationDate;
    private LocalTime creationTime;

    public Order(Customer customer, SubService subService, String address, double offeredPrice, LocalDate dateOfNeed, String details) {
        this.customer = customer;
        this.subService = subService;
        this.address = address;
        this.offeredPrice = offeredPrice;
        this.dateOfNeed = dateOfNeed;
        this.details = details;
        this.orderStatus = OrderStatus.AWAITING_SPECIALIST_OFFER;
        this.creationDate = LocalDate.now();
        this.creationTime = LocalTime.now();
    }
}
