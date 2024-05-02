package to2024g1.eventmanagement.dto;

import lombok.*;
import to2024g1.eventmanagement.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingViewDto {
    private Long id;
    private Integer numberOfReservedSeats;
    private String status;
    private String userEmail;
    private String fullName;
    private String eventName;
    private String eventDate;
    private String eventTime;
}
