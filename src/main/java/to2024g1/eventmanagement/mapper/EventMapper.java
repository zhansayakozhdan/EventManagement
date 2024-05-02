package to2024g1.eventmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import to2024g1.eventmanagement.dto.EventDto;
import to2024g1.eventmanagement.entity.Event;

@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    Event toEntity(EventDto eventDto);
}
