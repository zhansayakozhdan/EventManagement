package to2024g1.eventmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import to2024g1.eventmanagement.dto.BookingDto;
import to2024g1.eventmanagement.dto.EventDto;
import to2024g1.eventmanagement.entity.Booking;
import to2024g1.eventmanagement.entity.Event;
import to2024g1.eventmanagement.entity.EventType;
import to2024g1.eventmanagement.service.BookingService;
import to2024g1.eventmanagement.service.EventService;
import to2024g1.eventmanagement.service.EventTypeService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final EventTypeService eventTypeService;
    private final BookingService bookingService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/event/details/{id}")
    public String eventDetailsPage(@PathVariable Long id, Model model){
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "eventDetails";
    }

    @PostMapping("/make/booking/")
    public String makeBooking(BookingDto bookingDto){
        bookingService.makeBooking(bookingDto);

        return "redirect:/event/details/" + bookingDto.getEventId();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/panel/")
    public String manageEvent(Model model){
        List<Event> allEvents = eventService.getAllEvents();
        model.addAttribute("allEvents", allEvents);

        List<EventType> eventTypes = eventTypeService.getAllTypes();
        model.addAttribute("eventTypes", eventTypes);

        return "adminPanel";
    }

    @PostMapping("/create/event/")
    public String createEvent(EventDto eventDto, Model model){
        List<EventType> eventTypes = eventTypeService.getAllTypes();
        model.addAttribute("eventTypes", eventTypes);

        eventService.createEvent(eventDto);

        return "redirect:/";
    }

    @GetMapping("/edit/event/{id}")
    public String editEventPage(@PathVariable Long id, Model model){
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);

        List<EventType> eventTypes = eventTypeService.getAllTypes();
        model.addAttribute("eventTypes", eventTypes);
        return "editEventPage";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/edit/event/")
    public String editEvent(Event event){
        eventService.editEvent(event);
        return "redirect:/admin/panel/";
    }

//    @PostMapping("/delete/event/")
//    public String deleteEvent(Long id){
//        eventService.deleteEventById(id);
//        return "redirect:/admin/panel/";
//    }
}

//@PreAuthorize("isAuthenticated()")