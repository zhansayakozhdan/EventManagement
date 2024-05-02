package to2024g1.eventmanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import to2024g1.eventmanagement.dto.BookingDto;
import to2024g1.eventmanagement.entity.Booking;
import to2024g1.eventmanagement.entity.Event;
import to2024g1.eventmanagement.entity.User;
import to2024g1.eventmanagement.mapper.BookingMapper;
import to2024g1.eventmanagement.repository.BookingRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final EventService eventService;

    public Optional<Booking> getBookingById(Long id){
        return bookingRepository.findById(id);
    }

    public Booking makeBooking(BookingDto bookingDto) {
        Booking booking = BookingMapper.INSTANCE.toEntity(bookingDto);
        User user = userService.getUserByEmail(bookingDto.getUserEmail());
        booking.setUser(user);

        Event event = eventService.getEventById(bookingDto.getEventId());
        String result = decreaseTicketQuantity(event, bookingDto.getNumberOfReservedSeats());
        if (result.equals("Successfully booked!")) {
            booking.setEvent(event);
            eventService.editEvent(event);
            booking.setStatus("ПОДТВЕРЖДЕНО");
        } else {
            return null;
        }
        return bookingRepository.save(booking);
    }

    public void cancelBookingById(Long id){
        Booking booking = getBookingById(id).orElseThrow();
        if(booking == null){
            return;
        }
        Event event = eventService.getEventById(booking.getEvent().getId());
        increaseTicketQuantity(event, booking.getNumberOfReservedSeats());
        bookingRepository.deleteById(id);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void increaseTicketQuantity(Event event, int returnedTicket) {
        event.setQuantityOfTickets(event.getQuantityOfTickets() + returnedTicket);
    }

    public String decreaseTicketQuantity(Event event, int reservedTicket) {
        if (event.getQuantityOfTickets() >= reservedTicket) {
            event.setQuantityOfTickets(event.getQuantityOfTickets() - reservedTicket);
            return "Successfully booked!";
        } else if (event.getQuantityOfTickets() < reservedTicket && event.getQuantityOfTickets() != 0) {
            return "There are only" + event.getQuantityOfTickets() + "tickets left";
        } else {
            return "There are no tickets left!";
        }
    }

    public List<Booking> getAllBookingsByUserId(Long userId) {
        return bookingRepository.findAllBookingsByUserId(userId);
    }

    public List<Booking> getAllBookingsByEventId(Long eventId) {
        Event event = eventService.getEventById(eventId);
        if (event == null) {
            return null;
        } else {
            return bookingRepository.findAllBookingsByEventId(eventId);
        }
    }
}
