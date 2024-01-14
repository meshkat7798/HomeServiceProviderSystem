package entity;

import base.entity.BaseEntity;
import entity.user.Specialist;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuppressWarnings("unused")
public class Offer extends BaseEntity<Integer> {
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Specialist specialist;
    private LocalDate creationDate;
    private LocalTime creationTime;
    private double offeredPrice;
    private LocalDate offeredStartingDate;
    private double durationHoursOfOrder;

    public Offer(Specialist specialist, double offeredPrice, LocalDate offeredStartingDate, double durationHoursOfOrder) {
        this.specialist = specialist;
        this.offeredPrice = offeredPrice;
        this.offeredStartingDate = offeredStartingDate;
        this.durationHoursOfOrder = durationHoursOfOrder;
        this.creationDate=LocalDate.now();
        this.creationTime =LocalTime.now();
    }
}
