package to2024g1.eventmanagement.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import to2024g1.eventmanagement.entity.EventType;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private String name;
    private String description;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private String venue;  //место события
    private Integer quantityOfTickets;
    private Double ticketCost;
    private EventType eventType;
}
