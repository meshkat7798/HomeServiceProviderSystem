package entity;

import base.entity.BaseEntity;
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
public class Comment extends BaseEntity<Integer> {
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Order order;

    private int specialistScore;
    private String comment;
    private LocalDate creationDate;
    private LocalTime creationTime;

    public Comment(Order order, int specialistScore, String comment) {
        this.order = order;
        this.specialistScore = specialistScore;
        this.comment = comment;
        this.creationDate=LocalDate.now();
        this.creationTime = LocalTime.now();
    }
}
