package to2024g1.eventmanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import to2024g1.eventmanagement.entity.EventType;
import to2024g1.eventmanagement.repository.EventTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventTypeService {
    private final EventTypeRepository eventTypeRepository;
    public List<EventType> getAllTypes() {
        return eventTypeRepository.findAll();
    }
}
