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
public class Service extends BaseEntity<Integer> {

    @ToString.Exclude
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<SubService> subServices;

    private String name;

    @ToString.Exclude
    @ManyToMany
    private List<Specialist> specialists;

    public Service(String name) {
        this.name = name;
    }
}
