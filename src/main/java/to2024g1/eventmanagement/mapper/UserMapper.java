package to2024g1.eventmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import to2024g1.eventmanagement.dto.UserCreate;
import to2024g1.eventmanagement.dto.UserDto;
import to2024g1.eventmanagement.dto.UserUpdate;
import to2024g1.eventmanagement.entity.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    //так как здесь у нас методы не статик мы не можем ее просто вызвать в другом классе
    // поэтому и пишем данную строку чтобы мы могли вызвать все методы в другом классе

    User toEntity(UserCreate userCreate);
    User toUpdatedUser(UserUpdate userUpdate);

    UserDto toDtoView(User user); //автоматический сам создаст новый класс внутри который сам пропишет всю логику (проверка на нул и builder)
    //можешь ее проверить внутри папки build/classes/mapper
}
