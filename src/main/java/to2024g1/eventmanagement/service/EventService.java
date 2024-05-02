package to2024g1.eventmanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import to2024g1.eventmanagement.dto.EventDto;
import to2024g1.eventmanagement.entity.Event;
import to2024g1.eventmanagement.mapper.EventMapper;
import to2024g1.eventmanagement.repository.EventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id){
        return eventRepository.findById(id).orElseThrow();
    }

    public Event createEvent(EventDto eventDto) {
        Event event = EventMapper.INSTANCE.toEntity(eventDto);
        return eventRepository.save(event);
    }

    public Event editEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEventById(Long id){
      eventRepository.deleteById(id);
    }
}
