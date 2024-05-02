package to2024g1.eventmanagement.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import to2024g1.eventmanagement.entity.base.BaseEntity;

import java.util.List;

@Entity
@Table(name = "BOOKINGS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Booking extends BaseEntity {
    @Column(name = "NUMBER_OF_RESERVED_SEATS")
    private Integer numberOfReservedSeats;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EVENT_ID")
    private Event event;
}
