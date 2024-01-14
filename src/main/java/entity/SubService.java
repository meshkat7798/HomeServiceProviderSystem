package entity;

import base.entity.BaseEntity;
import entity.user.Specialist;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuppressWarnings("unused")
public class SubService extends BaseEntity<Integer> {
    @ToString.Exclude
    @ManyToMany
    private List<Specialist> specialists;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Service service;

    private String name;
    private double basePrice;
    private String details;

    public SubService(Service service, String name, double basePrice,String details ) {
        this.service = service;
        this.name = name;
        this.basePrice = basePrice;
        this.details = details;
    }
}
