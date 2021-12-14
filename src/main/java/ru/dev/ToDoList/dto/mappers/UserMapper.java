package ru.dev.ToDoList.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.dev.ToDoList.dto.UserDto;
import ru.dev.ToDoList.model.User;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    public UserDto toDto(User user);
    public List<UserDto> toDtoList (List<User> userList);
}
