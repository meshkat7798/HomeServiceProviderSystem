package entity;

import base.entity.BaseEntity;
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

    public Service(String name) {
        this.name = name;
    }
}
