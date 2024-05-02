package to2024g1.eventmanagement.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import to2024g1.eventmanagement.entity.Event;
import to2024g1.eventmanagement.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {
    private Integer numberOfReservedSeats;
    private String status;
    private String userEmail;
    private Long eventId;

}
