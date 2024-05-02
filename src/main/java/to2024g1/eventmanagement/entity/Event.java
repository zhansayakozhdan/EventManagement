package to2024g1.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import to2024g1.eventmanagement.entity.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EVENTS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Event extends BaseEntity {

    @Column(name = "EVENT_NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "EVENT_DATE")
    private LocalDate eventDate;

    @Column(name = "EVENT_TIME")
    private LocalTime eventTime;

    @Column(name = "VENUE")
    private String venue;  //место события

    @Column(name = "QUANTITY_OF_TICKETS")
    private Integer quantityOfTickets;

    @Column(name = "TICKET_COST")
    private Double ticketCost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EVENT_TYPE_ID")
    private EventType eventType;
}
