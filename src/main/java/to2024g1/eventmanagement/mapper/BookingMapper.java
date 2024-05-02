package to2024g1.eventmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import to2024g1.eventmanagement.dto.BookingDto;
import to2024g1.eventmanagement.dto.BookingViewDto;
import to2024g1.eventmanagement.entity.Booking;

@Mapper
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    Booking toEntity(BookingDto bookingDto);

    @Mapping(target = "eventName", source = "event.name")
    @Mapping(target = "eventDate", source = "event.eventDate")
    @Mapping(target = "eventTime", source = "event.eventTime")
    @Mapping(target = "status", source = "status", qualifiedByName = "defineStatus")
    @Mapping(target = "userEmail", source = "user.email")
    BookingViewDto toViewDto(Booking booking);

    @Named("defineStatus")
    default String defineStatus(String status){
        if(status == null){
            return null;
        }
        if(status.equals("1")){
            return "Подтверждено";
        } else if (status.equals("0")){
            return "Отменено";
        } else {
            return "В обработке";
        }
    }
}
