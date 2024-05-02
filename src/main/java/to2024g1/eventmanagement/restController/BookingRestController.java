package to2024g1.eventmanagement.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import to2024g1.eventmanagement.dto.BookingDto;
import to2024g1.eventmanagement.entity.Booking;
import to2024g1.eventmanagement.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingRestController {
    private final BookingService bookingService;

    @GetMapping
    public List<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }

    @PostMapping
    public Booking makeBooking(@RequestBody BookingDto bookingDto){
        return bookingService.makeBooking(bookingDto);
    }
}
