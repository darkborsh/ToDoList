package ru.dev.ToDoList.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.dev.ToDoList.dto.UserDto;
import ru.dev.ToDoList.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDto toDto(User user);
    User dtoToUser(UserDto dto);
    List<UserDto> toDtoList (List<User> userList);
}
